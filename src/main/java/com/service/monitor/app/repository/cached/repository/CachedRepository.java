package com.service.monitor.app.repository.cached.repository;

import com.service.monitor.app.domain.AppUser;
import com.service.monitor.app.repository.UserRepository;
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
    private CacheUserFinderByIpAdress userFinderByIpAdress;

    @Autowired
    private Cache cache;

    public void saveUser(AppUser appUser){
        cache.users.add(appUser);
        logger.info("Creating user in cache.");
    }

    public Optional<AppUser> findUserByToken(String token){
        return userFinderByToken.findUserByToken(token);
    }

    public Set<AppUser> findUserByIpAdressWithoutCookie(String ipAdress){
        return userFinderByIpAdress.findUserByIpAdressWithoutCookie(ipAdress);
    }




}
