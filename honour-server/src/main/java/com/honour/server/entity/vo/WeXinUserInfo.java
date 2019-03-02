package com.honour.server.entity.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class WeXinUserInfo implements Serializable {

    private String nickName;

    private Integer gender;

    private String language;

    private String city;

    private String province;

    private String country;

    private String avatarUrl;
}

