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
 * 商品类目选择的Controller
 *
 * @author lujiahao
 * @version V1.0
 * @email jiahao.lu@qtparking.com
 * @create 2016-09-01 10:56
 */
@Controller
@RequestMapping(value = "/item/cat")
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping(value = "/list")
    @ResponseBody
    public List categoryList(@RequestParam(value = "id",defaultValue = "0") Long parentId){
        List catList = new ArrayList();
        // 查询数据库
        List<TbItemCat> list = itemCatService.getItemCatList(parentId);
        for (TbItemCat tbItemCat : list) {
            Map node = new HashMap();
            node.put("id",tbItemCat.getId());
            node.put("text",tbItemCat.getName());
            // 如果是父节点的话就设置成关闭状态,如果是叶子节点就是open状态
            node.put("state",tbItemCat.getIsParent()?"closed":"open");
            catList.add(node);
        }
        return catList;
    }
}
