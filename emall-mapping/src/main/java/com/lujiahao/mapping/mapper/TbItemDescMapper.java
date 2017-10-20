package com.lujiahao.mapping.mapper;

import com.lujiahao.mapping.pojo.TbItemDesc;
import java.util.List;

public interface TbItemDescMapper {
    int deleteByPrimaryKey(Long itemId);

    int insert(TbItemDesc record);

    TbItemDesc selectByPrimaryKey(Long itemId);

    List<TbItemDesc> selectAll();

    int updateByPrimaryKey(TbItemDesc record);
}