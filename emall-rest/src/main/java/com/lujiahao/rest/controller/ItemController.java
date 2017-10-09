package com.lujiahao.rest.controller;

import com.lujiahao.common.pojo.CommonResult;
import com.lujiahao.rest.pojo.CatResult;
import com.lujiahao.rest.service.ItemCatService;
import com.lujiahao.rest.service.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 商品分类 & 商品信息
 * Created by lujiahao on 2016/10/30.
 */
@Controller
@RequestMapping(value = "/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemCatService itemCatService;

    /**
     * 商品分类列表
     */
//    @RequestMapping(value = "/category/all",
//        produces = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
//    @ResponseBody
//    public String getItemCatList(String callback){
//        CatResult catResult = itemCatService.getItemCatList();
//        String json = JsonUtils.objectToJson(catResult);
//        return callback + "(" + json + ");";
//    }

    // 这个和上面的功能一样,这个方法是SpringMvc4.1之后的功能
    // 调用的地方是 portal里面 lib-v1.js中1173行
    @RequestMapping(value = "/category/all")
    @ResponseBody
    public Object getItemCatList(String callback) {
        CatResult catResult = itemCatService.getItemCatList();
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(catResult);
        mappingJacksonValue.setJsonpFunction(callback);
        return mappingJacksonValue;
    }

    /**
     * 根据Id查询商品的基本信息
     */
    @RequestMapping(value = "/info/{itemId}")
    @ResponseBody
    public CommonResult getItemBaseInfo(@PathVariable Long itemId) {
        CommonResult commonResult = itemService.getItemBaseInfo(itemId);
        return commonResult;
    }

    /**
     * 根据商品id获取商品描述信息
     */
    @RequestMapping(value = "/desc/{itemId}")
    @ResponseBody
    public CommonResult getItemDesc(@PathVariable Long itemId) {
        CommonResult commonResult = itemService.getItemDesc(itemId);
        return commonResult;
    }

    /**
     * 根据商品id获取商品规格参数
     */
    @RequestMapping(value = "/param/{itemId}")
    @ResponseBody
    public CommonResult getItemParam(@PathVariable Long itemId) {
        CommonResult commonResult = itemService.getItemParam(itemId);
        return commonResult;
    }
}
