package com.lujiahao.rest.controller;

import com.lujiahao.common.pojo.TaotaoResult;
import com.lujiahao.rest.service.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 查询商品Controller
 * Created by lujiahao on 2016/10/30.
 */
@Controller
@RequestMapping(value = "/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    /**
     * 根据Id查询商品的基本信息
     * @param itemId
     * @return
     */
    @RequestMapping(value = "/info/{itemId}")
    @ResponseBody
    public TaotaoResult getItemBaseInfo(@PathVariable Long itemId){
        TaotaoResult taotaoResult = itemService.getItemBaseInfo(itemId);
        return taotaoResult;
    }

    /**
     * 根据商品id获取商品描述信息
     * @param itemId
     * @return
     */
    @RequestMapping(value = "/desc/{itemId}")
    @ResponseBody
    public TaotaoResult getItemDesc(@PathVariable Long itemId){
        TaotaoResult taotaoResult = itemService.getItemDesc(itemId);
        return taotaoResult;
    }

    /**
     * 根据商品id获取商品规格参数
     * @param itemId
     * @return
     */
    @RequestMapping(value = "/param/{itemId}")
    @ResponseBody
    public TaotaoResult getItemParam(@PathVariable Long itemId){
        TaotaoResult taotaoResult = itemService.getItemParam(itemId);
        return taotaoResult;
    }
}
