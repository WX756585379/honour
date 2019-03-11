package com.honour.server.dao;

import com.honour.server.entity.pojo.Menu;

import java.util.List;

public interface MenuMapper {

    int deleteByPrimaryKey(Long pid);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Long pid);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<Menu> getAllMenu();
}