package com.lujiahao.sso.service;


import com.lujiahao.common.domain.ServerResponse;
import com.lujiahao.sso.domain.UserDTO;

/**
 * 用户操作Service接口
 * @author lujiahao
 * @date 2017/10/20
 */
public interface IUserService {

    /**
     * 用户登录
     */
    ServerResponse userLogin(String username, String password);

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
     * 创建用户
     */
    int createUser(UserDTO userDTO);

    /**
     * 根据token查询用户信息
     *
     * @param token token信息
     * @return 用户信息
     */
    ServerResponse getUserByToken(String token);

    /**
     * 根据用户名获取找回密码问题
     * @param username 用户名
     * @return 找回密码问题
     */
    ServerResponse selectQuestionByUsername(String username);

    /**
     * 校验找回密码答案是否正确
     * @param username
     * @param question
     * @param answer
     * @return
     */
    ServerResponse validPwdAnswer(String username, String question, String answer);
}
