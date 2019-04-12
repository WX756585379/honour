package com.honour.manager.dao;

import com.honour.manager.entity.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long pid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long pid);

    int updateByPrimaryKeySelective(User record);

    int updateByUserNameSelective(User record);

    int updateByPrimaryKey(User record);

    User loadUserByUsername(String userName);
}