package com.honour.manager.dao;

import com.honour.manager.entity.pojo.Menu;
import com.honour.manager.entity.pojo.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(Long pid);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Long pid);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<Permission> getPermissions();
}