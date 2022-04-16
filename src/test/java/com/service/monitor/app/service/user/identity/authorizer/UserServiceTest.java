package com.service.monitor.app.service.user.identity.authorizer;

import com.service.monitor.app.domain.AppUser;
import com.service.monitor.app.repository.UserRepository;
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
    private UserIdentityAuthorizer identityAuthorizer;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findUserByToken() {
        String token = generateToken();
        AppUser appUser = new AppUser(true, token,"", LocalDateTime.now());
        userRepository.save(appUser);

        Assert.assertEquals(appUser.token, userService.findUserByToken(token).get().token);
    }

    @Test
    public void findUserByIpAdressWithoutCookes() {
        String tokenAsAdress = generateToken();
        AppUser appUser = new AppUser(false, "",tokenAsAdress, LocalDateTime.now());
        userRepository.save(appUser);

        AppUser receivedAppUser = userService.findOrCreateUser(Optional.empty(), tokenAsAdress);
        System.out.println(receivedAppUser.ipAdresses.size());
        Assert.assertEquals(tokenAsAdress, receivedAppUser.ipAdresses.get(0).getAdress());
    }

    @Test
    public void tryToFindUserWithIpAdressWithCookies() {
        String tokenAsAdress = generateToken();
        AppUser appUser = new AppUser(true, "",tokenAsAdress, LocalDateTime.now());
        userRepository.save(appUser);
        long originalUserId = appUser.id;

        AppUser receivedAppUser = userService.findOrCreateUser(Optional.empty(), tokenAsAdress);
        Assert.assertNotEquals(originalUserId, receivedAppUser.id);
    }

    @Test
    public void createUserWithToken() {
        String token = generateToken();
        userService.addTokenToPreAuth(token);

        AppUser appUser = userService.findOrCreateUser(Optional.of(token), "");
        Assert.assertEquals(token, appUser.token);
    }

    @Test
    public void createUserWithAdress() {
        String tokenAsAdress = generateToken();

        AppUser appUser = userService.findOrCreateUser(Optional.empty(), tokenAsAdress);

        Assert.assertEquals(tokenAsAdress, appUser.ipAdresses.get(0).getAdress());
        Assert.assertEquals(false, appUser.withCookies);
    }

    private String generateToken(){
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = identityAuthorizer.tokenLenght;
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}