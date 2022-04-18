package com.service.monitor.app.service.user.identity.authorizer;

import com.service.monitor.app.domain.AppUser;
import com.service.monitor.app.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserIdentityAuthorizerTest {

    @Autowired
    private UserIdentityAuthorizer identityAuthorizer;

    @Autowired
    private TokenGenerator tokenGenerator;

    @Autowired
    private UserRepository userRepository;

    @Mock
    HttpServletResponse response;

    @Test
    public void preAuthWithoutCookies() {
        Cookie[] cookies = {};
        ArgumentCaptor<Cookie> cookieCaptor = ArgumentCaptor.forClass(Cookie.class);
        identityAuthorizer.preAuth(cookies, response);
        Mockito.verify(response).addCookie(cookieCaptor.capture());
        Assert.assertEquals(16, cookieCaptor.getValue().getValue().length());
    }

    @Test
    public void preAuthWithCorrectCookie() {
        Cookie cookie = new Cookie(identityAuthorizer.authCookieName, tokenGenerator.generate());
        Cookie[] cookies = {cookie};

        identityAuthorizer.preAuth(cookies, response);
        Mockito.verify(response).addCookie(ArgumentMatchers.any());
    }

    @Test
    public void preAuthWithNotCorrectValueName() {
        Cookie cookie = new Cookie("TEST", tokenGenerator.generate());
        Cookie[] cookies = {cookie};
        identityAuthorizer.preAuth(cookies, response);
        ArgumentCaptor<Cookie> cookieCaptor = ArgumentCaptor.forClass(Cookie.class);
        Mockito.verify(response).addCookie(cookieCaptor.capture());

        Assert.assertEquals(identityAuthorizer.authCookieName,cookieCaptor.getValue().getName());
    }

    @Test
    public void preAuthWithNotCorrectToken() {
        Cookie cookie = new Cookie(identityAuthorizer.authCookieName, "TOKEN");
        Cookie[] cookies = {cookie};
        identityAuthorizer.preAuth(cookies, response);

        ArgumentCaptor<Cookie> cookieCaptor = ArgumentCaptor.forClass(Cookie.class);
        Mockito.verify(response).addCookie(cookieCaptor.capture());

        Assert.assertEquals(tokenGenerator.tokenLenght,cookieCaptor.getValue().getValue().length());
    }

    @Test
    public void preAuthWithUserWithCorrectTokenInDatabase() {
        String token = tokenGenerator.generate();
        AppUser appUser = new AppUser(true, token, "", LocalDateTime.now());
        userRepository.save(appUser);

        Cookie cookie = new Cookie(identityAuthorizer.authCookieName, token);
        Cookie[] cookies = {cookie};
        identityAuthorizer.preAuth(cookies, response);

        Mockito.verifyNoInteractions(response);
    }
}