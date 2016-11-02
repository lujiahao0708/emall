package com.taotao.rest.service;

import com.taotao.rest.pojo.CatResult;

/**
 * 商品分类列表查询
 *
 * @author lujiahao
 * @version V1.0
 * @email jiahao.lu@qtparking.com
 * @create 2016-09-06 17:39
 */
public interface ItemCatService {

    /**
     * 获取商品分类列表
     */
    CatResult getItemCatList();
}
