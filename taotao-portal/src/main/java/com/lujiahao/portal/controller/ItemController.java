package com.lujiahao.portal.controller;

import com.lujiahao.portal.pojo.ItemInfo;
import com.lujiahao.portal.service.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 商品详情页面展示
 * Created by lujiahao on 2016/10/31.
 */
@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    /**
     * 根据商品id查询商品信息
     * @param itemId
     * @param model
     * @return
     */
    @RequestMapping(value = "/item/{itemId}")
    public String showItem(@PathVariable Long itemId, Model model){
        ItemInfo item = itemService.getItemById(itemId);
        model.addAttribute("item",item);
        return "item";
    }

    /**
     * 根据商品id查询商品描述  这个在前端会延迟一秒加载
     * produces = MediaType.TEXT_HTML_VALUE+";charset=utf-8" 解决乱码
     * @param itemId
     * @return
     */
    @RequestMapping(value = "/item/desc/{itemId}",produces = MediaType.TEXT_HTML_VALUE+";charset=utf-8")
    @ResponseBody
    public String getItemDesc(@PathVariable Long itemId){
        String itemDescById = itemService.getItemDescById(itemId);
        return itemDescById;
    }

    /**
     * 根据商品id查询商品规格参数
     * @param itemId
     * @return
     */
    @RequestMapping(value = "/item/param/{itemId}",produces = MediaType.TEXT_HTML_VALUE+";charset=utf-8")
    @ResponseBody
    public String getItemParam(@PathVariable Long itemId){
        String itemDescById = itemService.getItemParamById(itemId);
        return itemDescById;
    }
}
