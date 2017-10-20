package com.lujiahao.rest.service.impl;

import com.lujiahao.common.domain.ServerResponse;
import com.lujiahao.common.utils.ExceptionUtil;
import com.lujiahao.common.utils.JsonUtils;
import com.lujiahao.rest.dao.JedisClientDao;
import com.lujiahao.rest.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lujiahao on 2016/10/30.
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private TbItemDescMapper itemDescMapper;

    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;

    @Autowired
    private JedisClientDao jedisClientDao;

    @Value("${REDIS_ITEM_KEY}")
    private String REDIS_ITEM_KEY;
    @Value("${REDIS_ITEM_EXPIRE}")
    private Integer REDIS_ITEM_EXPIRE;

    /**
     * 根据商品id获取商品基本信息
     * @param itemId 商品id
     * @return
     */
    @Override
    public ServerResponse getItemBaseInfo(long itemId) {
        try {
            // 添加缓存读取逻辑
            // 从缓存中取商品信息,商品id对应的信息
            String json = jedisClientDao.get(REDIS_ITEM_KEY + ":" + itemId + ":base");
            // 判断json中是否有值
            if (!StringUtils.isBlank(json)) {
                // 把json转换成java对象
                TbItem tbItem = JsonUtils.jsonToPojo(json, TbItem.class);
                System.out.println("使用缓存-->"+tbItem.toString());
                return ServerResponse.success(tbItem);
            }
        } catch (Exception e){
            ExceptionUtil.getStackTrace(e);
        }

        // 根据id查询商品基本信息
        TbItem tbItem = itemMapper.selectByPrimaryKey(itemId);

        // 添加缓存写入逻辑----应该加上trycatch,不应该影响主体功能
        try {
            // 把商品信息写入缓存中
            jedisClientDao.set(REDIS_ITEM_KEY + ":" +itemId + ":base", JsonUtils.objectToJson(tbItem));
            // 设置key的有效期
            jedisClientDao.expire(REDIS_ITEM_KEY + ":" +itemId + ":base",REDIS_ITEM_EXPIRE);
            System.out.println("写入缓存-->"+tbItem.toString());
        } catch (Exception e){
            ExceptionUtil.getStackTrace(e);
        }
        // 使用TaotaoResult包装一下
        return ServerResponse.success(tbItem);
    }

    /**
     * 根据商品id获取商品描述信息
     * @param itemId 商品id
     * @return
     */
    @Override
    public ServerResponse getItemDesc(long itemId) {
        // 读取缓存逻辑
        try {
            String json = jedisClientDao.get(REDIS_ITEM_KEY + ":" + itemId + ":desc");
            if (!StringUtils.isBlank(json)) {
                TbItemDesc tbItemDesc = JsonUtils.jsonToPojo(json, TbItemDesc.class);
                return ServerResponse.success(tbItemDesc);
            }
        } catch (Exception e){
            ExceptionUtil.getStackTrace(e);
        }
        // 从数据库中读取
        TbItemDesc tbItemDesc = itemDescMapper.selectByPrimaryKey(itemId);
        // 添加缓存逻辑
        try {
            // 将商品描述信息存入redis中
            jedisClientDao.set(REDIS_ITEM_KEY + ":" +itemId + ":desc",JsonUtils.objectToJson(tbItemDesc));
            // 设置过期时间
            jedisClientDao.expire(REDIS_ITEM_KEY + ":" +itemId + ":desc",REDIS_ITEM_EXPIRE);
        } catch (Exception e){
            ExceptionUtil.getStackTrace(e);
        }
        return ServerResponse.success(tbItemDesc);
    }

    /**
     * 根据商品id获取商品的规格参数
     * @param itemId 商品id
     * @return
     */
    @Override
    public ServerResponse getItemParam(long itemId) {
        // 读取缓存逻辑
        try {
            String json = jedisClientDao.get(REDIS_ITEM_KEY + ":" + itemId + ":param");
            if (!StringUtils.isBlank(json)) {
                TbItemParamItem paramItem = JsonUtils.jsonToPojo(json, TbItemParamItem.class);
                return ServerResponse.success(paramItem);
            }
        } catch (Exception e){
            ExceptionUtil.getStackTrace(e);
        }

        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);// TODO 这里为啥用这个不知道啊
        if (list != null && list.size() > 0) {
            TbItemParamItem paramItem = list.get(0);
            // 添加缓存逻辑
            try {
                // 将数据缓存到redis
                jedisClientDao.set(REDIS_ITEM_KEY + ":" +itemId + ":param",JsonUtils.objectToJson(paramItem));
                // 设置数据过期时间
                jedisClientDao.expire(REDIS_ITEM_KEY + ":" +itemId + ":param",REDIS_ITEM_EXPIRE);
            } catch (Exception e){
                ExceptionUtil.getStackTrace(e);
            }
            return ServerResponse.success(paramItem);
        }
        return ServerResponse.build(400,"该商品没有规格信息");
    }
}
