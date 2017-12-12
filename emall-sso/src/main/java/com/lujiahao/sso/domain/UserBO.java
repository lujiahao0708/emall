package com.lujiahao.sso.domain;

import lombok.Data;

/**
 * @author lujiahao
 * @date 2017-12-10 下午4:57
 */
@Data
public class UserBO {
    private Integer id;
    /**用户名*/
    private String username;
    /**密码*/
    private String password;
    /**手机号*/
    private String phone;
    /**邮箱*/
    private String email;
    /**找回密码的问题*/
    private String question;
    /**找回密码问题答案*/
    private String answer;
    /**用户权限id*/
    private Integer roleId;
    /**用户token*/
    private String token;
}
