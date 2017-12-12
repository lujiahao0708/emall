package com.lujiahao.sso.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author lujiahao
 * @date 2017-12-11 下午7:03
 */
public interface UserPwdMapper {

    /**
     * 根据用户名获取找回密码问题
     */
    String selectQuestionByUsername(String username);

    /**
     * 根据用户名获取找回密码问题
     */
    int validPwdAnswer(@Param("username") String username, @Param("question") String question, @Param("answer") String answer);

    /**
     * 修改密码
     */
    int updatePwdByUsername(@Param("username") String username, @Param("password") String passwordNew);


}
