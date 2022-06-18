package com.service.monitor.app.service;

import org.springframework.stereotype.Service;

@Service
public class AdminAuthService {

    private String authKey = "example_token";

    public boolean authorize(String adminKey){
        return adminKey.equals(authKey);
    }
}
