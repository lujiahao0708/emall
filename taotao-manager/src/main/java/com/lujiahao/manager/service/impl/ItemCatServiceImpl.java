package com.lujiahao.manager.service.impl;


import com.lujiahao.manager.service.ItemCatService;
import com.lujiahao.mapping.mapper.TbItemCatMapper;
import com.lujiahao.mapping.pojo.TbItemCat;
import com.lujiahao.mapping.pojo.TbItemCatExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品类目选择
 *
 * @author lujiahao
 * @version V1.0
 * @email jiahao.lu@qtparking.com
 * @create 2016-09-01 10:51
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Override
    public List<TbItemCat> getItemCatList(Long parentId) {
        TbItemCatExample example = new TbItemCatExample();
        // 设置查询条件
        TbItemCatExample.Criteria criteria = example.createCriteria();
        // 根据parentId查询子节点
        criteria.andParentIdEqualTo(parentId);
        // 返回子节点列表
        List<TbItemCat> list = itemCatMapper.selectByExample(example);
        return list;
    }
}
