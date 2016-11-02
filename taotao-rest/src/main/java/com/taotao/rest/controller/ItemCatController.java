package com.taotao.rest.controller;

import com.taotao.common.utils.JsonUtils;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 商品分类列表
 *
 * @author lujiahao
 * @version V1.0
 * @email jiahao.lu@qtparking.com
 * @create 2016-09-06 17:50
 */
@Controller
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;

//    @RequestMapping(value = "/itemcat/all",
//        produces = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
//    @ResponseBody
//    public String getItemCatList(String callback){
//        CatResult catResult = itemCatService.getItemCatList();
//        String json = JsonUtils.objectToJson(catResult);
//        return callback + "(" + json + ");";
//    }

    // 这个和上面的功能一样,这个方法是SpringMvc4.1之后的功能
    @RequestMapping(value = "/itemcat/all")
    @ResponseBody
    public Object getItemCatList(String callback){
        CatResult catResult = itemCatService.getItemCatList();
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(catResult);
        mappingJacksonValue.setJsonpFunction(callback);
        return mappingJacksonValue;
    }

}
