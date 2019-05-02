package com.honour.server.dao;

import com.honour.server.entity.pojo.ChannelRoomModelMapping;

public interface ChannelRoomModelMappingMapper {
    int deleteByPrimaryKey(Long pid);

    int insert(ChannelRoomModelMapping record);

    int insertSelective(ChannelRoomModelMapping record);

    ChannelRoomModelMapping selectByPrimaryKey(Long pid);

    int updateByPrimaryKeySelective(ChannelRoomModelMapping record);

    int updateByPrimaryKey(ChannelRoomModelMapping record);
}