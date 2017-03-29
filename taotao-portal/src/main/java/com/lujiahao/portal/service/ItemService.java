package com.lujiahao.portal.service;

import com.lujiahao.portal.pojo.ItemInfo;

/**
 * 商品信息查询
 * Created by lujiahao on 2016/10/31.
 */
public interface ItemService {
    /**
     * 根据商品id查询商品基本信息
     * @param itemId
     * @return
     */
    ItemInfo getItemById(Long itemId);

    /**
     * 通过商品id查询商品的描述信息
     * @param itemId
     * @return
     */
    String getItemDescById(Long itemId);

    /**
     * 根据商品id查询商品规格参数
     * @param itemId
     * @return
     */
    String getItemParamById(Long itemId);
}
