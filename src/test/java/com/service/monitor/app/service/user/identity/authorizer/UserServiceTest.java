package com.service.monitor.app.service.user.identity.authorizer;

import com.service.monitor.app.domain.AppUser;
import com.service.monitor.app.repository.UserRepository;
import com.service.monitor.app.service.user.identity.authorizer.user.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;

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
        AppUser appUser = new AppUser(true, token,"", LocalDateTime.now());
        userRepository.save(appUser);

        Assert.assertEquals(appUser.token, userService.findUserByToken(token).get().token);
    }

    @Test
    public void findUserByIpAdressWithoutCookes() {
        String tokenAsAdress = tokenService.generate();
        AppUser appUser = new AppUser(false, "",tokenAsAdress, LocalDateTime.now());
        userRepository.save(appUser);

        AppUser receivedAppUser = userService.findOrCreateUser(Optional.empty(), tokenAsAdress);
        System.out.println(receivedAppUser.ipAdresses.size());
        Assert.assertEquals(appUser.id, receivedAppUser.id);
    }

    @Test
    public void tryToFindUserWithIpAdressWithCookies() {
        String tokenAsAdress = tokenService.generate();
        AppUser appUser = new AppUser(true, "",tokenAsAdress, LocalDateTime.now());
        userRepository.save(appUser);

        AppUser receivedAppUser = userService.findOrCreateUser(Optional.empty(), tokenAsAdress);
        Assert.assertNotEquals(appUser.id, receivedAppUser.id);
    }

    @Test
    public void createUserWithToken() {
        String token = tokenService.generate();
        userService.addTokenToPreAuth(token);

        AppUser appUser = userService.findOrCreateUser(Optional.of(token), "");
        Assert.assertEquals(token, appUser.token);
    }

    @Test
    public void createUserWithAdress() {
        String tokenAsAdress = tokenService.generate();

        AppUser appUser = userService.findOrCreateUser(Optional.empty(), tokenAsAdress);

        Assert.assertEquals(tokenAsAdress, appUser.ipAdresses.get(0).getAdress());
        Assert.assertEquals(false, appUser.withCookies);
    }
}