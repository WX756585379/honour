package com.honour.server.service;

import com.honour.server.entity.vo.LoginRegistVO;
import com.honour.server.utils.GeetestLib;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;

@Service
public class LoginService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public int validGeetestLibCode(LoginRegistVO loginVO) {

        // Executors.newFixedThreadPool()
        // Executors.newCachedThreadPool()
        // Executors
        // ExecutorService
        // ThreadPoolExecutor

        //极验验证
        GeetestLib gtSdk = new GeetestLib();
        String challenge = loginVO.getChallenge();
        String validate = loginVO.getValidate();
        String seccode = loginVO.getSeccode();

        int gtResult = 0;
        int gt_server_status_code = 0;
        //自定义参数,可选择添加
        HashMap<String, String> param = new HashMap<>();

        //从redis中获取gt-server状态
        String val = stringRedisTemplate.opsForValue().get(loginVO.getStatusKey());
        if (val != null) {
            gt_server_status_code = Integer.parseInt(val);
        }
        if (gt_server_status_code == 1) {
            //gt-server正常，向gt-server进行二次验证
            gtResult = gtSdk.enhencedValidateRequest(challenge, validate, seccode, param);
        } else {
            // gt-server非正常情况下，进行failback模式验证
            System.out.println("failback:use your own server captcha validate");
            gtResult = gtSdk.failbackValidateRequest(challenge, validate, seccode);
        }
        System.out.println(gtResult);
        return gtResult;
    }


}


