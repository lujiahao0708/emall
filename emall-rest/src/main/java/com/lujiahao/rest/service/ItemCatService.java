package com.lujiahao.rest.service;


import com.lujiahao.rest.pojo.CatResult;

/**
 * 商品分类列表查询
 *
 * @author lujiahao
 * @version V1.0
 * @create 2016-09-06 17:39
 */
public interface ItemCatService {

    /**
     * 获取商品分类列表
     */
    CatResult getItemCatList();
}
