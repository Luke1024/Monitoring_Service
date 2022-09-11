package com.service.monitor.app.service;
import com.service.monitor.app.domain.AppUser;
import com.service.monitor.app.domain.UserSession;
import com.service.monitor.app.service.monitoring.StatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class SessionManager {

    private Logger LOGGER = LoggerFactory.getLogger(SessionManager.class);

    @Autowired
    private TokenService tokenService;

    @Autowired
    private StatusService statusService;

    public void addSessionIfNecessary(AppUser appUser, Optional<String> sessionToken){
        if(sessionToken.isPresent()){
            createNewSessionIfNecessary(appUser, sessionToken.get());
        } else {
            addBasicSessionWithoutCookiesIfNecessary(appUser);
        }
    }

    private void createNewSessionIfNecessary(AppUser appUser, String sessionToken){
        Optional<UserSession> userSessionOptional = appUser.getLastSession();
        if(userSessionOptional.isPresent()) {
            if (checkIfLastSessionIsTheCurrentSession(userSessionOptional.get(), sessionToken)) {
                return;
            }
        }
        createANewSession(appUser, sessionToken);
    }

    private boolean checkIfLastSessionIsTheCurrentSession(UserSession session, String sessionToken){
        return sessionToken.equals(session.getSessionToken());
    }

    private void createANewSession(AppUser appUser, String sessionToken){
        statusService.getStatusDto().addVisit();
        appUser.addSession(sessionToken);
    }

    private void addBasicSessionWithoutCookiesIfNecessary(AppUser appUser){
        Optional<UserSession> userSession = appUser.getLastSession();
        if( ! userSession.isPresent()){
            statusService.getStatusDto().addVisit();
            appUser.addSession(tokenService.tokenReplacementWhenCookiesSwitchOff);
        }
    }
}
