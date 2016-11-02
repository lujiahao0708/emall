package com.taotao.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

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
