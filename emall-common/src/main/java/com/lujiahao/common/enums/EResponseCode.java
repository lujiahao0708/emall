package com.lujiahao.common.enums;

/**
 * 全局响应码
 * @author lujiahao
 * @version 1.0
 * @date 2017-10-19 21:47
 */
public enum EResponseCode {
    SUCCESS(1,"OK"),
    ERROR(0,"ERROR"),

    NEED_LOGIN(10,"need_login"),
    ILLEGAL_ARGUMENT(2,"illegal_argument"),
    SERVER_ERROR(500,"SERVER_ERROR");

    private final int code;
    private final String msg;

    EResponseCode(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
