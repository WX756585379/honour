package com.honour.manager.config.security;

import com.honour.manager.dao.MenuMapper;
import com.honour.manager.entity.pojo.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 每一个资源所需要的角色 Collection<ConfigAttribute>决策器会用到
     */
    private static HashMap<String, Collection<ConfigAttribute>> map = null;

    /**
     * 返回请求的资源需要的角色
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //object 中包含用户请求的request 信息
        HttpServletRequest request = ((FilterInvocation) o).getHttpRequest();
        for (String url : map.keySet()) {
            if (new AntPathRequestMatcher(url).matches(request)) {
                return map.get(url);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        //初始化 所有资源 对应的角色
        loadResourceDefine();
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    /**
     * 初始化 所有资源 对应的角色
     */
    private void loadResourceDefine() {
        map = new HashMap<>(16);
        //权限资源 和 角色对应的表  也就是 角色权限 中间表
        List<Permission> permissions = menuMapper.getPermissions();
        //某个资源 可以被哪些角色访问
        for (Permission permission : permissions) {
            String url = permission.getUrl();
            String roleName = permission.getRoleName();
            ConfigAttribute role = new SecurityConfig(roleName);
            if (map.containsKey(url)) {
                map.get(url).add(role);
            } else {
                Set<ConfigAttribute> set = new HashSet<>();
                set.add(role);
                map.put(url, set);
            }
        }
    }
}
