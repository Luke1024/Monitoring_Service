package com.service.monitor.app.service.user.identity.authorizer;
import com.service.monitor.app.domain.AppUser;
import com.service.monitor.app.domain.UserSession;
import com.service.monitor.app.service.user.identity.authorizer.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.Optional;

@Service
public class SessionManager {

    private Logger LOGGER = LoggerFactory.getLogger(SessionManager.class);

    @Autowired
    private UserService userService;

    @Autowired
    private CookieFilter cookieFilter;

    @Autowired
    private TokenService tokenService;

    public void addSessionIfNecessary(AppUser appUser, Cookie[] cookies){
        Optional<String> sessionToken = cookieFilter.filterCookiesToValue(cookies,cookieFilter.sessionCookieName);
        if(sessionToken.isPresent()){
            createNewSessionIfNecessary(appUser, sessionToken.get());
        } else {
            addBasicSessionWithoutCookiesIfNecessary(appUser);
        }
    }

    private void createNewSessionIfNecessary(AppUser appUser, String sessionToken){
        Optional<UserSession> userSessionOptional = appUser.getLastSession();
        if(userSessionOptional.isPresent()){
            if(checkIfLastSessionIsTheCurrentSession(userSessionOptional.get(), sessionToken)){
                return;
            }
        }
        createANewSession(appUser, sessionToken);
    }

    private boolean checkIfLastSessionIsTheCurrentSession(UserSession session, String sessionToken){
        return sessionToken.equals(session.getSessionToken());
    }

    private void createANewSession(AppUser appUser, String sessionToken){
        if( ! tokenService.checkIfTokenWasGeneratedInAuthAndRemove(sessionToken)){
            LOGGER.warn("Someone generated valid token from outside.");
        }
        appUser.addSession(sessionToken);
    }

    private void addBasicSessionWithoutCookiesIfNecessary(AppUser appUser){
        Optional<UserSession> userSession = appUser.getLastSession();
        if( ! userSession.isPresent()){
            appUser.addSession(tokenService.tokenReplacementWhenCookiesSwitchOff);
        }
    }
}
