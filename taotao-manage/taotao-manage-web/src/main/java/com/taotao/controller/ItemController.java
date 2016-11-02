package com.taotao.controller;

import com.taotao.common.pojo.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
/**
 * 商品管理controller
 * @author lujiahao
 *
 */
@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	// 这是支持restfull的url接口 
	// @PathVariable:把url模板参数({itemId})取出来绑定到它注解的形参上
	@RequestMapping(value="/item/{itemId}")
	@ResponseBody
	public TbItem getIItemById(@PathVariable Long itemId){
		TbItem itemById = itemService.getItemById(itemId);
		return itemById;
	}

	/**
	 * 获得商品列表
     */
	@RequestMapping(value="/item/list")
	@ResponseBody
	public EUDataGridResult getItemList(Integer page,Integer rows){
		EUDataGridResult result = itemService.getItemList(page, rows);
		return result;
	}

    /**
     * 添加商品条目
     */
    @RequestMapping(value = "/item/save",method = RequestMethod.POST)
    @ResponseBody
    private TaotaoResult createItem(TbItem item,String desc) throws Exception{
        TaotaoResult result = itemService.createItem(item,desc);
        return result;
    }
	

}
