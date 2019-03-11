package com.honour.server.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class MethodService {

    // @Secured("ROLE_ADMIN")
    // public String admin() {
    //     return "hello admin";
    // }
    //
    // // 需要ADMIN和DBA角色
    // @PreAuthorize("hasRole('ADMIN') and hasRole('DBA')")
    // public String dba() {
    //     return "hello dba";
    // }
    //
    // // 三种任何一种角色都可以
    // @PreAuthorize("hasAnyRole('ADMIN','DBA','USER')")
    // public String user() {
    //     return "hello user";
    // }
}
