package com.honour.server.entity.vo;

import java.io.Serializable;

public class ExOpenIdVO implements Serializable{

    private String code;

    private WeXinUserInfo weXinUserInfo;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public WeXinUserInfo getWeXinUserInfo() {
        return weXinUserInfo;
    }

    public void setWeXinUserInfo(WeXinUserInfo weXinUserInfo) {
        this.weXinUserInfo = weXinUserInfo;
    }
}
