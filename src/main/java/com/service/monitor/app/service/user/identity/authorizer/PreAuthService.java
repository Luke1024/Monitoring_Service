package com.service.monitor.app.service.user.identity.authorizer;

import com.service.monitor.app.domain.AppUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.security.SecureRandom;
import java.util.Optional;

//service only returning cookie for later optional user creation

@Service
public class PreAuthService {
    private SecureRandom random = new SecureRandom();

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private CookieFilter cookieFilter;

    private Logger LOGGER = LoggerFactory.getLogger(PreAuthService.class);

    public void preAuth(Cookie[] cookies, HttpServletResponse response){

        Optional<String> authToken = getCookieValue(cookies, cookieFilter.authCookieName);
        if(authToken.isPresent()){
            Optional<AppUser> appUser = findUserByToken(authToken.get());
            if(appUser.isPresent()){
                LOGGER.info("User found with token: " + authToken.get() + "initializing new session.");
                generateNewSession(response);
                return;
            }
        }
        generateNewToken(response);
        generateNewSession(response);
    }

    private Optional<String> getCookieValue(Cookie[] cookies, String cookieName){
        return cookieFilter.filterCookiesToValue(cookies, cookieName);
    }

    private Optional<AppUser> findUserByToken(String token){
        return userService.findUserByToken(token);
    }

    private void generateNewToken(HttpServletResponse response){
        String tokenNew = tokenService.generate();
        tokenService.addTokenToPreAuth(tokenNew);
        response.addHeader("Set-Cookie", cookieGenerator(cookieFilter.authCookieName, tokenNew));
    }

    public String cookieGenerator(String authCookieName, String token){
        return authCookieName + "=" + token + "; Max-Age=15000000; Secure; HttpOnly; SameSite=None";
    }

    void generateNewSession(HttpServletResponse response){
        String tokenNew = tokenService.generate();
        tokenService.addTokenToPreAuth(tokenNew);
        response.addHeader("Set-Cookie", sessionCookieGenerator(cookieFilter.sessionCookieName, tokenNew));
    }

    private String sessionCookieGenerator(String sessionCookieName, String token){
        return sessionCookieName + "=" + token + "; Secure; HttpOnly; SameSite=None";
    }
}
