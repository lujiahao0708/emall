package com.lujiahao.rest.service;


import com.lujiahao.common.pojo.TaotaoResult;

/**
 * 操作Redis缓存的服务
 * Created by lujiahao on 2016/9/23.
 */
public interface RedisService {
    /**
     * 同步缓存
     * @param contentId
     * @return
     */
    TaotaoResult syncContent(long contentId);
}
