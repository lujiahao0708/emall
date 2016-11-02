package com.taotao.portal.controller;

import com.taotao.portal.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页
 *
 * @author lujiahao
 * @version V1.0
 * @email jiahao.lu@qtparking.com
 * @create 2016-09-06 14:15
 */
@Controller
public class IndexController {
    @Autowired
    private ContentService contentService;

    @RequestMapping(value = "/index")
    public String showIndex(Model model){
        String adJson = contentService.getContentList();
        model.addAttribute("ad1",adJson);
        return "index";
    }
}
