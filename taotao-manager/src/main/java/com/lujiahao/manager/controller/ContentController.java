package com.lujiahao.manager.controller;

import com.lujiahao.common.pojo.TaotaoResult;
import com.lujiahao.manager.service.ContentService;
import com.lujiahao.mapping.pojo.TbContent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 内容管理
 *
 * @author lujiahao
 * @version V1.0
 * @email jiahao.lu@qtparking.com
 * @create 2016-09-12 17:15
 */
@Controller
@RequestMapping(value = "/content")
public class ContentController {
    @Autowired
    private ContentService contentService;

    @RequestMapping(value = "/save")
    @ResponseBody
    public TaotaoResult insertContent(TbContent tbContent) {
        TaotaoResult taotaoResult = contentService.insertContent(tbContent);
        return taotaoResult;
    }
}
