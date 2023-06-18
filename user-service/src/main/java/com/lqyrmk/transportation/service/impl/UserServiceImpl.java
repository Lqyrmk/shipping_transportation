package com.lqyrmk.transportation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lqyrmk.transportation.common.Result;
import com.lqyrmk.transportation.common.TransportationException;
import com.lqyrmk.transportation.entity.LoginUser;
import com.lqyrmk.transportation.entity.Shipper;
import com.lqyrmk.transportation.entity.User;
import com.lqyrmk.transportation.entity.UserRole;
import com.lqyrmk.transportation.mapper.ShipperMapper;
import com.lqyrmk.transportation.mapper.UserMapper;
import com.lqyrmk.transportation.mapper.UserRoleMapper;
import com.lqyrmk.transportation.service.UserService;
import com.lqyrmk.transportation.utils.JwtUtil;
import com.lqyrmk.transportation.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/3/18 16:48
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private ShipperMapper shipperMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public Result<Map<String, Object>> login(User user) {
        // AuthenticationManager authenticate方法进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        // 认证未通过
        if (authenticate == null) {
            throw new RuntimeException("用户名或密码错误！");
        }
        // 认证通过，使用userid生成jwt
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getUserId().toString();
        String jwt = JwtUtil.createJWT(userId);
        Map<String, Object> map = new HashMap<>();
        map.put("token", jwt);

        // 将完整的用户信息存入redis，id作为key
//        redisTemplate.opsForValue().set("login:" + userId, loginUser);
        redisCache.setCacheObject("login:" + userId, loginUser);

        List<String> permissions = loginUser.getPermissions();
        // 判断permissions中是否存在管理员权限
        if (permissions.stream().anyMatch("system:manage"::equals)) {
            map.put("permission", 2);
        }
        else {
            map.put("permission", 1);
        }

        // 返回jwt
        return Result.success(map, "登录成功！");
    }

    @Override
    public Result<String> logout() {
        // 获取SecurityContextHolder中的用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getUserId();
        // 删除redis中的值
        redisCache.deleteObject("login:" + userId);
        return Result.success(null, "已退出登录！");
    }

    @Override
    public User register(User user) {

        // 加密
        String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);

        // 查询是否存在相同的用户名
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, user.getUsername());
        if (userMapper.selectOne(queryWrapper) != null) {
            throw new TransportationException("用户名已存在");
        }
        // 查询邮箱是否已被绑定
        LambdaQueryWrapper<User> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.eq(User::getEmail, user.getEmail());
        if (userMapper.selectOne(queryWrapper2) != null) {
            throw new TransportationException("该邮箱已绑定其他账号");
        }
        // 查询手机号是否已被绑定
        LambdaQueryWrapper<User> queryWrapper3 = new LambdaQueryWrapper<>();
        queryWrapper3.eq(User::getPhone, user.getPhone());
        if (userMapper.selectOne(queryWrapper3) != null) {
            throw new TransportationException("该手机号已绑定其他账号");
        }
        // 用户可被注册
        // 添加用户
        userMapper.insert(user);

        // 创建权限关系，注册获得用户角色
        UserRole userRole = new UserRole(user.getUserId(), 1L);
        userRoleMapper.insert(userRole);

        Shipper shipper = new Shipper();
        shipper.setShipperId(user.getUserId());
        shipper.setShipperName(user.getUsername());
        shipperMapper.insert(shipper);

        return user;
    }

}
