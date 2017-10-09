package com.lujiahao.rest.service;


import com.lujiahao.common.pojo.CommonResult;

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
    CommonResult syncContent(long contentCategoryId);
}
