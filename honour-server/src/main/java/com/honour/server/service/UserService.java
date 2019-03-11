package com.honour.server.service;

import com.honour.server.dao.UserMapper;
import com.honour.server.entity.pojo.Role;
import com.honour.server.entity.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User findUserInfoAndRolesByAccount(String account) {
        User user = userMapper.findByAccountName(account);
        List<Role> roleList = userMapper.getUserRolesByUid(user.getPid());
        user.setRoleList(roleList);
        return user;
    }
}
