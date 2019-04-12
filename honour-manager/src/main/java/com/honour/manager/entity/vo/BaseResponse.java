package com.honour.manager.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class BaseResponse implements Serializable {

    public static final int RESULT_CODE_SUCCESS = 0;
    public static final int RESULT_CODE_VALIDATE_FAILED = -1;
    public static final int RESULT_CODE_SERVICE_UNAVAILABLE = -98;
    public static final int RESULT_CODE_ERROR_UNKNOWN = -99;

    private int code;
    private Object result;
    private String message;
    private String debugMessage;
    private long timestamp = System.currentTimeMillis();

    public BaseResponse() {
    }

    private BaseResponse(int code, String message, Object data) {
        this.code = code;
        this.result = data;
        this.message = message;
    }

    public static BaseResponse create(int code, String message) {
        return new BaseResponse(code, message, null);
    }

    public static BaseResponse create(ResponseEnum responseEnum) {
        return new BaseResponse(responseEnum.getCode(), responseEnum.getDesc(), null);
    }

    public static BaseResponse success() {
        return new BaseResponse(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getDesc(), null);
    }

    public static BaseResponse success(String msg) {
        return new BaseResponse(ResponseEnum.SUCCESS.getCode(), msg, null);
    }

    public static BaseResponse success(Object data) {
        return new BaseResponse(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getDesc(), data);
    }

    public static BaseResponse success(String msg, Object data) {
        return new BaseResponse(ResponseEnum.SUCCESS.getCode(), msg, data);
    }

    public static BaseResponse error() {
        return new BaseResponse(ResponseEnum.ERROR.getCode(), ResponseEnum.ERROR.getDesc(), null);
    }

    public static BaseResponse error(ResponseEnum responseEnum) {
        return new BaseResponse(responseEnum.getCode(), responseEnum.getDesc(), null);
    }

    public static BaseResponse error(String errorMessage) {
        return new BaseResponse(ResponseEnum.ERROR.getCode(), errorMessage, null);
    }
}
