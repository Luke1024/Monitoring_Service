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
    private Cache cache;

    @Autowired
    private UserRepository userRepository;

    public void saveUser(AppUser appUser) {
        cache.users.put(appUser.getToken(),appUser);
        logger.info("Creating user in cache.");
    }

    public Optional<AppUser> findUserByToken(String token){
        Optional<AppUser> appUserFromCache = searchCacheByToken(token);
        if(appUserFromCache.isPresent()){
            return appUserFromCache;
        } else return userFromDatabase(token);
    }

    private Optional<AppUser> searchCacheByToken(String token){
        if(cache.users.containsKey(token)){
            return Optional.of(cache.users.get(token));
        } else return Optional.empty();
    }

    private Optional<AppUser> userFromDatabase(String token){
        Optional<AppUser> appUserFromDatabase = userRepository.findByToken(token);
        if(appUserFromDatabase.isPresent()){
            AppUser appUser = appUserFromDatabase.get();
            cache.users.put(appUser.getToken(), appUser);
        }
        return appUserFromDatabase;
    }
}
