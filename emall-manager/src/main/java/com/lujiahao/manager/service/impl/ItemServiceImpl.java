package com.lujiahao.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lujiahao.common.pojo.EUDataGridResult;
import com.lujiahao.common.pojo.TaotaoResult;
import com.lujiahao.common.utils.IDUtils;
import com.lujiahao.manager.service.ItemService;
import com.lujiahao.mapping.mapper.TbItemDescMapper;
import com.lujiahao.mapping.mapper.TbItemMapper;
import com.lujiahao.mapping.pojo.TbItem;
import com.lujiahao.mapping.pojo.TbItemDesc;
import com.lujiahao.mapping.pojo.TbItemExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 商品管理service
 * @author lujiahao
 *
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;

    @Autowired
    private TbItemDescMapper itemDescMapper;

    /**
     * 获取所有的商品信息
     * pagehelper对逆向工程生成的代码支持不好,不能对有查询条件的查询分页,会抛异常
     * @param page 页码
     * @param rows 每页显示行数
     * @return
     */
    @Override
    public List<TbItem> getAllItem(int page, int rows) {
        TbItemExample example = new TbItemExample();
        PageHelper.startPage(page,rows);
        List<TbItem> tbItemList = itemMapper.selectByExample(example);
        PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(tbItemList);
        return tbItemList;
    }

	@Override
	public TbItem getItemById(long itemId) {
//		TbItem item = itemMapper.selectByPrimaryKey(itemId);
		
		// 添加查询条件
		TbItemExample example = new TbItemExample();
		TbItemExample.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> list = itemMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			TbItem tbItem = list.get(0);
			return tbItem;
		}
		return null;
	}

	/**
	 * 返回商品列表-带分页的
	 */
	@Override
	public EUDataGridResult getItemList(int page, int rows) {
		// 查询商品列表
		TbItemExample example = new TbItemExample();
		// 分页处理
		PageHelper.startPage(page, rows);
		List<TbItem> list = itemMapper.selectByExample(example);
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

    /**
     * 添加商品条目
     */
	@Override
	public TaotaoResult createItem(TbItem item, String desc) throws Exception{
		// item补全
		// 生成商品id
		Long itemId = IDUtils.genItemId();
		item.setId(itemId);
		item.setStatus((byte) 1);// 商品状态 1:正常 2:下架 3:删除
		item.setCreated(new Date());
		item.setUpdated(new Date());
		// 插入数据库
		itemMapper.insert(item);
        // 添加商品描述
        TaotaoResult taotaoResult = insertItemDesc(itemId, desc);
        if (taotaoResult.getStatus() != 200) {
            throw new Exception();// 这里跑出异常是为了让spring自动回滚事务
        }
        return TaotaoResult.ok();
	}
    /**
     * 添加商品描述
     */
    private TaotaoResult insertItemDesc(Long itemId,String desc){
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());
        itemDescMapper.insert(itemDesc);
        return TaotaoResult.ok();
    }

}
