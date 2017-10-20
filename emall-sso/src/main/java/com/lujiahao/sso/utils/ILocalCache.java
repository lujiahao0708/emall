package com.lujiahao.sso.utils;

/**
 * 本地缓存
 *
 * @author lujiahao
 * @version 1.0
 * @date 2017-10-20 17:24
 */
public interface ILocalCache<T> {
    /**
     * 设置缓存
     * @param key
     * @param value
     * @return
     */
    boolean setCache(String key, T value);

    /**
     * 删除缓存
     * @param key
     * @return
     */
    boolean cleanCache(String key);

    /**
     * 获取缓存
     * @param key
     * @return
     */
    T getCache(String key);


//=============V1.0==================
//    /**
//     * 存储
//     * @param key
//     * @param value
//     */
//    boolean setCache(String key, T value);
//
//    /**
//     * 删除缓存
//     * @param key
//     */
//    boolean cleanCache(String key);
//
//    /**
//     * 根据key获取缓存
//     * @param key
//     */
//    T getCache(String key);
}
