package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

/**
 * 
 * @author lujiahao
 *
 */
public interface ItemService {
	/**
	 * 通过商品id查询商品
	 */
	TbItem getItemById(long itemId);
	
	/**
	 * 返回商品列表-带分页的
	 */
	EUDataGridResult getItemList(int page,int rows);

    /**
     * 添加商品
     */
	TaotaoResult createItem(TbItem item,String desc) throws Exception;
}
