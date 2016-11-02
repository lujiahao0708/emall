package com.taotao.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;
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
    public TaotaoResult insertContent(TbContent tbContent){
        TaotaoResult taotaoResult = contentService.insertContent(tbContent);
        return taotaoResult;
    }
}
