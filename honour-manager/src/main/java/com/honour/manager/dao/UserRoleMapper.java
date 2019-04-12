package com.honour.manager.dao;

import com.honour.manager.entity.pojo.UserRole;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Long pid);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Long pid);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);
}