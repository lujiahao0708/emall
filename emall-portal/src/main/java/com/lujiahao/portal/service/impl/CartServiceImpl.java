package com.lujiahao.portal.service.impl;

import com.lujiahao.common.domain.ServerResponse;
import com.lujiahao.common.utils.CookieUtils;
import com.lujiahao.common.utils.HttpClientUtil;
import com.lujiahao.common.utils.JsonUtils;
import com.lujiahao.portal.pojo.CartItem;
import com.lujiahao.portal.service.CartService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 购物车Service
 * Created by lujiahao on 2016/11/1.
 */
@Service
public class CartServiceImpl implements CartService {
    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${ITEM_INFO_URL}")
    private String ITEM_INFO_URL;


    /**
     * 根据商品id添加商品信息到购物车
     *
     * @param itemId
     * @param num
     * @return
     */
    @Override
    public ServerResponse addCartItem(HttpServletRequest request, HttpServletResponse response, long itemId, int num) {
        // 区商品信息
        CartItem cartItem = null;
        // 先从购物车中取一次,这样能省一次网络访问   取购物车商品列表
        List<CartItem> itemList = getCartItemList(request);
        // 判断购物车商品列表中是否有商品存在
        for (CartItem cItem : itemList) {
            // 如果存在此商品
            if (cItem.getId() == itemId) {
                // 增加商品数量
                cItem.setNum(cItem.getNum() + num);
                cartItem = cItem;
                break;
            }
        }
        if (cartItem == null) {
            cartItem = new CartItem();
            // 根据商品id查询商品信息
            String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO_URL + itemId);
            // 把json转换成java对象
            ServerResponse serverResponse = ServerResponse.formatToPojo(json, TbItem.class);
            if (serverResponse.getStatus() == 200) {
                TbItem item = (TbItem) serverResponse.getData();
                cartItem.setId(item.getId());
                cartItem.setTitle(item.getTitle());
                cartItem.setImage(item.getImage() == null ? "" : item.getImage().split(",")[0]);
                cartItem.setNum(num);
                cartItem.setPrice(item.getPrice());
            }
            // 添加购物车列表
            itemList.add(cartItem);
        }
        // 把购物车列表写入cookie
        CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(itemList), true);
        return ServerResponse.success();
    }

    /**
     * 获取购物车列表
     * @param request
     * @param response
     * @return
     */
    @Override
    public List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response) {
        List<CartItem> itemList = getCartItemList(request);
        return itemList;
    }

    /**
     * 删除购物车中的商品
     * @param request
     * @param response
     * @param itemId
     * @return
     */
    @Override
    public ServerResponse deleteCartItem(HttpServletRequest request, HttpServletResponse response, long itemId) {
        // 从cookie中获取购物车商品
        List<CartItem> itemList = getCartItemList(request);
        // 从列表中找到此商品
        for (CartItem cartItem : itemList) {
            if (cartItem.getId() == itemId) {
                itemList.remove(cartItem);
                break;
            }
        }
        // 把购物车中商品信息重新写入cookie中
        CookieUtils.setCookie(request,response,"TT_CART",JsonUtils.objectToJson(itemId),true);
        return ServerResponse.success();
    }

    /**
     * 从cookie中获取商品列表
     *
     * @return
     */
    private List<CartItem> getCartItemList(HttpServletRequest request) {
        String cartJson = CookieUtils.getCookieValue(request, "TT_CART", true);
        if (cartJson == null) {
            return new ArrayList<>();
        }
        try {
            List<CartItem> list = JsonUtils.jsonToList(cartJson, CartItem.class);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

}
