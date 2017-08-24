package com.lujiahao.mmall.service;

import com.lujiahao.mmall.common.ServerResponse;
import com.lujiahao.mmall.pojo.User;

/**
 * Created by lujiahao on 2017/7/30.
 */
public interface IUserService {
    ServerResponse<User> login(String username,String password);

    ServerResponse<String> checkValid(String str, String type);

    ServerResponse<String> register(User user);

    ServerResponse<String> selectQuestion(String username);

    ServerResponse<String> checkAnswer(String username, String question, String answer);

    ServerResponse<String> forgetResetPassword(String username, String passwordNew, String forgetToken);

    ServerResponse<String> resetPassword(String passwordOld,String passwordNew,User user);

    ServerResponse<User> updateInformation(User user);

    ServerResponse<User> getInformation(Integer userId);

    ServerResponse checkAdminRole(User user);
}
