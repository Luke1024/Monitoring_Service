package com.service.monitor.app.repository;

import com.service.monitor.app.domain.AppUser;
import com.service.monitor.app.domain.IPAdress;
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
    private IPAdressRepository adressRepository;

    private List<AppUser> cachedUsers = new ArrayList<>();

    public void saveUser(AppUser appUser){
        userRepository.save(appUser);
        logger.info("Creating user in database.");
    }

    public Optional<AppUser> findUserByToken(String token){
        Optional<AppUser> userOptional = searchCacheByToken(token);
        if(userOptional.isPresent()){
            return userOptional;
        } else return cacheUserByTokenIfExist(token);
    }

    private Optional<AppUser> searchCacheByToken(String token){
        for(AppUser appUser : cachedUsers){
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
            cachedUsers.add(appUserOptional.get());
            return appUserOptional;
        } else return Optional.empty();
    }

    public Set<AppUser> findUserByIpAdress(String ipAdress){

        Set<AppUser> appUsers = new HashSet<>();

        List<IPAdress> ipAdressList = adressRepository.findByIpAdress(ipAdress);

        for(IPAdress adress : ipAdressList){
            appUsers.add(adress.getAppUser());
        }
        return appUsers;
    }
}
