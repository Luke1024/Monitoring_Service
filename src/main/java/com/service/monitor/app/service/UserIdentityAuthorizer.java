package com.service.monitor.app.service;

import com.service.monitor.app.domain.AppUser;
import com.service.monitor.app.domain.IPAdress;
import com.service.monitor.app.repository.IPAdressRepository;
import com.service.monitor.app.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserIdentityAuthorizer {
    private Random random = new Random();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IPAdressRepository adressRepository;

    private List<String> tokensInPreAuth = new ArrayList<>();
    private String authCookieName = "AUTH";
    private int tokenLenght = 16;
    private String tokenReplacementWhenCookiesSwitchOff = "";

    private Logger LOGGER = LoggerFactory.getLogger(UserIdentityAuthorizer.class);

    public void preAuth(HttpServletRequest request, HttpServletResponse response){
        Optional<String> token = filterValidCookiesToToken(request.getCookies());
        if(token.isPresent()){
            if(findUserByToken(token.get()).isPresent()){
                return;
            }
        }
        String tokenNew = generateToken();
        tokensInPreAuth.add(tokenNew);
        response.addCookie(new Cookie(authCookieName, tokenNew));
    }

    public AppUser auth(HttpServletRequest request){
        Optional<String> token = filterValidCookiesToToken(request.getCookies());
        String ipAdress = request.getRemoteAddr();
        Optional<AppUser> appUserOptional;
        if (token.isPresent()) {
            appUserOptional = findUserByToken(token.get());
        } else {
            appUserOptional = findUserByIpAdress(ipAdress);
        }
        return returnOrCreateUser(appUserOptional, token, ipAdress);
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
            if(checkIfTokenWasGeneratedInAuth(token.get())){
                LOGGER.warn("Someone generated valid token from outside.");
            }
            user = new AppUser(true, token.get(), ipAdress, LocalDateTime.now());
        } else {
            user = new AppUser(false, tokenReplacementWhenCookiesSwitchOff, ipAdress, LocalDateTime.now());
        }
        userRepository.save(user);
        return user;
    }

    private Optional<String> filterValidCookiesToToken(Cookie[] cookies){
        if(cookies != null) {
            List<String> tokenList = new ArrayList<>();
            for (Cookie cookie : cookies) {
                if (cookie.getName() == authCookieName) {
                    if (cookie.getValue().length() == tokenLenght) {
                        tokenList.add(cookie.getValue());
                    }
                }
            }
            if(tokenList.size() > 1){
                LOGGER.warn("Multiple auth token detected in request.");
                return Optional.of(tokenList.get(0));
            }
        }
        return Optional.empty();
    }

    private String generateToken(){
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = tokenLenght;
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    private boolean checkIfTokenWasGeneratedInAuth(String token){
        int index = tokensInPreAuth.indexOf(token);
        if(index != -1) {
            tokensInPreAuth.remove(tokensInPreAuth);
            return true;
        } else return false;
    }

    private Optional<AppUser> findUserByToken(String token){
        return userRepository.findByToken(token);
    }

    private Optional<AppUser> findUserByIpAdress(String ipAdress){
        List<IPAdress> adresses = adressRepository.findByIpAdress(ipAdress);
        List<AppUser> possibleUsers = new ArrayList<>();
        for(IPAdress adress : adresses){
            Optional<AppUser> appUserOptional = userRepository.findById(adress.getId());
            if(appUserOptional.isPresent()) {
                if( ! appUserOptional.get().withCookies) {
                    possibleUsers.add(appUserOptional.get());
                }
            }
        }
        if(possibleUsers.size() > 0){
            if(possibleUsers.size() > 1){
                LOGGER.warn("Multiple users without cookies on the same adress detected.");
            }
            return Optional.of(possibleUsers.get(0));
        }
        return Optional.empty();
    }
}
