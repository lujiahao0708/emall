package com.lujiahao.mmall.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.lujiahao.mmall.common.ServerResponse;
import com.lujiahao.mmall.dao.CategoryMapper;
import com.lujiahao.mmall.pojo.Category;
import com.lujiahao.mmall.service.ICategoryService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author lujiahao
 * @version 1.0
 * @date 2017-08-12 21:29
 */
@Service("iCategoryService")
public class CategoryServiceImpl implements ICategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 添加分类
     */
    public ServerResponse addCategory(String categoryName, Integer parentId) {
        if (parentId == null && StringUtils.isBlank(categoryName)) {
            return ServerResponse.createByErrorMessage("添加商品参数错误");
        }
        Category category = new Category();
        category.setName(categoryName);
        category.setParentId(parentId);
        category.setStatus(true);// 这个分类是可用的

        int insert = categoryMapper.insert(category);
        if (insert > 0) {
            return ServerResponse.createBySuccessMessage("添加分类成功");
        }
        return ServerResponse.createByErrorMessage("添加分类失败");
    }

    /**
     * 更新分类名称
     */
    @Override
    public ServerResponse updateCategoryName(Integer categoryId, String categoryName) {
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        if (category != null) {
            category.setName(categoryName);
            int i = categoryMapper.updateByPrimaryKeySelective(category);
            if (i > 0) {
                return ServerResponse.createBySuccessMessage("更新分类名称成功");
            } else {
                return ServerResponse.createByErrorMessage("更新分类名称失败");
            }
        } else {
            return ServerResponse.createByErrorMessage("该分类不存在,添加后才可修改");
        }
    }

    /**
     * 根据parentId查询分类信息
     */
    public ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId) {
        List<Category> categoryList = categoryMapper.selectCategoryChildrenByParentId(categoryId);
        if (CollectionUtils.isEmpty(categoryList)) {
            LOGGER.info("========== 未找到当前分类的子节点 ==========");
        }
        return ServerResponse.createBySuccess(categoryList);
    }

    /**
     * 递归查询本节点的id和子节点的id
     */
    public ServerResponse selectCategoryAndChildrenById(Integer categoryId){
        Set<Category> categorySet = Sets.newHashSet();
        findChildCategory(categorySet,categoryId);
        List<Integer> categoryIdList = Lists.newArrayList();
        if (categoryId != null) {
            for (Category category : categorySet) {
                categoryIdList.add(category.getId());
            }
        }
        return ServerResponse.createBySuccess(categoryIdList);
    }

    // 递归算法,查出子节点
    private Set<Category> findChildCategory(Set<Category> categorySet,Integer categoryId){
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        if (category != null) {
            categorySet.add(category);
        }
        // 查找子节点,递归算法需要退出条件
        List<Category> categoryList = categoryMapper.selectCategoryChildrenByParentId(categoryId);
        for (Category categoryItem : categoryList) {
            findChildCategory(categorySet,categoryItem.getId());
        }
        return categorySet;
    }
}
