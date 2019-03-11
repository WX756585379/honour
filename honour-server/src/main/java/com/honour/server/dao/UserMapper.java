package com.honour.server.dao;

import com.honour.server.entity.pojo.Role;
import com.honour.server.entity.pojo.User;

import java.util.List;

public interface UserMapper {

    int deleteByPrimaryKey(Long pid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long pid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<Role> getUserRolesByUid(Long uid);

    User findByAccountName(String account);
}