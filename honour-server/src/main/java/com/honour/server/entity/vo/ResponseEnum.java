package com.honour.server.entity.vo;

public enum ResponseEnum {

    SUCCESS(0, "SUCCESS"),
    ERROR(1, "ERROR"),
    NEED_LOGIN(2, "NEED_LOGIN"),
    ERROR_UNKNOWN(-99, "ERROR_UNKNOWN"),
    VALIDATE_FAILED(-1, "VALIDATE_FAILED"),
    SERVICE_UNAVAILABLE(-98, "SERVICE_UNAVAILABLE"),
    ILLEGAL_ARGUMENT(4, "ILLEGAL_ARGUMENT");

    private final int code;
    private final String desc;

    ResponseEnum(int code, String desc) {
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
