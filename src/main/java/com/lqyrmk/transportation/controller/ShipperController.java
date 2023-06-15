package com.lqyrmk.transportation.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lqyrmk.transportation.common.Result;
import com.lqyrmk.transportation.entity.Carrier;
import com.lqyrmk.transportation.entity.Shipper;
import com.lqyrmk.transportation.service.ShipperService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description 托运人控制层
 * @Author YuanmingLiu
 * @Date 2023/4/27 19:14
 */
@Api(tags = "托运人接口")
@Slf4j
@RestController
@RequestMapping("/shipper")
public class ShipperController {

    @Autowired
    private ShipperService shipperService;


    @GetMapping("/details")
    @ApiOperation("查询所有的承运商")
    @ApiImplicitParams({
    })
    public Result<Shipper> getAllCarrier() {
        // 获取所有的承运商
        LambdaQueryWrapper<Shipper> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Shipper::getShipperId, 14L)
                .select(Shipper::getShipperId, Shipper::getShipperName);
        Shipper shipper = shipperService.getOne(queryWrapper);
        return Result.success(shipper, "查询成功！");
    }

//    /**
//     * @description: 注册页
//     * @author: YuanmingLiu
//     * @date: 2023/4/23 20:23
//     * @param: []
//     * @return: java.lang.String
//     **/
//    @GetMapping("/register")
//    public String registerPage() {
//        return "register";
//    }
//
//    /**
//     * @description: 用户注册
//     * @author: YuanmingLiu
//     * @date: 2023/4/23 20:28
//     * @param: []
//     * @return: java.lang.String
//     **/
//    @PostMapping("/register")
//    public String register(Shipper shipper, String passwordConfirm, Model model) {
//
//        model.addAttribute("shipperName", shipper.getShipperName());
//        model.addAttribute("password", shipper.getPassword());
//        model.addAttribute("passwordConfirm", passwordConfirm);
//
//        if (!shipper.getPassword().equals(passwordConfirm)) {
//            // 两次密码不一致
//            model.addAttribute("registerError", "两次密码不一致");
//            return "register";
//        }
//
//        // 用户名已存在
//        if (shipperService.getShipperByName(shipper.getShipperName()) != null) {
//            model.addAttribute("registerError", "用户名已存在");
//            return "register";
//        }
//        // 用户名可用
//        shipperService.registerShipper(shipper);
//        // 重定向防止表单重复提交
//        return "redirect:/login";
//    }
//
//
//    /**
//     * @description: 登录页
//     * @author: YuanmingLiu
//     * @date: 2023/4/23 18:03
//     * @param: []
//     * @return: java.lang.String
//     **/
//    @GetMapping(value = {"/", "/login"})
//    public String loginPage() {
//        return "login";
//    }
//
//    /**
//     * @description: 用户登录
//     * @author: YuanmingLiu
//     * @date: 2023/4/23 20:00
//     * @param: [shipper, session, model]
//     * @return: java.lang.String
//     **/
//    @PostMapping("/login")
//    public String main(Shipper shipper, HttpSession session, Model model) {
//
//        Shipper loginShipper = shipperService.login(shipper.getShipperName(), shipper.getPassword());
//
//        if (loginShipper != null) {
//            log.info("login shipper: {}", loginShipper);
//            // 把登录成功的用户保存起来
//            session.setAttribute("loginShipper", loginShipper);
//            // 登录成功重定向到index.html，重定向防止表单重复提交
//            return "redirect:/index.html";
//        }
//        // 登录失败回到登录页面
//        else {
//            model.addAttribute("loginError", "用户名或密码错误");
//            // 回到登录页面
//            return "login";
//        }
//    }
//
//    /**
//     * @description: 去主页面
//     * @author: YuanmingLiu
//     * @date: 2023/4/23 18:07
//     * @param: [session, model]
//     * @return: java.lang.String
//     **/
//    @GetMapping("/index.html")
//    public String indexPage(HttpSession session, Model model) {
//        return "index";
//    }
//
//    /**
//     * @description: 退出登录
//     * @author: YuanmingLiu
//     * @date: 2023/4/30 18:33
//     * @param: []
//     * @return: java.lang.String
//     **/
//    @GetMapping("/logout")
//    public String logout(HttpSession session) {
//        session.removeAttribute("loginShipper");
//        return "login";
//    }

}
