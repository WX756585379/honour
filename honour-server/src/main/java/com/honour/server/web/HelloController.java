package com.honour.server.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/admin/hello")
    public String admin() {
        return "hello admin";
    }

    @GetMapping("/user/hello")
    public String user() {
        return "hello user";
    }

    @GetMapping("/db/hello")
    public String dba() {
        return "hello dba";
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
