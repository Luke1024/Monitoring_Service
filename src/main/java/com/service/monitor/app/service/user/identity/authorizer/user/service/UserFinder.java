package com.service.monitor.app.service.user.identity.authorizer.user.service;

import com.service.monitor.app.domain.AppUser;
import com.service.monitor.app.domain.IPAdress;
import com.service.monitor.app.repository.IPAdressRepository;
import com.service.monitor.app.repository.UserRepository;
import com.service.monitor.app.service.user.identity.authorizer.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserFinder {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IPAdressRepository adressRepository;

    @Autowired
    private TokenService tokenService;

    public Optional<AppUser> findUserByToken(String token){
        return userRepository.findByToken(token);
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
            if(appUser.getToken().equals(tokenService.tokenReplacementWhenCookiesSwitchOff)) {
                return Optional.of(appUser);
            }
        }
        return Optional.empty();
    }

    private Optional<AppUser> filterMultipleUsers(List<AppUser> appUsers){
        if(appUsers.size() > 0){
            if(appUsers.size() > 1){
                //LOGGER.warn("Multiple users without cookies on the same adress detected.");
            }
            return Optional.of(appUsers.get(0));
        }
        return Optional.empty();
    }


}
