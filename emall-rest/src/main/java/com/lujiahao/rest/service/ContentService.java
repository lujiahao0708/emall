package com.lujiahao.rest.service;


import java.util.List;

/**
 * 内容服务
 * Created by lujiahao on 2016/9/12.
 */
public interface ContentService {
    /**
     * 根据categoryId查询内容列表
     */
    List<TbContent> getContentList(long categoryId);
}
