package com.honour.server.web;

import com.honour.server.dao.RoleMapper;
import com.honour.server.entity.pojo.Role;
import com.honour.server.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/honour/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    public List<Role> allRoles() {
        return roleService.allRoles();
    }

    @PostMapping("/addRole")
    public Object addNewRole(@RequestBody Role role) {
        if (!role.getName().startsWith("ROLE_")) {
            role.setName("ROLE_" + role.getName());
        }
        return roleService.insertSelective(role);
    }
}
