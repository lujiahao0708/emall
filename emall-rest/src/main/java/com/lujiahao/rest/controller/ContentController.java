package com.lujiahao.rest.controller;


import com.lujiahao.common.domain.ContentNode;
import com.lujiahao.common.domain.ServerResponse;
import com.lujiahao.common.utils.ExceptionUtil;
import com.lujiahao.mapping.pojo.TbContent;
import com.lujiahao.rest.service.ContentCategoryService;

import com.lujiahao.rest.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 内容分类
 * Created by lujiahao on 2016/9/11.
 */
@Controller
@RequestMapping(value = "/content")
public class ContentController {
    @Autowired
    private ContentCategoryService contentCategoryService;

    @Autowired
    private ContentService contentService;

    /**
     * 内容分类列表
     * 用于CMS管理系统的接口  管理tb_content_category和tb_content表中的数据
     */
    @RequestMapping(value = "/category/list")
    @ResponseBody
    public List<ContentNode> getContentCategoryList(
            @RequestParam(value = "parentId", defaultValue = "0") long parentId) {
        List<ContentNode> list = contentCategoryService.getContentCategoryList(parentId);
        return list;
    }

    @RequestMapping(value = "/category/create", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse createContentCategory(long parentId, String name) {
        ServerResponse serverResponse = contentCategoryService.insertContentCategory(parentId, name);
        return serverResponse;
    }

    @RequestMapping(value = "/category/delete", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse deleteContentCategory(long parentId, long id) {
        ServerResponse serverResponse = contentCategoryService.deleteContentCategory(parentId, id);
        return serverResponse;
    }

    @RequestMapping(value = "/category/update", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse updateContentCategory(long id, String name) {
        ServerResponse serverResponse = contentCategoryService.updateContentCategory(id, name);
        return serverResponse;
    }

    /**
     * 根据内容分类id查询首页广告轮播图信息
     */
    @RequestMapping(value = "/list/{contentCategoryId}")
    @ResponseBody
    public ServerResponse getContentCategoryList(@PathVariable Long contentCategoryId) {
        try {
            List<TbContent> contentList = contentService.getContentList(contentCategoryId);
            if (contentList != null && contentList.size() > 0) {
                return ServerResponse.success(contentList);
            } else {
                return ServerResponse.build(404, "无数据");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.build(500, ExceptionUtil.getStackTrace(e));
        }
    }
}
