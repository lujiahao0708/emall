package com.lujiahao.sso.utils.cache;

import com.lujiahao.common.domain.ServerResponse;
import com.lujiahao.common.utils.JsonUtils;
import com.lujiahao.mapping.pojo.EmallUser;
import com.lujiahao.sso.dao.JedisClientDao;
import com.lujiahao.sso.utils.ILocalCache;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * Redis实现缓存
 *
 * @author lujiahao
 * @version 1.0
 * @date 2017-10-20 17:33
 */
public class RedisCacheImpl<T> implements ILocalCache<T> {
    public static final Logger LOGGER = LoggerFactory.getLogger(RedisCacheImpl.class);

    @Autowired
    private JedisClientDao jedisClientDao;

    @Value("${REDIS_USER_SESSION_KEY}")
    private String REDIS_USER_SESSION_KEY;
    @Value("${SSO_SESSION_EXPIRE}")
    private Integer SSO_SESSION_EXPIRE;

    @Override
    public boolean setCache(String key, T value) {
        // 这里采用接口编程的方式,到底用redis还是用guava
        // 把用户信息写入redis
        jedisClientDao.set(REDIS_USER_SESSION_KEY + ":" + key, JsonUtils.objectToJson(value));
        // 设置session过期时间
        jedisClientDao.expire(REDIS_USER_SESSION_KEY + ":" + key, SSO_SESSION_EXPIRE);
        return true;
    }

    @Override
    public boolean cleanCache(String key) {
        try {
            // 根据token从redis中查询用户信息
            String json = jedisClientDao.get(REDIS_USER_SESSION_KEY + ":" + key);
            if (StringUtils.isNoneBlank(json)) {
                // 更新过期时间--清除key
                jedisClientDao.expire(REDIS_USER_SESSION_KEY + ":" + key, 0);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public T getCache(String key) {
        try {
            // 根据token从redis中查询用户信息
            String json = jedisClientDao.get(REDIS_USER_SESSION_KEY + ":" + key);
            if (StringUtils.isBlank(json)) {
                return ServerResponse.build(400, "此Session已经过期,请重新登录");
            }
            // 更新过期时间
            jedisClientDao.expire(REDIS_USER_SESSION_KEY + ":" + key, SSO_SESSION_EXPIRE);
            // 返回用户信息
            EmallUser emallUser = JsonUtils.jsonToPojo(json, EmallUser.class);
            return ServerResponse.success(emallUser);
        } catch (Exception e) {
            return ServerResponse.error("无法获取用户信息");
        }
    }
}
