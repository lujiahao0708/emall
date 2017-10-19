package com.lujiahao.rest.service;


import com.lujiahao.common.domain.ContentNode;
import com.lujiahao.common.domain.ServerResponse;

import java.util.List;

/**
 * 内容分类服务
 * Created by lujiahao on 2016/9/11.
 */
public interface ContentCategoryService {
    /**
     * 根据父节点id查询分类列表
     */
    List<ContentNode> getContentCategoryList(long parentId);

    /**
     * 添加内容分类节点
     */
    ServerResponse insertContentCategory(long parentId, String name);

    /**
     * 删除内容分类节点
     */
    ServerResponse deleteContentCategory(long parentId, long id);

    /**
     * 更新内容分类节点的名称
     */
    ServerResponse updateContentCategory(long id, String name);
}
