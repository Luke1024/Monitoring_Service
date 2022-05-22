package com.service.monitor.app.repository;

import com.service.monitor.app.domain.AppUser;
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
    private CacheUserFinder cacheUserFinder;

    @Autowired
    private Cache cache;

    public void saveUser(AppUser appUser) {
        //avoiding duplicate users without token
        if(isUserHasUniqueToken(appUser)) {
            cache.users.add(appUser);
            logger.info("Creating user in cache.");
        } else {
            logger.warn("Token collision detected with token: " + appUser.getToken());
        }
    }

    private boolean isUserHasUniqueToken(AppUser appUser){
        String newUserToken = appUser.getToken();
        Optional<AppUser> appUserOptional = cacheUserFinder.findUserByToken(newUserToken);
        return ! appUserOptional.isPresent();
    }

    public Optional<AppUser> findUserByToken(String token){
        return cacheUserFinder.findUserByToken(token);
    }
}
