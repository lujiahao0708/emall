package com.lujiahao.sso.domain.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class User {
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

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createDate;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateDate;
}