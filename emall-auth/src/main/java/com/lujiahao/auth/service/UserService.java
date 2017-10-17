package com.lujiahao.auth.service;


import com.lujiahao.auth.domain.UserDTO;
import com.lujiahao.common.pojo.CommonResult;

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
    CommonResult checkData(String content, Integer type);

    /**
     * 创建用户
     */
    int createUser(UserDTO userDTO);

    /**
     * 用户登录
     */
    CommonResult userLogin(UserDTO userDTO, HttpServletRequest request, HttpServletResponse response);

    /**
     * 根据token查询用户信息
     * @param token
     * @return
     */
    CommonResult getUserByToken(String token);
}
