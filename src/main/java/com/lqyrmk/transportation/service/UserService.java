package com.lqyrmk.transportation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lqyrmk.transportation.common.Result;
import com.lqyrmk.transportation.entity.User;

import java.util.Map;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/3/18 16:47
 */
public interface UserService extends IService<User> {

    /**
     * @description: 用户登录
     * @author: YuanmingLiu
     * @date: 2023/6/9 15:13
     * @param: [user]
     * @return: com.lqyrmk.emovie.common.Result<com.lqyrmk.emovie.entity.User>
     **/
    Result<Map<String, Object>> login(User user);

    /**
     * @description: 用户退出登录
     * @author: YuanmingLiu
     * @date: 2023/6/9 15:16
     * @param: []
     * @return: com.lqyrmk.emovie.common.Result<java.lang.String>
     **/
    Result<String> logout();


    /**
     * @description: 用户注册
     * @author: YuanmingLiu
     * @date: 2023/3/18 17:33
     * @param: [user]
     * @return: void
     **/
    User register(User user);

}
