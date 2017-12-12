package com.lujiahao.sso.service;

import com.lujiahao.common.domain.ServerResponse;

/**
 * 用户密码相关service
 * @author lujiahao
 * @date 2017-12-11 下午6:54
 */
public interface IUserPwdService {
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

    /**
     * 修改密码
     * @param username
     * @param passwordNew
     * @param forgetToken
     * @return
     */
    ServerResponse resetPwd(String username, String passwordNew, String forgetToken);
}
