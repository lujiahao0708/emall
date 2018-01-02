package com.lujiahao.sso.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Guava实现缓存
 *
 * @author lujiahao
 * @version 1.0
 * @date 2017-10-20 17:33
 */
@Component
public class GuavaCacheImpl<T> implements ILocalCache<T> {
    public static final Logger LOGGER = LoggerFactory.getLogger(GuavaCacheImpl.class);

    /**LRU算法*/
    private static LoadingCache<String, Object> localCache = CacheBuilder.newBuilder().initialCapacity(1000)
            .maximumSize(10000).expireAfterAccess(12, TimeUnit.HOURS)
            .build(new CacheLoader<String, Object>() {
                // 默认的数据加载实现,当调用get取值是,如果没有key,就执行这个方法
                @Override
                public Object load(String s) throws Exception {
                    return null;
                }
            });

    @Override
    public boolean setCache(String key, T value) {
        try {
            LOGGER.info("Guava本地缓存 存储 key:{} value:{}", key, value);
            localCache.put(key, value);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean cleanCache(String key) {
        try {
            localCache.invalidate(key);
            LOGGER.info("Guava本地缓存 清除 key:{}", key);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Object getCache(String key) {
        try {
            Object value = localCache.get(key);
            LOGGER.info("Guava本地缓存 获取 key:{} value:{}", key, value);
            return value;
        } catch (Exception e) {
            LOGGER.error("========== localCache get error ==========", e);
        }
        return null;
    }
}
