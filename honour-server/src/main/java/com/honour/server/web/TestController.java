package com.honour.server.web;

import com.honour.server.entity.vo.BaseResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    // 登录
    @RequestMapping(value = "/authentication/login", method = RequestMethod.POST)
    public String createToken(String username, String password) {
        return null;
    }

    // 测试普通权限
    @PreAuthorize("hasAuthority('ROLE_NORMAL')")
    @RequestMapping(value = "/normal/test", method = RequestMethod.GET)
    public String test1() {
        return "ROLE_NORMAL /normal/test接口调用成功！";
    }

    // 测试管理员权限
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "/admin/test", method = RequestMethod.GET)
    public String test2() {
        return "ROLE_ADMIN /admin/test接口调用成功！";
    }

    @GetMapping(value = "/needLogin")
    public Object needLogin() {
        return BaseResponse.error(401, "您还未登录");
    }
}
