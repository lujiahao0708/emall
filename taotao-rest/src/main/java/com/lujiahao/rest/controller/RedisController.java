package com.lujiahao.rest.controller;

import com.lujiahao.common.pojo.TaotaoResult;
import com.lujiahao.rest.service.RedisService;

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
public class RedisController {

    @Autowired
    private RedisService redisService;

    @RequestMapping("/content/{contentCid}")
    @ResponseBody
    public TaotaoResult contentCacheSync(@PathVariable Long contentCid){
        TaotaoResult result = redisService.syncContent(contentCid);
        return result;
    }
}
