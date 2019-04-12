package com.honour.manager.config.security;

import com.honour.manager.dao.RoleMapper;
import com.honour.manager.dao.UserMapper;
import com.honour.manager.entity.pojo.Role;
import com.honour.manager.entity.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(userName);
        if (user != null) {
            List<Role> roles = roleMapper.getRolesByUserId(user.getPid());
            user.setAuthorities(roles);
        }
        return user;
    }
}
