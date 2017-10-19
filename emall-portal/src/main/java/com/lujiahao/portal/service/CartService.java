package com.lujiahao.portal.service;


import com.lujiahao.common.domain.ServerResponse;
import com.lujiahao.portal.pojo.CartItem;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 购物车的操作  这里主要是使用cookie来实现的  其实还有其他的实现方法
 * Created by lujiahao on 2016/11/1.
 */
public interface CartService {
    /**
     * 根据商品id添加商品信息到购物车
     * @param itemId
     * @param num
     * @return
     */
    ServerResponse addCartItem(HttpServletRequest request, HttpServletResponse response, long itemId, int num);

    /**
     * 获取购物车列表
     * @param request
     * @param response
     * @return
     */
    List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response);

    // TODO 手动修改购物车商品数量

    /**
     * 删除购物车中的商品
     * @param request
     * @param response
     * @param itemId
     * @return
     */
    ServerResponse deleteCartItem(HttpServletRequest request, HttpServletResponse response, long itemId);
}
