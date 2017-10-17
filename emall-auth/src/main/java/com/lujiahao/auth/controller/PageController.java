package com.lujiahao.auth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lujiahao
 * @version 1.0
 * @date 2017-10-17 18:26
 */
@Controller
public class PageController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PageController.class);

    @RequestMapping("/")
    public String index(){
        return "index";
    }

}
