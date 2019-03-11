package com.honour.server.web;

import com.honour.server.entity.pojo.User;
import com.honour.server.entity.vo.BaseResponse;
import com.honour.server.entity.vo.ExOpenIdVO;
import com.honour.server.entity.vo.LoginRegistVO;
import com.honour.server.utils.GeetestLib;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/honour")
public class LoginController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @ApiOperation(value = "用户登录")
    @ApiImplicitParam(name = "loginRegistVO", required = true, dataTypeClass = LoginRegistVO.class)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(@RequestBody LoginRegistVO loginRegistVO) {
        //极验验证
        GeetestLib gtSdk = new GeetestLib();

        String challenge = loginRegistVO.getChallenge();
        String validate = loginRegistVO.getValidate();
        String seccode = loginRegistVO.getSeccode();
        int gt_server_status_code = 0;

        //从redis中获取gt-server状态
        String val = stringRedisTemplate.opsForValue().get(loginRegistVO.getStatusKey());
        if (val != null) {
            gt_server_status_code = Integer.parseInt(val);
        }

        //自定义参数,可选择添加
        HashMap<String, String> param = new HashMap<>();
        int gtResult = 0;
        if (gt_server_status_code == 1) {
            //gt-server正常，向gt-server进行二次验证
            gtResult = gtSdk.enhencedValidateRequest(challenge, validate, seccode, param);
            System.out.println(gtResult);
        } else {
            // gt-server非正常情况下，进行failback模式验证
            System.out.println("failback:use your own server captcha validate");
            gtResult = gtSdk.failbackValidateRequest(challenge, validate, seccode);
            System.out.println(gtResult);
        }

        User user = new User();
        if (gtResult == 1) {
            // 验证成功
            return BaseResponse.success();
        } else {
            return BaseResponse.error("验证失败！");
        }
    }
}
