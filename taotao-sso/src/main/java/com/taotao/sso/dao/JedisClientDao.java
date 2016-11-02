package com.taotao.sso.dao;

/**
 *
 * Created by lujiahao on 2016/9/23.
 */
public interface JedisClientDao {
    /**
     * 获取String
     * @param key
     * @return
     */
    String get(String key);

    /**
     * 存储String
     * @param key
     * @param value
     * @return
     */
    String set(String key, String value);

    /**
     * 获取Hash
     * @param hkey
     * @param key
     * @return
     */
    String hget(String hkey, String key);

    /**
     * 设置Hash
     * @param hkey
     * @param key
     * @param value
     * @return
     */
    long hset(String hkey, String key, String value);

    /**
     * 自增
     * @param key
     * @return
     */
    long incr(String key);

    /**
     * 设置过期时间
     * @param key
     * @param second
     * @return
     */
    long expire(String key, int second);

    /**
     * 查询过期时间
     * @param key
     * @return
     */
    long ttl(String key);

    /**
     * 删除一个String类型的key
     * @param key
     * @return
     */
    long del(String key);

    /**
     * 删除一个Hash类型的key
     * @param hkey
     * @param key
     * @return
     */
    long hdel(String hkey, String key);
}
