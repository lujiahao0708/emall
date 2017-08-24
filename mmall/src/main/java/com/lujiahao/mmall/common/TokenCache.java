package com.lujiahao.mmall.common;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author lujiahao
 * @version 1.0
 * @date 2017-07-31 23:29
 */
public class TokenCache {
    public static Logger LOGGER = LoggerFactory.getLogger(TokenCache.class);

    public static final String TOKEN_PREFIX = "token_";

    // LRU
    public static LoadingCache<String, String> localCache = CacheBuilder.newBuilder().initialCapacity(1000).maximumSize(10000).expireAfterAccess(12, TimeUnit.HOURS)
            .build(new CacheLoader<String, String>() {
                //默认的数据加载实现,当调用get取值的时候,如果key没有对应的值,就调用这个方法进行加载.
                @Override
                public String load(String s) throws Exception {
                    return "null";
                }
            });
    public static void setKey(String key,String value){
        localCache.put(key,value);
    }

    public static String getKey(String key){
        String value = null;
        try {
            value = localCache.get(key);
            if ("null".equals(value)) {
                return null;
            }
        } catch (Exception e){
            LOGGER.error("========== localCache get error ==========" + e.getMessage());
        }
        return null;
    }
}
