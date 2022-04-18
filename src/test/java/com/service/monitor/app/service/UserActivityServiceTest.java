package com.service.monitor.app.service;

import com.service.monitor.app.repository.UserRepository;
import com.service.monitor.app.service.user.identity.authorizer.UserIdentityAuthorizer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Locale;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserActivityServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserIdentityAuthorizer identityAuthorizer;

    @Test
    public void preAuthTestWithoutCookies() {

    }
}