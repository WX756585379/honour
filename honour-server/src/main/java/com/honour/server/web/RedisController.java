package com.honour.server.web;

import com.honour.server.utils.jedis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/redis")
public class RedisController {

    private static int ExpireTime = 60;   // redis中存储的过期时间60s

    @Resource
    private RedisUtil redisUtil;

    @RequestMapping("set")
    public boolean redisset(String key, String value) {
        // UserEntity userEntity = new UserEntity();
        // userEntity.setId(Long.valueOf(1));
        // userEntity.setGuid(String.valueOf(1));
        // userEntity.setName("zhangsan");
        // userEntity.setAge(String.valueOf(20));
        // userEntity.setCreateTime(new Date());
        // return redisUtil.set(key,userEntity,ExpireTime);
        // return redisUtil.set(key, value);
        return true;
    }

    @RequestMapping("get")
    public Object redisget(String key) {
        return redisUtil.get(key);
    }

    @RequestMapping("expire")
    public boolean expire(String key) {
        return redisUtil.expire(key, ExpireTime);
    }
}
