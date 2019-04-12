package com.honour.manager.dao;

import com.honour.manager.entity.pojo.MenuRole;

public interface MenuRoleMapper {
    int deleteByPrimaryKey(Long pid);

    int insert(MenuRole record);

    int insertSelective(MenuRole record);

    MenuRole selectByPrimaryKey(Long pid);

    int updateByPrimaryKeySelective(MenuRole record);

    int updateByPrimaryKey(MenuRole record);
}