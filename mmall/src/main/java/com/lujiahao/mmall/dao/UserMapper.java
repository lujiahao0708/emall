package com.lujiahao.mmall.dao;

import com.lujiahao.mmall.pojo.User;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 校验用户名是否存在
     * @param username
     * @return
     */
    int checkUsername(String username);

    /**
     * 用户名密码登录
     * @param username
     * @param password
     * @return
     */
    User selectLogin(@Param("username") String username, @Param("password") String password);

    /**
     * 校验邮箱是否存在
     * @param email
     * @return
     */
    int checkEmail(String email);

    /**
     * 根据用户名查找找回密码的问题
     * @param username
     * @return
     */
    String selectQuestionByUsername(String username);

    /**
     * 校验找回密码校验问题和答案
     * @param username
     * @param question
     * @param answer
     * @return
     */
    int checkAnswer(@Param("username") String username, @Param("question") String question, @Param("answer") String answer);

    /**
     * 根据用户名重置密码
     * @param username
     * @param passwordNew
     * @return
     */
    int updatePasswordByUsername(@Param("username")String username, @Param("passwordNew")String passwordNew);

    int checkPassword(@Param(value="password")String password,@Param("userId")Integer userId);

    int checkEmailByUserId(@Param(value="email")String email,@Param(value="userId")Integer userId);
}