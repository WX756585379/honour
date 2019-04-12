package com.honour.manager.controller;

import com.honour.manager.constant.Constant;
import com.honour.manager.entity.param.LoginParam;
import com.honour.manager.entity.vo.BaseResponse;
import com.honour.manager.entity.vo.ResponseEnum;
import com.honour.manager.service.AuthService;
import com.honour.manager.utils.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@Api(tags = "AuthController", description = "后台用户管理")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(value = "/auth/login")
    @ApiOperation(value = "登录以后返回token")
    public Object login(@RequestBody LoginParam loginParam) {
        // 登录成功会返回Token给用户
        return authService.login(loginParam.getUsername(), loginParam.getPassword());
    }

    @ApiOperation(value = "刷新token")
    @PostMapping(value = "/token/refresh")
    public Object refreshToken(HttpServletRequest request) {
        String token = request.getHeader(JwtTokenUtil.TOKEN_HEADER);
        String refreshToken = authService.refreshToken(token);
        if (refreshToken == null) {
            return BaseResponse.error("操作失败");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", JwtTokenUtil.TOKEN_HEAD);
        return BaseResponse.success(tokenMap);
    }

    // @PostMapping(value = "/info")
    // @ApiOperation(value = "获取当前登录用户信息")
    // public Object getAdminInfo(Principal principal) {
    //     String username = principal.getName();
    //
    // }

    @PostMapping(value = "/user/hi")
    public String userHi(String name) throws AuthenticationException {
        return "hi " + name + " , you have 'user' role";
    }

    @PostMapping(value = "/admin/hi")
    public String adminHi(String name) throws AuthenticationException {
        return "hi " + name + " , you have 'admin' role";
    }
}
