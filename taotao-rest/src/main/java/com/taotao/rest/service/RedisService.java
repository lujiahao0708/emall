package com.taotao.rest.service;

import com.taotao.common.pojo.TaotaoResult;

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
