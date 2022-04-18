package com.service.monitor.app.service.user.identity.authorizer;

import com.service.monitor.app.domain.AppUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserIdentityAuthorizer {
    private SecureRandom random = new SecureRandom();

    @Autowired
    private UserService userService;

    @Autowired
    private TokenGenerator tokenGenerator;

    protected String authCookieName = "AUTH";

    private Logger LOGGER = LoggerFactory.getLogger(UserIdentityAuthorizer.class);

    public void preAuth(Cookie[] cookies, HttpServletResponse response){
        Optional<String> token = filterValidCookiesToToken(cookies);
        if(token.isPresent()){
            Optional<AppUser> appUser = findUserByToken(token.get());
            if(appUser.isPresent()){
                return;
            }
        }
        generateNewToken(response);
    }

    public AppUser auth(Cookie[] cookies, String ipAdress){
        Optional<String> token = filterValidCookiesToToken(cookies);
        return userService.findOrCreateUser(token, ipAdress);
    }

    private Optional<AppUser> findUserByToken(String token){
        return userService.findUserByToken(token);
    }

    private void generateNewToken(HttpServletResponse response){
        String tokenNew = tokenGenerator.generate();
        userService.addTokenToPreAuth(tokenNew);
        Cookie cookie = new Cookie(authCookieName, tokenNew);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(15000000);
        response.addCookie(cookie);
    }

    private Optional<String> filterValidCookiesToToken(Cookie[] cookies){
        List<String> tokenList = new ArrayList<>();
        if(cookies == null) {
            return Optional.empty();
        }
        for (Cookie cookie : cookies) {
                if (isCookieGood(cookie)) {
                    tokenList.add(cookie.getValue());
                }
            }
        return filterMultipleTokens(tokenList);
    }

    private Optional<String> filterMultipleTokens(List<String> tokenList){
        if(tokenList.size() > 0) {
            if (tokenList.size() > 1) {
                LOGGER.warn("Multiple auth token detected in request.");
            }
            return Optional.of(tokenList.get(0));
        } else return Optional.empty();
    }

    private boolean isCookieGood(Cookie cookie){
        if (cookie.getName() == authCookieName) {
            if (cookie.getValue().length() == tokenGenerator.tokenLenght) {
                userService.addTokenToPreAuth(cookie.getValue());
                return true;
            }
        }
        return false;
    }
}
