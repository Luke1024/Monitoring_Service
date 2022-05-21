package com.service.monitor.app.service.user.identity.authorizer;

import com.service.monitor.app.domain.AppUser;
import com.service.monitor.app.repository.UserRepository;
import com.service.monitor.app.service.PreAuthService;
import com.service.monitor.app.service.TokenService;
import com.service.monitor.app.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    private SecureRandom random = new SecureRandom();

    @Autowired
    private PreAuthService identityAuthorizer;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Test
    public void findUserByToken() {
        String token = tokenService.generate();
        AppUser appUser = new AppUser(token, LocalDateTime.now());
        userRepository.save(appUser);

        Assert.assertEquals(appUser.getToken(), userService.findUserByToken(token).get().getToken());
    }

    @Test
    public void createUserWithToken() {

    }
}