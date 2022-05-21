package com.service.monitor.app.service;


import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Service
class TokenService {

    private SecureRandom random = new SecureRandom();
    int tokenLenght = 16;
    private List<String> tokensInPreAuth = new ArrayList<>();
    public String tokenReplacementWhenCookiesSwitchOff = "";

    public void addTokenToPreAuth(String token){
        tokensInPreAuth.add(token);
    }

    public String generate(){
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = tokenLenght;
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    public boolean checkIfTokenWasGeneratedInAuthAndRemove(String token){
        int index = tokensInPreAuth.indexOf(token);
        if(index != -1) {
            tokensInPreAuth.remove(index);
            return true;
        } else return false;
    }
}
