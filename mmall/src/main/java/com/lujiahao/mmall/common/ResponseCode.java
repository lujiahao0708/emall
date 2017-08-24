package com.lujiahao.mmall.common;

/**
 * 响应码
 *
 * @author lujiahao
 * @version 1.0
 * @date 2017-07-30 17:22
 */
public enum ResponseCode {
    SUCCESS(0, "SUCCESS"),
    ERROR(1, "ERROR"),
    NEED_LOGIN(10, "NEED_LOGIN"),
    ILLEGAL_ARGUMENT(2, "ILLEGAL_ARGUMENT");

    private final int code;
    private final String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
