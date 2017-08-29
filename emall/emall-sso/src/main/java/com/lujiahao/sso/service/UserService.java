package com.lujiahao.sso.service;


import com.lujiahao.common.pojo.TaotaoResult;
import com.lujiahao.mapping.pojo.TbUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lujiahao on 2016/10/31.
 */
public interface UserService {
    /**
     * 校验数据
     * @param content 数据内容
     * @param type 数据类型
     * @return
     */
    TaotaoResult checkData(String content, Integer type);

    /**
     * 创建用户
     * @param tbUser 用户信息
     * @return
     */
    TaotaoResult createUser(TbUser tbUser);

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @param request
     *@param response
     */
    TaotaoResult userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response);

    /**
     * 根据token查询用户信息
     * @param token
     * @return
     */
    TaotaoResult getUserByToken(String token);
}
