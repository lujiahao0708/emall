package com.lujiahao.mmall.service;

import com.lujiahao.mmall.common.ServerResponse;
import com.lujiahao.mmall.pojo.Category;

import java.util.List;

/**
 * 分类管理模块
 */
public interface ICategoryService {
    ServerResponse addCategory(String categoryName, Integer parentId);

    ServerResponse updateCategoryName(Integer categoryId, String categoryName);

    ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId);

    ServerResponse selectCategoryAndChildrenById(Integer categoryId);
}
