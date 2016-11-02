package com.taotao.controller;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping(value = "/list")
    @ResponseBody
    public List<EasyUITreeNode> getContentCategoryList(
            @RequestParam(value = "id",defaultValue = "0") long parentId){
        List<EasyUITreeNode> list = contentCategoryService.getContentCategoryList(parentId);
        return list;
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult createContentCategory(long parentId,String name){
        TaotaoResult taotaoResult = contentCategoryService.insertContentCategory(parentId, name);
        return taotaoResult;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult deleteContentCategory(long parentId,long id){
        TaotaoResult taotaoResult = contentCategoryService.deleteContentCategory(parentId, id);
        return taotaoResult;
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult updateContentCategory(long id,String name){
        TaotaoResult taotaoResult = contentCategoryService.updateContentCategory(id, name);
        return taotaoResult;
    }
}
