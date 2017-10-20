package com.lujiahao.sso.utils.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.lujiahao.sso.utils.ILocalCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Guava实现缓存
 *
 * @author lujiahao
 * @version 1.0
 * @date 2017-10-20 17:33
 */
public class GuavaCacheImpl<T> implements ILocalCache<T> {
    public static final Logger LOGGER = LoggerFactory.getLogger(GuavaCacheImpl.class);

    // LRU算法
    private static LoadingCache<String, String> localCache = CacheBuilder.newBuilder().initialCapacity(1000)
            .maximumSize(10000).expireAfterAccess(12, TimeUnit.HOURS)
            .build(new CacheLoader<String, String>() {
                // 默认的数据加载实现,当调用get取值是,如果没有key,就执行这个方法
                @Override
                public String load(String s) throws Exception {
                    return "null";
                }
            });

    @Override
    public boolean setCache(String key, T value) {
        localCache.put(key, value);
        return true;
    }

    @Override
    public boolean cleanCache(String key) {
        try {

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public T getCache(String key) {
        String value = null;
        try {
            value = localCache.get(key);
            if ("null".equals(value)) {
                return null;
            }
            return value;
        } catch (Exception e) {
            LOGGER.error("========== localCache get error ==========", e);
        }
        return null;
    }
}
