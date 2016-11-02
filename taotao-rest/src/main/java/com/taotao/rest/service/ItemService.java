package com.taotao.rest.service;

import com.taotao.common.pojo.TaotaoResult;

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
    TaotaoResult getItemBaseInfo(long itemId);

    /**
     * 根据商品id获取商品描述信息
     * @param itemId 商品id
     * @return
     */
    TaotaoResult getItemDesc(long itemId);

    /**
     * 根据商品id获取商品的规格参数
     * @param itemId 商品id
     * @return
     */
    TaotaoResult getItemParam(long itemId);
}
