package com.honour.server.config.security;

import com.honour.server.entity.pojo.Menu;
import com.honour.server.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 权限资源管理器
 * 为权限决断器提供支持
 */
@Component
public class CustomMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private IgnoredUrlsProperties ignoredUrlsProperties;

    @Autowired
    private MenuService menuService;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        System.out.println("requestUrl = " + requestUrl);
        for (String str : ignoredUrlsProperties.getUrls()) {
            if (antPathMatcher.match(str.trim(), requestUrl)) {
                return SecurityConfig.createList("ROLE_ALL");
            }
        }

        List<Menu> allMenu = menuService.getAllMenu();//可以加到缓存中
        // for (Menu menu : allMenu) {
        //     if (antPathMatcher.match(menu.getUrl(), requestUrl) && menu.getRoles().size() > 0) {
        //         List<Role> roles = menu.getRoles();
        //         int size = roles.size();
        //         String[] values = new String[size];
        //         for (int i = 0; i < size; i++) {
        //             values[i] = roles.get(i).getName();
        //         }
        //         return SecurityConfig.createList(values);
        //     }
        // }
        //没有匹配上的资源，都是登录访问
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
