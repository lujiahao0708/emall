package com.lujiahao.mmall.common;

/**
 * @author lujiahao
 * @version 1.0
 * @date 2017-07-30 17:52
 */
public class Const {
    public static final String CURRENT_USER = "currentUser";

    /**
     * 校验的参数类型
     * 使用接口的目的是为了分组  接口有这个特点
     */
    public interface CHECKTYPE{
        String USERNAME = "username";
        String EMAIL = "email";
    }

    public interface Role{
        int ROLE_CUSTOMER = 0;// 普通用户
        int ROLE_ADMIN = 1;// 管理员
    }
}
