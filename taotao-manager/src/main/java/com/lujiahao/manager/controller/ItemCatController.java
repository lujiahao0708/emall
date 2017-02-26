package com.lujiahao.manager.controller;

import com.lujiahao.manager.service.ItemCatService;
import com.lujiahao.mapping.pojo.TbItemCat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品类目控制器
 * Created by ljh on 2017/2/26.
 */
@Controller
@RequestMapping(value = "/item/cat")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    /**
     * 根据商品类目的父节点id获取商品类目列表
     * @param parentId
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public List categoryList(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
        List catList = new ArrayList();
        List<TbItemCat> itemCatList = itemCatService.getItemCatList(parentId);
        for (TbItemCat tbItemCat : itemCatList) {
            Map<String, Object> node = new HashMap<String, Object>();
            node.put("id", tbItemCat.getId());
            node.put("text", tbItemCat.getName());
            node.put("state", tbItemCat.getIsParent() ? "closed" : "open");
            catList.add(node);
        }
        return catList;
    }
}
