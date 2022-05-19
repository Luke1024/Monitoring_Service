package com.service.monitor.app.service.user.identity.authorizer.user.service;

import com.service.monitor.app.domain.AppUser;
import com.service.monitor.app.repository.CachedRepository;
import com.service.monitor.app.service.user.identity.authorizer.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserCreator {

    @Autowired
    private CachedRepository cachedRepository;

    @Autowired
    private TokenService tokenService;

    private Logger LOGGER = LoggerFactory.getLogger(UserCreator.class);

    public AppUser createUser(Optional<String> token, String ipAdress){
        AppUser user;
        if(token.isPresent()){
            if( ! tokenService.checkIfTokenWasGeneratedInAuthAndRemove(token.get())){
                LOGGER.warn("Someone generated valid token from outside.");
            }
            user = new AppUser(token.get(), ipAdress, LocalDateTime.now());
        } else {
            user = new AppUser(tokenService.tokenReplacementWhenCookiesSwitchOff, ipAdress, LocalDateTime.now());
        }
        cachedRepository.saveUser(user);
        LOGGER.info("Authorized new user with token: " + user.getToken());
        return user;
    }
}
