package com.service.monitor.app.service.user.identity.authorizer;

import com.service.monitor.app.service.user.identity.authorizer.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CookieFilter {

    public String authCookieName = "AUTH";
    public String sessionCookieName = "SESSION";

    @Autowired
    private TokenService tokenService;

    private Logger LOGGER = LoggerFactory.getLogger(CookieFilter.class);

    @Autowired
    private UserService userService;

    public Optional<String> filterCookiesToValue(Cookie[] cookies, String cookieName){
        List<String> tokenList = new ArrayList<>();
        if(cookies == null) {
            return Optional.empty();
        }
        for (Cookie cookie : cookies) {
            if (isCookieGood(cookie, cookieName)) {
                tokenList.add(cookie.getValue());
            }
        }
        return filterMultipleTokens(tokenList);
    }

    private boolean isCookieGood(Cookie cookie, String cookieName){
        if (cookie.getName().equals(cookieName)) {
            if (cookie.getValue().length() == tokenService.tokenLenght) {
                return true;
            }
        }
        return false;
    }

    private Optional<String> filterMultipleTokens(List<String> tokenList){
        if(tokenList.size() > 0) {
            if (tokenList.size() > 1) {
                LOGGER.warn("Multiple cookies with the same name detected.");
            }
            return Optional.of(tokenList.get(0));
        } else return Optional.empty();
    }
}
