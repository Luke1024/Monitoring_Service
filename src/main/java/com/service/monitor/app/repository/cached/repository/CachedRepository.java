package com.service.monitor.app.repository.cached.repository;

import com.service.monitor.app.domain.AppUser;
import com.service.monitor.app.repository.UserRepository;
import com.service.monitor.app.service.user.identity.authorizer.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CachedRepository {

    private Logger logger = LoggerFactory.getLogger(CachedRepository.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CacheUserFinderByToken userFinderByToken;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private Cache cache;

    public void saveUser(AppUser appUser) {
        if (isUserHasCookies(appUser)){
            cache.users.add(appUser);
            logger.info("Creating user in cache.");
        } else {
            fetchUniqueCookieLessUser();
        }
    }

    private boolean isUserHasCookies(AppUser appUser){
        return ! appUser.getToken().equals(tokenService.tokenReplacementWhenCookiesSwitchOff);
    }

    private void fetchUniqueCookieLessUser(){
        userFinderByToken.findUserByToken(tokenService.tokenReplacementWhenCookiesSwitchOff);
    }

    public Optional<AppUser> findUserByToken(String token){
        return userFinderByToken.findUserByToken(token);
    }
}
