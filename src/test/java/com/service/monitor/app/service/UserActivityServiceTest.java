package com.service.monitor.app.service;

import com.service.monitor.app.repository.UserRepository;
import com.service.monitor.app.service.user.identity.authorizer.PreAuthService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserActivityServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PreAuthService identityAuthorizer;

    @Test
    public void preAuthTestWithoutCookies() {

    }
}