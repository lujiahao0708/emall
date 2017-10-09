package com.lujiahao.manager.service;


import com.lujiahao.common.pojo.CommonResult;
import com.lujiahao.mapping.pojo.TbContent;

/**
 * 内容管理服务
 * Created by lujiahao on 2016/9/12.
 */
public interface ContentService {
    /**
     * 添加首页广告轮播图内容
     * @param tbContent
     * @return
     */
    CommonResult insertContent(TbContent tbContent);
}
