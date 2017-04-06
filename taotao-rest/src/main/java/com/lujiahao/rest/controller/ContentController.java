package com.lujiahao.rest.controller;

import com.lujiahao.common.pojo.TaotaoResult;
import com.lujiahao.common.utils.ExceptionUtil;
import com.lujiahao.mapping.pojo.TbContent;
import com.lujiahao.rest.service.ContentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 内容服务
 *
 * @author lujiahao
 * @version V1.0
 * @email xinruodingshui@gmail.com
 * @create 2016-09-12 15:39
 */
@Controller
@RequestMapping(value = "/content")
public class ContentController {
    @Autowired
    private ContentService contentService;

    /**
     * 根据内容分类id查询首页广告轮播图信息
     * @param contentCategoryId
     * @return
     */
    @RequestMapping(value = "/list/{contentCategoryId}")
    @ResponseBody
    public TaotaoResult getContentCategoryList(@PathVariable Long contentCategoryId) {
        try {
            List<TbContent> contentList = contentService.getContentList(contentCategoryId);
            if (contentList != null && contentList.size() > 0) {
                return TaotaoResult.ok(contentList);
            } else {
                return TaotaoResult.build(404,"无数据");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }
}
