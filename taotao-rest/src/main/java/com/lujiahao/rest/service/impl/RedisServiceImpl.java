package com.lujiahao.rest.service.impl;

import com.lujiahao.common.pojo.TaotaoResult;
import com.lujiahao.common.utils.ExceptionUtil;
import com.lujiahao.rest.dao.JedisClientDao;
import com.lujiahao.rest.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by lujiahao on 2016/9/23.
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Value("${INDEX_CONTENT_REDIS_KEY}")
    private String INDEX_CONTENT_REDIS_KEY;

    @Autowired
    private JedisClientDao jedisClientDao;

    @Override
    public TaotaoResult syncContent(long contentId) {
        try {
        jedisClientDao.hdel(INDEX_CONTENT_REDIS_KEY, contentId + "");
        } catch (Exception e){
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return TaotaoResult.ok();
    }
}
