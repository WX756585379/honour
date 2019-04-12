package com.honour.manager.dao;

import com.honour.manager.entity.pojo.Role;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long pid);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long pid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> getRolesByUserId(Long userId);
}