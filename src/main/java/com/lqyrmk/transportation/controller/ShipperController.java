package com.lqyrmk.transportation.controller;

import com.lqyrmk.transportation.entity.Shipper;
import com.lqyrmk.transportation.service.ShipperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/4/27 19:14
 */
@Slf4j
@Controller
public class ShipperController {

    @Autowired
    private ShipperService shipperService;

//    @RequestMapping("/index")
//    public String index() {
//        return "index";
//    }

    /**
     * @description: 注册页
     * @author: YuanmingLiu
     * @date: 2023/4/23 20:23
     * @param: []
     * @return: java.lang.String
     **/
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }


    /**
     * @description: 登录页
     * @author: YuanmingLiu
     * @date: 2023/4/23 18:03
     * @param: []
     * @return: java.lang.String
     **/
    @GetMapping(value = {"/", "/login"})
    public String loginPage() {
        return "login";
    }

    /**
     * @description: 用户登录
     * @author: YuanmingLiu
     * @date: 2023/4/23 20:00
     * @param: [shipper, session, model]
     * @return: java.lang.String
     **/
    @PostMapping("/login")
    public String main(Shipper shipper, HttpSession session, Model model) {

        Shipper loginShipper = shipperService.login(shipper.getShipperName(), shipper.getPassword());

        if (loginShipper != null) {
            log.info("login shipper: {}", loginShipper);
            // 把登录成功的用户保存起来
            session.setAttribute("loginShipper", loginShipper);
            // 登录成功重定向到index.html，重定向防止表单重复提交
            return "redirect:/index.html";
        }
        // 登录失败回到登录页面
        else {
            model.addAttribute("msg", "账号密码错误");
            // 回到登录页面
            return "login";
        }
    }

    /**
     * @description: 去主页面
     * @author: YuanmingLiu
     * @date: 2023/4/23 18:07
     * @param: [session, model]
     * @return: java.lang.String
     **/
    @GetMapping("/index.html")
    public String indexPage(HttpSession session, Model model) {
//        Object loginUser = session.getAttribute("loginUser");
//        if (loginUser != null) {
//            return "main";
//        } else {
//            // 回到登录页面
//            model.addAttribute("msg", "请重新登录");
//            return "login";
//        }
        return "index";
    }

}
