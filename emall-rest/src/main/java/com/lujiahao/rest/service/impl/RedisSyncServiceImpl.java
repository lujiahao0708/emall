package com.lujiahao.rest.service.impl;

import com.lujiahao.common.pojo.CommonResult;
import com.lujiahao.common.utils.ExceptionUtil;
import com.lujiahao.rest.dao.JedisClientDao;
import com.lujiahao.rest.service.RedisSyncService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Redis同步服务
 * Created by lujiahao on 2016/9/23.
 */
@Service
public class RedisSyncServiceImpl implements RedisSyncService {

    @Value("${INDEX_CONTENT_REDIS_KEY}")
    private String INDEX_CONTENT_REDIS_KEY;

    @Autowired
    private JedisClientDao jedisClientDao;

    /**
     * 同步首页广告位轮播图缓存
     * @param contentCategoryId
     * @return
     */
    @Override
    public CommonResult syncContent(long contentCategoryId) {
        try {
            jedisClientDao.hdel(INDEX_CONTENT_REDIS_KEY, contentCategoryId + "");
        } catch (Exception e) {
            return CommonResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return CommonResult.ok();
    }
}
