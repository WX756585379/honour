package com.honour.server.web;

import com.honour.server.entity.vo.BaseResponse;
import com.honour.server.entity.vo.Captcha;
import com.honour.server.utils.CreateVerifyCode;
import com.honour.server.utils.jedis.RedisUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/honour/captcha")
public class CaptchaController {

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/init")
    @ApiOperation(value = "初始化验证码")
    public Object initCaptcha() {
        String captchaId = UUID.randomUUID().toString().replace("-", "");
        String code = new CreateVerifyCode().randomStr(4);
        Captcha captcha = new Captcha();
        captcha.setCaptchaId(captchaId);
        // 缓存验证码
        redisUtil.set(captchaId, code, 3L, TimeUnit.MINUTES);
        return BaseResponse.success(captcha);
    }

    @RequestMapping(value = "/draw/{captchaId}", method = RequestMethod.GET)
    @ApiOperation(value = "根据验证码ID获取图片")
    public void drawCaptcha(@PathVariable("captchaId") String captchaId, HttpServletResponse response) {
        try {
            //得到验证码 生成指定验证码
            String code = (String) redisUtil.get(captchaId);
            CreateVerifyCode vCode = new CreateVerifyCode(116, 36, 4, 10, code);
            vCode.write(response.getOutputStream());
        } catch (Exception e) {
            log.error("根据验证码ID获取图片异常！", e);
        }
    }
}
