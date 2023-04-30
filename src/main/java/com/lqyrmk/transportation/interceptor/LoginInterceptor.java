package com.lqyrmk.transportation.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Description 登录检查拦截器
 * @Author YuanmingLiu
 * @Date 2023/4/30 18:39
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * @description: 目标方法执行之前
     * @author: YuanmingLiu
     * @date: 2023/4/30 18:40
     * @param: [request, response, handler]
     * @return: boolean
     **/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("preHandle拦截的请求路径是{}", requestURI);

        // 登录检查逻辑
        HttpSession session = request.getSession();
        Object loginShipper = session.getAttribute("loginShipper");
        if (loginShipper != null) {
            // 放行
            log.info("登录放行");
            return true;
        }
        // 未登录则跳转到登录页
        log.info("登录拦截");
        request.setAttribute("loginError", "请先登录");
        request.getRequestDispatcher("/").forward(request, response);
        return false;
    }

}
