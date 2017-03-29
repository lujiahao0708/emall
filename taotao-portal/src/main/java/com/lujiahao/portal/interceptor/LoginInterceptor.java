package com.lujiahao.portal.interceptor;

import com.lujiahao.common.utils.CookieUtils;
import com.lujiahao.mapping.pojo.TbUser;
import com.lujiahao.portal.service.impl.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户登录的拦截器
 * Created by lujiahao on 2016/11/1.
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private UserServiceImpl userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 在handler执行之前处理
        // 判断用户是否登录
        // 1.从cookie中取token
        String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
        // 根据token换取用户信息,调用sso系统的接口
        TbUser user = userService.getUserByToken(token);
        // 取不到用户信息
        if (user == null) {
            // 跳转到登录界面,把用户请求的url作为参数传递给登陆界面
            response.sendRedirect(userService.SSO_BASE_URL + userService.SSO_PAGE_LOGIN + "?redirect=" + request.getRequestURL());
            // 返回false 拦截
            return false;
        }
        // 取到用户信息,放行
        return true;
        // 返回值决定handler是否执行. true:执行;false:不执行
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // handler执行之后,返回ModelAndView之后
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 返回ModelAndView之后
    }
}
