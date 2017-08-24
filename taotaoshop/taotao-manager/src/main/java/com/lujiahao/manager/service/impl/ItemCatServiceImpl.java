package com.lujiahao.manager.service.impl;

import com.lujiahao.manager.service.ItemCatService;
import com.lujiahao.mapping.mapper.TbItemCatMapper;
import com.lujiahao.mapping.pojo.TbItemCat;
import com.lujiahao.mapping.pojo.TbItemCatExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ljh on 2017/2/26.
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Override
    public List<TbItemCat> getItemCatList(Long parentId) {
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> tbItemCatList = itemCatMapper.selectByExample(example);

        return tbItemCatList;
    }
}
