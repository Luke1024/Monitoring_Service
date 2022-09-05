package com.service.monitor.app.service;

import com.service.monitor.app.domain.AppUser;
import com.service.monitor.app.domain.Contact;
import com.service.monitor.app.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.Cookie;
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

    @Autowired
    private CookieFilter cookieFilter;

    @Test
    public void findUserByToken() {
        AppUser appUser = createNewUser(true);
        Assert.assertEquals(appUser.getToken(), userService.findUserByToken(appUser.getToken()).get().getToken());
    }

    @Test
    public void saveContact() {
        AppUser appUser = createNewUser(true);

        Contact contact = new Contact("name","email","message", appUser);
        userService.saveContact(contact);

        Assert.assertEquals(contact.toString(), userService.findUserByToken(appUser.getToken()).get().getContacts().get(0).toString());
    }

    //auth test suite

    //token is present

    //user already exist
    @Test
    public void testAuthUserWithCookiesAlreadyExist(){
        AppUser appUser = createNewUser(true);
        Cookie[] cookies = {new Cookie(cookieFilter.authCookieName, appUser.getToken())};
        AppUser appUserAuthorized = userService.auth(cookies);
        Assert.assertEquals(appUser.getToken(), appUser.getToken());
    }
    //user don't exist
    @Test
    public void testAuthUserWithCookiesDontExist(){
        String token = tokenService.generate();
        Cookie[] cookies = {new Cookie(cookieFilter.authCookieName, token)};
        AppUser appUserAuthorized = userService.auth(cookies);
        Assert.assertEquals(token, appUserAuthorized.getToken());
    }
    //token is not present

    //user already exist
    @Test
    public void testAuthUserWithoutCookiesAlreadyExist(){
        AppUser appUser = createNewUser(false);
        Cookie[] cookies = {};
        AppUser appUserAuthorized = userService.auth(cookies);
        Assert.assertEquals(appUser.toString(), appUserAuthorized.toString());
    }
    //user don't exist
    @Test
    public void testAuthUserWithoutCookiesDontExist(){
        Cookie[] cookies = {};
        AppUser appUserAuthorized = userService.auth(cookies);
        Assert.assertNotNull(appUserAuthorized);
    }

    @Test
    public void testAllowOnlyOneUserWithoutCookies(){
        Cookie[] cookies = {};
        AppUser appUserAuthorized = userService.auth(cookies);
        AppUser appUserAuthorized2 = userService.auth(cookies);
        AppUser appUserAuthorized3 = userService.auth(cookies);
        AppUser appUserAuthorized4 = userService.auth(cookies);

        Assert.assertEquals(appUserAuthorized2.toString(), appUserAuthorized3.toString());
        Assert.assertEquals(appUserAuthorized3.toString(), appUserAuthorized4.toString());
    }

    private AppUser createNewUser(boolean cookies){
        AppUser appUser;
        if(cookies) {
            String token = tokenService.generate();
            appUser = new AppUser(token, LocalDateTime.now());
        } else {
            appUser = new AppUser(tokenService.tokenReplacementWhenCookiesSwitchOff, LocalDateTime.now());
        }
        return createUserIfNotExistAlready(appUser);
    }

    private AppUser createUserIfNotExistAlready(AppUser appUser){
        Optional<AppUser> appUserOptional = userRepository.findByToken(appUser.getToken());
        if(appUserOptional.isPresent()) {
            return appUserOptional.get();
        }
        userRepository.save(appUser);
        return appUser;
    }
}