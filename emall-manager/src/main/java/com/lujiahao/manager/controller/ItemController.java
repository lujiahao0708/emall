package com.lujiahao.manager.controller;

import com.github.pagehelper.PageInfo;
import com.lujiahao.common.pojo.TaotaoResult;
import com.lujiahao.manager.service.ItemService;
import com.lujiahao.mapping.pojo.TbItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 后台管理系统
 * 商品信息管理接口
 *
 * @author lujiahao
 */
@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    /**
     * 查询商品-商品列表页面
     */
    @RequestMapping(value = "/showItemList")
    public String showItemList(@RequestParam(defaultValue = "1") Integer pageNum,
                               @RequestParam(defaultValue = "10") Integer pageSize,
                               ModelMap modelMap) {
        PageInfo<TbItem> pageInfo = itemService.getAllItem(pageNum, pageSize);
        modelMap.put("pageInfo", pageInfo);
        return "manager/itemlist";
    }


    /**
     * 添加商品条目
     */
    @RequestMapping(value = "/item/save", method = RequestMethod.POST)
    @ResponseBody
    private TaotaoResult createItem(TbItem item, String desc) throws Exception {
        TaotaoResult result = itemService.createItem(item, desc);
        return result;
    }

    /**
     * 根据商品id查询商品信息
     */
    // 这是支持restfull的url接口
    // @PathVariable:把url模板参数({itemId})取出来绑定到它注解的形参上
    @RequestMapping(value = "/item/{itemId}")
    @ResponseBody
    public TbItem getIItemById(@PathVariable Long itemId) {
        TbItem itemById = itemService.getItemById(itemId);
        return itemById;
    }
}
