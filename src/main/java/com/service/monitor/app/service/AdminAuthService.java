package com.service.monitor.app.service;

import org.springframework.stereotype.Service;

@Service
public class AdminAuthService {

    private String authKey = "example_token";
    private String deleteAuthKey = "example_token_2";

    public boolean authorize(String adminKey){
        return adminKey.equals(authKey);
    }

    public boolean authorizeDelete(String adminDeleteKey){
        return adminDeleteKey.equals(deleteAuthKey);
    }
}
