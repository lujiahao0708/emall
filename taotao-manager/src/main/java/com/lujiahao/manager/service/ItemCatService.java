package com.lujiahao.manager.service;


import com.lujiahao.mapping.pojo.TbItemCat;

import java.util.List;

/**
 * 商品类目选择功能
 *
 * @author lujiahao
 * @version V1.0
 * @email jiahao.lu@qtparking.com
 * @create 2016-09-01 10:49
 */
public interface ItemCatService {
    public List<TbItemCat> getItemCatList(Long parentId);
}
