package com.lujiahao.mapping.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class EmallUser {
    private Integer id;

    private String username;

    private String password;

    private String email;

    private String phone;

    private String question;

    private String answer;

    private Integer roleId;

    private Date createDate;

    private Date updateDate;
}