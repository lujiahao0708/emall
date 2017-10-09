package com.lujiahao.manager.controller;

import com.lujiahao.common.pojo.ContentNode;
import com.lujiahao.common.pojo.CommonResult;
import com.lujiahao.common.utils.HttpClientUtil;
import com.lujiahao.common.utils.JsonUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 内容分类控制器
 *
 * @author lujiahao
 * @version V1.0
 * @email jiahao.lu@qtparking.com
 * @create 2016-09-11 16:22
 */
@Controller
@RequestMapping(value = "/content/category")
public class ContentCategoryController {

    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${REST_CONTENT_CATEGORY_LIST}")
    private String REST_CONTENT_CATEGORY_LIST;
    @Value("${REST_CONTENT_CATEGORY_CREATE}")
    private String REST_CONTENT_CATEGORY_CREATE;
    @Value("${REST_CONTENT_CATEGORY_DELETE}")
    private String REST_CONTENT_CATEGORY_DELETE;
    @Value("${REST_CONTENT_CATEGORY_UPDATE}")
    private String REST_CONTENT_CATEGORY_UPDATE;

    /**
     * 网站内容管理
     *  内容分类管理
     * @param parentId
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public List<ContentNode> getContentCategoryList(
            @RequestParam(value = "id",defaultValue = "0") long parentId){
        Map<String,String> param = new HashMap<>();
        param.put("parentId",String.valueOf(parentId));

        String s = HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_CATEGORY_LIST, param);
        List<ContentNode> list = JsonUtils.jsonToList(s, ContentNode.class);
        return list;
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createContentCategory(long parentId, String name){
        Map<String,String> param = new HashMap<>();
        param.put("parentId",String.valueOf(parentId));
        param.put("name",name);
        String s = HttpClientUtil.doPost(REST_BASE_URL + REST_CONTENT_CATEGORY_CREATE, param);
        CommonResult taotaoResult = JsonUtils.jsonToPojo(s, CommonResult.class);
        return taotaoResult;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult deleteContentCategory(long parentId,long id){
        Map<String,String> param = new HashMap<>();
        param.put("parentId",String.valueOf(parentId));
        param.put("id",String.valueOf(id));
        String s = HttpClientUtil.doPost(REST_BASE_URL + REST_CONTENT_CATEGORY_DELETE, param);
        CommonResult taotaoResult = JsonUtils.jsonToPojo(s, CommonResult.class);
        return taotaoResult;
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateContentCategory(long id,String name){
        Map<String,String> param = new HashMap<>();
        param.put("id",String.valueOf(id));
        param.put("name",name);
        String s = HttpClientUtil.doPost(REST_BASE_URL + REST_CONTENT_CATEGORY_UPDATE, param);
        CommonResult taotaoResult = JsonUtils.jsonToPojo(s, CommonResult.class);
        return taotaoResult;
    }
}
