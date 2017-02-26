package com.lujiahao.manager.service;

import com.lujiahao.mapping.pojo.TbItemCat;

import java.util.List;

/**
 * 商品分类的Service
 * Created by ljh on 2017/2/26.
 */
public interface ItemCatService {

    /**
     * 根据商品分类的父节点获取商品分类列表
     * @param parentId
     * @return
     */
    List<TbItemCat> getItemCatList(Long parentId);
}
