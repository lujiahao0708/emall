package com.lujiahao.rest.service;


import com.lujiahao.common.pojo.CommonResult;

/**
 * 查询商品信息
 * Created by lujiahao on 2016/10/30.
 */
public interface ItemService {
    /**
     * 根据商品id获取商品基本信息
     * @param itemId 商品id
     * @return
     */
    CommonResult getItemBaseInfo(long itemId);

    /**
     * 根据商品id获取商品描述信息
     * @param itemId 商品id
     * @return
     */
    CommonResult getItemDesc(long itemId);

    /**
     * 根据商品id获取商品的规格参数
     * @param itemId 商品id
     * @return
     */
    CommonResult getItemParam(long itemId);
}
