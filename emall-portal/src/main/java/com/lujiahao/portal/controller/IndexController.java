package com.lujiahao.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lujiahao on 2017/3/27.
 */
@Controller
public class IndexController {

    @RequestMapping(value = "index")
    public String showIndex() {
        return "index";
    }
}
