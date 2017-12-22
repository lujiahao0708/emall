package com.lujiahao.manager.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lujiahao
 * @date 2017-12-12 下午6:25
 */
@Controller
public class LoginController {

    @Value("${sso.server}")
    private String ssoServer;
    @Value("${sso.loginUrl}")
    private String loginUrl;

    @RequestMapping("/")
    public String login(Model model) {
        model.addAttribute("url1", ssoServer + loginUrl);
//        Map<String, Object> map = new HashMap<>();
//        map.put("url",ssoServer + loginUrl);
        return "login";
    }
}
