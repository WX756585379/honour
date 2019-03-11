package com.honour.server.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegService {

    public int reg(String username, String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        String encodePasswod = encoder.encode(password);
        return saveToDB(username, encodePasswod);
    }

    public int saveToDB(String username, String password) {
        return 1;
    }
}
