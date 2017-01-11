package com.lujiahao.manager.service;


import com.lujiahao.common.pojo.TaotaoResult;
import com.lujiahao.mapping.pojo.TbContent;

/**
 * 内容管理服务
 * Created by lujiahao on 2016/9/12.
 */
public interface ContentService {
    /**
     * 添加内容
     */
    TaotaoResult insertContent(TbContent tbContent);
}
