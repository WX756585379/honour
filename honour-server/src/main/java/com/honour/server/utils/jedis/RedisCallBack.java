package com.honour.server.utils.jedis;

import org.springframework.data.redis.core.RedisTemplate;

public interface RedisCallBack<T> {

    T doInRedis(RedisTemplate redisTemplate);

}
