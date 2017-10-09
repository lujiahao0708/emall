package com.lujiahao.portal.controller;

import com.lujiahao.common.pojo.CommonResult;
import com.lujiahao.portal.pojo.CartItem;
import com.lujiahao.portal.service.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 购物车的Controller
 * Created by lujiahao on 2016/11/1.
 */
@Controller
@RequestMapping(value = "/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    /**
     * 展示购物车界面
     * @param itemId
     * @param num
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/add/{itemId}")
    public String addCartItem(@PathVariable Long itemId, @RequestParam(defaultValue = "1") Integer num,
                              HttpServletRequest request, HttpServletResponse response) {
        CommonResult commonResult = cartService.addCartItem(request, response, itemId, num);
        //return "cartSuccess";  // 不这样直接写的原因是因为如果刷新的话会造成多次添加购物车,造成数量不正确
        return "redirect:/cart/success.html";
    }

    /**
     * 显示添加购物车成功界面
     */
    @RequestMapping(value = "/success")
    public String addCartItem() {
        return "cartSuccess";
    }

    /**
     * 展示购物车列表
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/cart")
    public String showCart(HttpServletRequest request, HttpServletResponse response, Model model){
        List<CartItem> list = cartService.getCartItemList(request, response);
        model.addAttribute("cartList",list);
        return "cart";
    }

    @RequestMapping(value = "/delete/{itemId}")
    public String deleteCartItem(@PathVariable Long itemId, HttpServletRequest request, HttpServletResponse response){
        CommonResult commonResult = cartService.deleteCartItem(request, response, itemId);
        return "redirect:/cart/cart.html";
    }
}
