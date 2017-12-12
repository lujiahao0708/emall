package com.lujiahao.sso.domain;

import lombok.Data;

/**
 * 用户登录参数封装bean
 *
 * @author lujiahao
 * @version 1.0
 * @date 2017-10-10 17:00
 */
@Data
public class UserDTO {
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
