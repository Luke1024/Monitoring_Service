package com.service.monitor.app.service;

import com.service.monitor.app.domain.AppUser;
import com.service.monitor.app.repository.UserRepository;
import com.service.monitor.app.service.PreAuthService;
import com.service.monitor.app.service.SessionManager;
import com.service.monitor.app.service.TokenService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SessionManagerTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    private PreAuthService preAuthService;

    @Test
    public void testingAddingSessionWhenCookiesSwitchOn(){
        String token = tokenService.generate();
        AppUser appUser = new AppUser("", LocalDateTime.now());
        sessionManager.addSessionIfNecessary(appUser, Optional.of(token));

        assertEquals(token, appUser.getLastSession().get().getSessionToken());
    }

    @Test
    public void testAlreadyExistingSession(){
        String token = tokenService.generate();
        AppUser appUser = new AppUser("", LocalDateTime.now());
        appUser.addSession(token);
        sessionManager.addSessionIfNecessary(appUser, Optional.of(token));

        assertEquals(token, appUser.getLastSession().get().getSessionToken());
    }

    @Test
    public void testCreateSessionWithSessionAlreadyExisting(){
        String token = tokenService.generate();
        AppUser appUser = new AppUser("", LocalDateTime.now());
        appUser.addSession("");
        sessionManager.addSessionIfNecessary(appUser, Optional.of(token));

        assertEquals(token, appUser.getLastSession().get().getSessionToken());
    }
}