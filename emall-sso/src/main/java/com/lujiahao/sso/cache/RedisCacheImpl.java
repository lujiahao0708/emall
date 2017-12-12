//package com.lujiahao.sso.cache;
//
//import com.lujiahao.common.domain.ServerResponse;
//import com.lujiahao.common.utils.JsonUtils;
//import com.lujiahao.sso.domain.Const;
//import com.lujiahao.sso.domain.pojo.User;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
///**
// * Redis实现缓存
// *
// * @author lujiahao
// * @version 1.0
// * @date 2017-10-20 17:33
// */
//public class RedisCacheImpl<T> implements ILocalCache<T> {
//    public static final Logger LOGGER = LoggerFactory.getLogger(RedisCacheImpl.class);
//
//    @Autowired
//    private JedisClientDao jedisClientDao;
//
//    @Override
//    public boolean setCache(String key, T value) {
//        // 这里采用接口编程的方式,到底用redis还是用guava
//        // 把用户信息写入redis
//        jedisClientDao.set(Const.CACHE_TOKEN + ":" + key, JsonUtils.objectToJson(value));
//        // 设置session过期时间
//        jedisClientDao.expire(Const.CACHE_TOKEN + ":" + key, Const.CACHE_TOKEN_EXPIRE);
//        return true;
//    }
//
//    @Override
//    public boolean cleanCache(String key) {
//        try {
//            // 根据token从redis中查询用户信息
//            String json = jedisClientDao.get(Const.CACHE_TOKEN + ":" + key);
//            if (StringUtils.isNoneBlank(json)) {
//                // 更新过期时间--清除key
//                jedisClientDao.expire(Const.CACHE_TOKEN + ":" + key, 0);
//            }
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    @Override
//    public Object getCache(String key) {
//        try {
//            // 根据token从redis中查询用户信息
//            String json = jedisClientDao.get(Const.CACHE_TOKEN + ":" + key);
//            if (StringUtils.isBlank(json)) {
//                return ServerResponse.build(400, "此Session已经过期,请重新登录");
//            }
//            // 更新过期时间
//            jedisClientDao.expire(Const.CACHE_TOKEN + ":" + key, Const.CACHE_TOKEN_EXPIRE);
//            // 返回用户信息
//            User emallUser = JsonUtils.jsonToPojo(json, User.class);
//            return ServerResponse.success(emallUser);
//        } catch (Exception e) {
//            return ServerResponse.error("无法获取用户信息");
//        }
//    }
//}
