package com.lujiahao.sso.service;


import com.lujiahao.common.domain.ServerResponse;
import com.lujiahao.sso.domain.UserDTO;
import org.springframework.stereotype.Component;

/**
 * 用户操作Service接口
 * @author lujiahao
 * @date 2017/10/20
 */
public interface IUserService {

    /**
     * 用户登录
     */
    ServerResponse userLogin(UserDTO userDTO);

    /**
     * 注销登录
     * @param token
     * @return
     */
    ServerResponse userLogout(String token);

    /**
     * 校验数据
     *
     * @param content 数据内容
     * @param type    数据类型
     * @return
     */
    ServerResponse checkData(String content, Integer type);

    /**
     * 注册
     */
    ServerResponse<String> createUser(UserDTO userDTO);

    /**
     * 根据token查询用户信息
     *
     * @param token token信息
     * @return 用户信息
     */
    ServerResponse getUserByToken(String token);


}
