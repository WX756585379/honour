package com.honour.server.dao;

import com.honour.server.entity.pojo.DictionaryMapping;

public interface DictionaryMappingMapper {
    int deleteByPrimaryKey(Long pid);

    int insert(DictionaryMapping record);

    int insertSelective(DictionaryMapping record);

    DictionaryMapping selectByPrimaryKey(Long pid);

    int updateByPrimaryKeySelective(DictionaryMapping record);

    int updateByPrimaryKey(DictionaryMapping record);
}