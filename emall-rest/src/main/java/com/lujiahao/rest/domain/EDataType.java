package com.lujiahao.rest.domain;

/**
 * 数据校验的枚举
 *
 * @author lujiahao
 * @version 1.0
 * @date 2017-04-11 17:13
 */
public enum EDataType {
    /**
     * username
     */
    USERNAME(1),
    /**
     * phone
     */
    PHONE(2),
    /**
     * email
     */
    EMAIL(3);

    private int value;

    EDataType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static EDataType parse(int value) {
        for (EDataType eDataType : EDataType.values()) {
            if (eDataType.getValue() == value) {
                return eDataType;
            }
        }
        throw new IllegalArgumentException();
    }
}
