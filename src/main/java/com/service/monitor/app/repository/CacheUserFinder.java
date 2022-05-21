package com.service.monitor.app.repository;

import com.service.monitor.app.domain.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class CacheUserFinder {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Cache cache;

    public Optional<AppUser> findUserByToken(String token){
        Optional<AppUser> userOptional = searchCacheByToken(token);
        if(userOptional.isPresent()){
            return userOptional;
        } else return cacheUserByTokenIfExist(token);
    }

    private Optional<AppUser> searchCacheByToken(String token){
        for(AppUser appUser : cache.users){
            if(isAppUserWithCorrectToken(appUser,token)){
                return Optional.of(appUser);
            }
        }
        return Optional.empty();
    }

    private boolean isAppUserWithCorrectToken(AppUser appUser, String token){
        return appUser.getToken().equals(token);
    }

    private Optional<AppUser> cacheUserByTokenIfExist(String token){
        Optional<AppUser> appUserOptional = userRepository.findByToken(token);
        if(appUserOptional.isPresent()){
            cache.users.add(appUserOptional.get());
            return appUserOptional;
        } else return Optional.empty();
    }
}
