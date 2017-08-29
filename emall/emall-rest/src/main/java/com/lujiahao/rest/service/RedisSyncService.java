package com.lujiahao.rest.service;


import com.lujiahao.common.pojo.TaotaoResult;

/**
 * 操作Redis缓存的服务
 * Created by lujiahao on 2016/9/23.
 */
public interface RedisSyncService {
    /**
     * 同步首页广告位轮播图缓存
     * @param contentCategoryId
     * @return
     */
    TaotaoResult syncContent(long contentCategoryId);
}
