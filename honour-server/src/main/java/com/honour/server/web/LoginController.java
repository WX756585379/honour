package com.honour.server.web;

import com.honour.server.entity.pojo.User;
import com.honour.server.entity.vo.BaseResponse;
import com.honour.server.entity.vo.LoginRegistVO;
import com.honour.server.service.LoginService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/honour/auth")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @ApiOperation(value = "用户登录")
    @ApiImplicitParam(name = "loginRegistVO", required = true, dataTypeClass = LoginRegistVO.class)
    @PostMapping("/login")
    public Object login(@RequestBody LoginRegistVO loginVO) {
        int gtResult = loginService.validGeetestLibCode(loginVO);
        if (gtResult == 1) {// 验证成功
            // User user = new User();
            return BaseResponse.success();
        } else {
            return BaseResponse.error("验证失败！");
        }
    }

    @ApiOperation(value = "用户注册")
    @ApiImplicitParam(name = "loginRegistVO", required = true, dataTypeClass = LoginRegistVO.class)
    @PostMapping(value = "/register")
    public Object register(@RequestBody LoginRegistVO registVO) {
        int gtResult = loginService.validGeetestLibCode(registVO);
        if (gtResult == 1) {
            return BaseResponse.success();
        } else {
            return BaseResponse.error("验证失败！");
        }
    }
}
