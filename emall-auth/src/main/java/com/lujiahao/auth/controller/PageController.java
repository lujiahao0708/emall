package com.lujiahao.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 页面跳转Controller
 * Created by lujiahao on 2016/11/1.
 */
@Controller
@RequestMapping(value = "/page")
public class PageController {

    /**
     * 跳转到注册页面
     * @return
     */
    @RequestMapping(value = "/register")
    public String showRegister(){
        return "register";
    }

    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping(value = "/login")
    public String showLogin(String redirect, Model model){
        model.addAttribute("redirect",redirect);// 把回调的url传递给登录页面的jsp
        return "login";
    }

    /**
     * 忘记密码页面
     */
    @RequestMapping(value = "/forgotPassword")
    public String forgotPassword(){
        return "forgotPassword";
    }

    //http://localhost:8084/page/haha?carNumbers=444&carNumbers=222&carNumbers=333
    @RequestMapping(value = "haha")
    @ResponseBody
    public void haha(String... carNumbers){
        System.out.println(carNumbers.length);
    }
}
