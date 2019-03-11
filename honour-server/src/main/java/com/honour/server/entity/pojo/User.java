package com.honour.server.entity.pojo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class User {

    private Long pid;

    private String account;

    private String password;

    private String nickName;

    private String realName;

    private Integer gender;

    private String avatar;

    private String openId;

    private String question;

    private String answer;

    private Date loginTime;

    private String loginIp;

    private String status;

    private Date createTime;

    private Date updateTime;

    private List<Role> roleList;
}