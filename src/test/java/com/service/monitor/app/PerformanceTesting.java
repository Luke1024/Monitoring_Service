package com.service.monitor.app;

import com.service.monitor.app.domain.AppUser;
import com.service.monitor.app.repository.UserRepository;
import com.service.monitor.app.repository.cached.repository.Cache;
import com.service.monitor.app.repository.cached.repository.CachedRepository;
import com.service.monitor.app.service.UserActivityService;
import com.service.monitor.app.service.user.identity.authorizer.CookieFilter;
import com.service.monitor.app.service.user.identity.authorizer.TokenService;
import com.service.monitor.app.service.user.identity.authorizer.UserService;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.Cookie;
import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PerformanceTesting {

    private Logger LOGGER = LoggerFactory.getLogger(PerformanceTesting.class);

    @Autowired
    private UserActivityService activityService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private CookieFilter cookieFilter;

    @Autowired
    private Cache cache;

    @Autowired
    private UserRepository userRepository;

    @Ignore
    @Test
    public void actionSaving(){

        long start = System.currentTimeMillis();

        int actionSavingIterations = 10000;

        String action = "gkahsgldyiafglagfsdogaosdgaodspaosdgpagd";

        String authToken = tokenService.generate();
        String sessionToken = tokenService.generate();
        AppUser appUser = new AppUser(authToken, LocalDateTime.now());

        userRepository.save(appUser);

        Cookie authCookie = new Cookie(cookieFilter.authCookieName,authToken);
        Cookie sessionCookie = new Cookie(cookieFilter.sessionCookieName,sessionToken);

        Cookie[] cookies = {authCookie, sessionCookie};

        for(int i=0; i<actionSavingIterations; i++){
            activityService.save(action, cookies);
        }

        cache.saveActiveUsersToDatabase();

        long finish = System.currentTimeMillis();
        Assert.assertEquals(0, finish-start);
    }
}
