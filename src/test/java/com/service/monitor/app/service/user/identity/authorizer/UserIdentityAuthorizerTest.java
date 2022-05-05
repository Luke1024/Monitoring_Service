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

import static org.mockito.ArgumentMatchers.*;

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
        identityAuthorizer.preAuth(cookies, response);
        ArgumentCaptor<String> stringCaptor = ArgumentCaptor.forClass(String.class);
        Mockito.verify(response).addHeader(any(),stringCaptor.capture());
        Assert.assertTrue(stringCaptor.getValue().length()>0);
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