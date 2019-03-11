package com.honour.server.config.security;

import com.honour.server.entity.pojo.Role;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Slf4j
@Data
public class SecurityUserDetails implements UserDetails {

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        for (Role role : roleList) {
            authorityList.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorityList;
    }

    @Override
    public String getUsername() {
        return this.getAccount();
    }

    /**
     * 当前账户是否过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public String getPassword() {
        return password;
    }

    /**
     * 当前账户密码是否过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
