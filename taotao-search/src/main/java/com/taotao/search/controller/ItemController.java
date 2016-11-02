package com.taotao.search.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.search.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 索引库维护
 * Created by lujiahao on 2016/10/24.
 */
@Controller
@RequestMapping(value = "/manager")
public class ItemController {
    @Autowired
    private ItemService itemService;

    /**
     * 导入商品数据到索引库
     */
    @RequestMapping(value = "/importall")
    @ResponseBody
    public TaotaoResult importAllItems() {
        TaotaoResult taotaoResult = itemService.importAllItems();
        return taotaoResult;
    }
}
