package com.honour.manager.entity.pojo;

import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class User implements UserDetails, Serializable {

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

    private String email;

    private String phone;

    private String loginIp;

    private String status;

    private Date createTime;

    private Date updateTime;

    private List<Role> authorities;

    @Override
    public List<Role> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return account;
    }

    /**
     * 用户账号是否过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 用户账号是否被锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 用户密码是否过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 用户是否可用
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}