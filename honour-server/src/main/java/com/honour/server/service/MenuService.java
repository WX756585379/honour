package com.honour.server.service;

import com.honour.server.dao.MenuMapper;
import com.honour.server.entity.pojo.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;

    public List<Menu> getAllMenu() {
        return menuMapper.getAllMenu();
    }
}
