package com.honour.server.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 权限管理决断器
 * 判断用户拥有的权限或角色是否有资源访问权限
 */
@Slf4j
@Component
public class UrlAccessDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication auth, Object o, Collection<ConfigAttribute> cas) {
        if (cas == null) return;

        for (ConfigAttribute ca : cas) {
            //当前请求需要的权限
            String needRole = ca.getAttribute();
            if ("ROLE_ALL".equals(needRole)) {
                return;
            }
            if ("ROLE_LOGIN".equals(needRole)) {
                if (auth instanceof AnonymousAuthenticationToken) {
                    throw new BadCredentialsException("未登录");
                } else {
                    return;
                }
            }
            //当前用户所具有的权限
            for (GrantedAuthority authority : auth.getAuthorities()) {
                if (authority.getAuthority().equals(needRole)) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("抱歉，您没有访问权限!");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}