package com.honour.server.entity.vo;

import lombok.Data;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class BaseResponse implements Serializable {

    public static final int RESULT_CODE_SUCCESS = 0;
    public static final int RESULT_CODE_VALIDATE_FAILED = -1;
    public static final int RESULT_CODE_SERVICE_UNAVAILABLE = -98;
    public static final int RESULT_CODE_ERROR_UNKNOWN = -99;

    private Object result;
    private int code;
    private String message;
    private String debugMessage;
    private long timestamp = System.currentTimeMillis();

    /**
     * 构造函数
     */
    public BaseResponse() {
    }

    public BaseResponse(int code) {
        this.code = code;
    }

    private BaseResponse(int code, Object data) {
        this.code = code;
        this.result = data;
    }

    private BaseResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private BaseResponse(int code, String message, Object data) {
        this.code = code;
        this.result = data;
        this.message = message;
    }

    public static BaseResponse create(int code, String message) {
        return new BaseResponse(code, message);
    }

    public static BaseResponse create(ResponseEnum responseEnum) {
        return new BaseResponse(responseEnum.getCode(), responseEnum.getDesc());
    }

    public static BaseResponse success() {
        return new BaseResponse(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getDesc());
    }

    public static BaseResponse success(String msg) {
        return new BaseResponse(ResponseEnum.SUCCESS.getCode(), msg);
    }

    public static BaseResponse success(Object data) {
        return new BaseResponse(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getDesc(), data);
    }

    public static BaseResponse success(String msg, Object data) {
        return new BaseResponse(ResponseEnum.SUCCESS.getCode(), msg, data);
    }

    public static BaseResponse error() {
        return new BaseResponse(ResponseEnum.ERROR.getCode(), ResponseEnum.ERROR.getDesc());
    }

    public static BaseResponse error(String msg) {
        return new BaseResponse(ResponseEnum.ERROR.getCode(), msg);
    }

    public static BaseResponse error(int code, String message) {
        return new BaseResponse(code, message);
    }
}
