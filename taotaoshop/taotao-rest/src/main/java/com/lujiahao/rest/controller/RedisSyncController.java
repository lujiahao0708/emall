package com.lujiahao.rest.controller;

import com.lujiahao.common.pojo.TaotaoResult;
import com.lujiahao.rest.service.RedisSyncService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Redis缓存同步的Controller
 * Created by lujiahao on 2016/9/23.
 */
@Controller
@RequestMapping("/cache/sync")
public class RedisSyncController {

    @Autowired
    private RedisSyncService redisSyncService;

    /**
     * 同步首页广告轮播图缓存
     * @param contentCategoryId
     * @return
     */
    @RequestMapping("/content/{contentCategoryId}")
    @ResponseBody
    public TaotaoResult contentCacheSync(@PathVariable Long contentCategoryId){
        TaotaoResult result = redisSyncService.syncContent(contentCategoryId);
        return result;
    }
}
