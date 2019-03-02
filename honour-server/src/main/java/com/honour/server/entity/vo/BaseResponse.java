package com.honour.server.entity.vo;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class BaseResponse implements Serializable {

    public static final int RESULT_CODE_SUCCESS = 0;
    public static final int RESULT_CODE_VALIDATE_FAILED = -1;
    public static final int RESULT_CODE_SERVICE_UNAVAILABLE = -98;
    public static final int RESULT_CODE_ERROR_UNKNOWN = -99;

    private Object data;
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
        this.data = data;
    }

    private BaseResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private BaseResponse(int code, String message, Object data) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public static BaseResponse create(int code, String message) {
        return new BaseResponse(code, message);
    }

    public static BaseResponse create(ResponseEnum responseEnum) {
        return new BaseResponse(responseEnum.getCode(), responseEnum.getDesc());
    }

    public static BaseResponse success() {
        return new BaseResponse(ResponseEnum.SUCCESS.getCode());
    }

    public static BaseResponse successOfMsg(String msg) {
        return new BaseResponse(ResponseEnum.SUCCESS.getCode(), msg);
    }

    public static BaseResponse successOfData(Object data) {
        return new BaseResponse(ResponseEnum.SUCCESS.getCode(), data);
    }

    public static BaseResponse success(String msg, Object data) {
        return new BaseResponse(ResponseEnum.SUCCESS.getCode(), msg, data);
    }

    public static BaseResponse error() {
        return new BaseResponse(ResponseEnum.ERROR.getCode(), ResponseEnum.ERROR.getDesc());
    }

    public static BaseResponse error(String errorMessage) {
        return new BaseResponse(ResponseEnum.ERROR.getCode(), errorMessage);
    }

    public void setResultData(Object data) {
        this.data = data;
    }

    public void setResponseEnum(ResponseEnum responseEnum) {
        setResultCodeAndMessage(responseEnum.getCode(), responseEnum.getDesc());
    }

    public void setResultCodeAndMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public BaseResponse setData(Object data) {
        this.data = data;
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public BaseResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
