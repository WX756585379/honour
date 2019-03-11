package com.honour.server.constant;

public enum FileTypeEnum {

    IMG(1, "图片"),
    AUDIO(3, "音频"),
    VIDEO(2, "视频"),
    OTHER(4, "其他");

    private int code;
    private String msg;

    private FileTypeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int code() {
        return this.code;
    }

    public String msg() {
        return this.msg;
    }
}
