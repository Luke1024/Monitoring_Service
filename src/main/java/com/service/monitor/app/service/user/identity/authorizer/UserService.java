package com.service.monitor.app.service.user.identity.authorizer;

import com.service.monitor.app.domain.AppUser;
import com.service.monitor.app.domain.IPAdress;
import com.service.monitor.app.repository.IPAdressRepository;
import com.service.monitor.app.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private List<String> tokensInPreAuth = new ArrayList<>();

    @Autowired
    private IPAdressRepository adressRepository;
    private Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private String tokenReplacementWhenCookiesSwitchOff = "";

    public Optional<AppUser> findUserByToken(String token){
        return userRepository.findByToken(token);
    }

    public void addTokenToPreAuth(String token){
        tokensInPreAuth.add(token);
    }

    public AppUser findOrCreateUser(Optional<String> token, String ipAdress){
        Optional<AppUser> appUserOptional;
        if (token.isPresent()) {
            appUserOptional = findUserByToken(token.get());
        } else {
            appUserOptional = findUsersByIpAdressWithoutCookies(ipAdress);
        }
        return returnOrCreateUser(appUserOptional, token, ipAdress);
    }

    private Optional<AppUser> findUsersByIpAdressWithoutCookies(String ipAdress){
        List<IPAdress> adresses = adressRepository.findByIpAdress(ipAdress);
        List<AppUser> possibleUsers = new ArrayList<>();
        for(IPAdress adress : adresses){
            Optional<AppUser> appUserOptional = getUserWithoutCookiesByAdress(adress);
            if(appUserOptional.isPresent()) {
                possibleUsers.add(appUserOptional.get());
            }
        }
        return filterMultipleUsers(possibleUsers);
    }

    private Optional<AppUser> getUserWithoutCookiesByAdress(IPAdress ipAdress){
        AppUser appUser = ipAdress.getAppUser();
        if(appUser != null){
            if( ! appUser.withCookies) {
                return Optional.of(appUser);
            }
        }
        return Optional.empty();
    }

    private Optional<AppUser> filterMultipleUsers(List<AppUser> appUsers){
        if(appUsers.size() > 0){
            if(appUsers.size() > 1){
                LOGGER.warn("Multiple users without cookies on the same adress detected.");
            }
            return Optional.of(appUsers.get(0));
        }
        return Optional.empty();
    }

    private AppUser returnOrCreateUser(Optional<AppUser> appUser, Optional<String> token, String ipAdress){
        if(appUser.isPresent()){
            return appUser.get();
        } else {
            return createUser(token, ipAdress);
        }
    }

    private AppUser createUser(Optional<String> token, String ipAdress){
        AppUser user;
        if(token.isPresent()){
            if( ! checkIfTokenWasGeneratedInAuth(token.get())){
                LOGGER.warn("Someone generated valid token from outside.");
            }
            user = new AppUser(true, token.get(), ipAdress, LocalDateTime.now());
        } else {
            user = new AppUser(false, tokenReplacementWhenCookiesSwitchOff, ipAdress, LocalDateTime.now());
        }
        userRepository.save(user);
        return user;
    }

    private boolean checkIfTokenWasGeneratedInAuth(String token){
        int index = tokensInPreAuth.indexOf(token);
        if(index != -1) {
            tokensInPreAuth.remove(tokensInPreAuth);
            return true;
        } else return false;
    }
}
