package com.lujiahao.manager.service;


import com.lujiahao.common.pojo.EUDataGridResult;
import com.lujiahao.common.pojo.TaotaoResult;
import com.lujiahao.mapping.pojo.TbItem;

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
	EUDataGridResult getItemList(int page, int rows);

    /**
     * 添加商品
     */
	TaotaoResult createItem(TbItem item, String desc) throws Exception;
}
