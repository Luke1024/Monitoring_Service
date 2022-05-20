package com.service.monitor.app.service.user.identity.authorizer.user.service;

import com.service.monitor.app.domain.AppUser;
import com.service.monitor.app.repository.cached.repository.CachedRepository;
import com.service.monitor.app.service.user.identity.authorizer.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserFinder {

    @Autowired
    private CachedRepository cachedRepository;

    @Autowired
    private TokenService tokenService;

    private Logger logger = LoggerFactory.getLogger(UserFinder.class);

    public Optional<AppUser> findUserByToken(String token){
        return cachedRepository.findUserByToken(token);
    }

    public Optional<AppUser> findUser(Optional<String> token, String ipAdress){
        Optional<AppUser> appUserOptional;
        if (token.isPresent()) {
            return findUserByToken(token.get());
        } else {
            return findUsersByIpAdressWithoutCookies(ipAdress);
        }
    }

    private Optional<AppUser> findUsersByIpAdressWithoutCookies(String ipAdress){
        Set<AppUser> usersWithRequiredIp = cachedRepository.findUserByIpAdressWithoutCookie(ipAdress);
        return filterMultipleUsers(usersWithRequiredIp);
    }

    private List<AppUser> getUsersWithoutCookies(Set<AppUser> usersWithRequiredIp){
        List<AppUser> usersWithoutCookies = new ArrayList<>();
        for(AppUser appUser : usersWithRequiredIp){
            if(appUser.getToken().equals("")){
                usersWithoutCookies.add(appUser);
            }
        }
        return usersWithoutCookies;
    }

    private Optional<AppUser> filterMultipleUsers(Set<AppUser> appUsers){
        if(appUsers.size() > 0){
            if(appUsers.size() > 1){
                logger.info("Multiple users on same adress without cookies detected.");
            }
            return Optional.of(appUsers.toArray().);
        }
        return Optional.empty();
    }


}
