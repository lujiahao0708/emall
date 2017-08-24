package com.lujiahao.mmall.controller.backend;

import com.lujiahao.mmall.common.Const;
import com.lujiahao.mmall.common.ResponseCode;
import com.lujiahao.mmall.common.ServerResponse;
import com.lujiahao.mmall.pojo.User;
import com.lujiahao.mmall.service.ICategoryService;
import com.lujiahao.mmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 品类管理
 *
 * @author lujiahao
 * @version 1.0
 * @date 2017-08-12 21:19
 */
@Controller
@RequestMapping("/manage/category")
public class CategoryManageController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private ICategoryService iCategoryService;

    /**
     * 添加分类
     *
     * @param session
     * @param categoryName 分类名称
     * @param parentId     分类的父节点id(根节点为0)
     * @return
     */
    @RequestMapping(value = "add_category.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addCategory(HttpSession session, String categoryName,
                                      @RequestParam(value = "parentId", defaultValue = "0") int parentId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录,请重新登录");
        }
        // 校验是否是管理员
        if (iUserService.checkAdminRole(user).isSuccess()) {
            // 是管理员,添加分类逻辑开始
            return iCategoryService.addCategory(categoryName, parentId);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限");
        }
    }

    /**
     * 更新分类名称
     *
     * @param session
     * @param categoryId   分类的节点id(根节点为0)
     * @param categoryName 分类名称
     * @return
     */
    @RequestMapping(value = "update_category_name.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse updateCategoryName(HttpSession session, Integer categoryId, String categoryName) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录,请重新登录");
        }
        // 校验是否是管理员
        if (iUserService.checkAdminRole(user).isSuccess()) {
            // 是管理员,添加分类逻辑开始
            return iCategoryService.updateCategoryName(categoryId, categoryName);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限");
        }
    }

    /**
     * 根据categoryId查询平级子节点信息
     * @param session
     * @param categoryId 分类节点的id
     * @return
     */
    @RequestMapping(value = "get_category.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getChildrenParallelCategory(
            HttpSession session,
            @RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录,请重新登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            // 查询子节点的category信息,不递归保持平级
            return iCategoryService.getChildrenParallelCategory(categoryId);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限");
        }
    }

    /**
     * 递归查询本节点的id和子节点的id
     * @param session
     * @param categoryId 当前节点id
     * @return
     */
    @RequestMapping(value = "get_deep_category.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getCategoryAndDeepChildrenCategory(
            HttpSession session,
            @RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录,请重新登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            // 查询所有子节点的category信息
            return iCategoryService.selectCategoryAndChildrenById(categoryId);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限");
        }
    }
}
