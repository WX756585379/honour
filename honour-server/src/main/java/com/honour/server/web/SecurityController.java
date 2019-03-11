package com.honour.server.web;

import com.honour.server.entity.vo.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(description = "Security相关接口")
@RequestMapping("/honour")
@Transactional
public class SecurityController {

    @GetMapping(value = "/needLogin")
    @ApiOperation(value = "没有登录")
    public Object needLogin() {
        return BaseResponse.error(401, "您还未登录");
    }
}
