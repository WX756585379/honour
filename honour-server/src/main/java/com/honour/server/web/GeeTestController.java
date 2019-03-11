package com.honour.server.web;

import com.honour.server.entity.vo.BaseResponse;
import com.honour.server.entity.vo.GeetInit;
import com.honour.server.utils.GeetestLib;
import com.honour.server.utils.JsonUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/honour/common")
public class GeeTestController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/geeInit")
    public Object geetInit() {
        GeetestLib gtSdk = new GeetestLib();
        String resStr = "{}";
        //自定义参数,可选择添加
        HashMap<String, String> param = new HashMap<>();
        //进行验证预处理
        int gtServerStatus = gtSdk.preProcess(param);
        //将服务器状态设置到redis中
        String key = UUID.randomUUID().toString();
        stringRedisTemplate.opsForValue().set(key, gtServerStatus + "", 360, TimeUnit.SECONDS);
        resStr = gtSdk.getResponseStr();
        GeetInit geetInit = JsonUtil.jsonToPojo(resStr, GeetInit.class);
        if (geetInit != null) {
            geetInit.setStatusKey(key);
        }
        return BaseResponse.successOfData(geetInit);
    }
}
