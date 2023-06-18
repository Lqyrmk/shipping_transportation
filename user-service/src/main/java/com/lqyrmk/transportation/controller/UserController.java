package com.lqyrmk.transportation.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lqyrmk.transportation.common.Result;
import com.lqyrmk.transportation.entity.LoginUser;
import com.lqyrmk.transportation.entity.User;
import com.lqyrmk.transportation.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/3/18 16:47
 */
@Api(tags = "用户接口")
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @description: 根据id查询用户
     * @author: YuanmingLiu
     * @date: 2023/6/6 17:53
     * @param: []
     * @return: com.lqyrmk.emovie.common.Result<com.lqyrmk.emovie.entity.User>
     **/
    @GetMapping("/info")
    @ApiOperation(value = "根据id查询用户")
    @ApiImplicitParams({
    })
    public Result<User> getUserById() {

        // 获取SecurityContextHolder中的用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getUserId();

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(User::getUserId, userId)
                .select(User::getUserId, User::getUsername, User::getEmail, User::getPhone, User::getGender);
        User user = userService.getOne(queryWrapper);
        if (user != null) {
            return Result.success(user, "用户信息查询成功！");
        }
        return Result.error("用户信息查询失败！");

    }

    /**
     * @description: 用户登录
     * @author: YuanmingLiu
     * @date: 2023/3/18 17:12
     * @param: [session, user]
     * @return: com.lqyrmk.emovie.common.Result<com.lqyrmk.emovie.entity.User>
     **/
    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户登录信息", required = true)
    })
    public Result<Map<String, Object>> login(@RequestBody User user) {
        // 登录
        return userService.login(user);
    }

    /**
     * @description: 用户退出登录
     * @author: YuanmingLiu
     * @date: 2023/3/18 17:14
     * @param: [session]
     * @return: com.lqyrmk.emovie.common.Result<java.lang.String>
     **/
    @PostMapping("/logout")
    @ApiOperation(value = "用户退出登录")
    @ApiImplicitParams({
    })
    public Result<String> logout() {
        return userService.logout();
    }

    /**
     * @description: 用户注册
     * @author: YuanmingLiu
     * @date: 2023/3/18 17:41
     * @param: [user]
     * @return: void
     **/
    @PostMapping("/register")
    @ApiOperation(value = "用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户注册信息", required = true)
    })
    public Result<User> register(@RequestBody User user) {
        userService.register(user);
        return Result.success(null, "注册成功！");
    }

}
