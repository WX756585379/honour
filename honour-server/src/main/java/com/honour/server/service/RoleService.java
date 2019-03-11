package com.honour.server.service;

import com.honour.server.dao.RoleMapper;
import com.honour.server.entity.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;

    public List<Role> allRoles() {
        return roleMapper.selectAllRole();
    }

    public int insertSelective(Role role) {
        return roleMapper.insertSelective(role);
    }
}
