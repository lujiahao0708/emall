package com.lujiahao.sso.service;


import com.lujiahao.common.domain.ServerResponse;
import com.lujiahao.sso.domain.UserDTO;

/**
 * Created by lujiahao on 2016/10/31.
 */
public interface IUserService {
    /**
     * 校验数据
     *
     * @param content 数据内容
     * @param type    数据类型
     * @return
     */
    ServerResponse checkData(String content, Integer type);

    /**
     * 创建用户
     */
    int createUser(UserDTO userDTO);

    /**
     * 用户登录
     */
    ServerResponse userLogin(String username, String password);

    /**
     * 根据token查询用户信息
     *
     * @param token
     * @return
     */
    ServerResponse getUserByToken(String token);
}
