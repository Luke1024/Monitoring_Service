package com.service.monitor.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AdminAuthService {

    private String authKey = "example_token";
    private String deleteAuthKey = "example_token_2";

    private Logger logger = LoggerFactory.getLogger(AdminAuthService.class);

    public boolean authorize(String adminKey){
        if(adminKey.equals(authKey)){
            return true;
        } else {
            logger.warn("Authentication failed.");
            return false;
        }
    }

    public boolean authorizeDelete(String adminDeleteKey){
        if(adminDeleteKey.equals(deleteAuthKey)){
            return true;
        } else {
            logger.warn("Authentication failed for delete action.");
            return false;
        }
    }
}
