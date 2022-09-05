package com.service.monitor.app.service;

import com.service.monitor.app.domain.Action;
import com.service.monitor.app.domain.AppUser;
import com.service.monitor.app.domain.Contact;
import com.service.monitor.app.domain.dto.ContactDto;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

        Cookie[] cookies = {new Cookie(cookieFilter.authCookieName,appUser.getToken())};

        ContactDto contactDto = new ContactDto("name","email","message");

        userService.saveContact(contactDto, cookies);

        Contact savedUserContact = userService.findUserByToken(appUser.getToken()).get().getContacts().get(0);

        Assert.assertEquals(contactDto.toString(), new ContactDto(savedUserContact.getName(), savedUserContact.getEmail(), savedUserContact.getMessage()).toString());
    }

    //auth test suite

    //token is present

    //user already exist
    @Test
    public void testAuthUserWithCookiesAlreadyExist(){
        AppUser appUser = createNewUser(true);
        Cookie[] cookies = {new Cookie(cookieFilter.authCookieName, appUser.getToken())};
        AppUser appUserAuthorized = userService.getOrCreateUserBasedOnToken(cookies);
        Assert.assertEquals(appUser.getToken(), appUser.getToken());
    }
    //user don't exist
    @Test
    public void testAuthUserWithCookiesDontExist(){
        String token = tokenService.generate();
        Cookie[] cookies = {new Cookie(cookieFilter.authCookieName, token)};
        AppUser appUserAuthorized = userService.getOrCreateUserBasedOnToken(cookies);
        Assert.assertEquals(token, appUserAuthorized.getToken());
    }
    //token is not present

    //user already exist
    @Test
    public void testAuthUserWithoutCookiesAlreadyExist(){
        AppUser appUser = createNewUser(false);
        Cookie[] cookies = {};
        AppUser appUserAuthorized = userService.getOrCreateUserBasedOnToken(cookies);
        Assert.assertEquals(appUser.toString(), appUserAuthorized.toString());
    }
    //user don't exist
    @Test
    public void testAuthUserWithoutCookiesDontExist(){
        Cookie[] cookies = {};
        AppUser appUserAuthorized = userService.getOrCreateUserBasedOnToken(cookies);
        Assert.assertNotNull(appUserAuthorized);
    }

    @Test
    public void testAllowOnlyOneUserWithoutCookies(){
        Cookie[] cookies = {};
        AppUser appUserAuthorized = userService.getOrCreateUserBasedOnToken(cookies);
        AppUser appUserAuthorized2 = userService.getOrCreateUserBasedOnToken(cookies);
        AppUser appUserAuthorized3 = userService.getOrCreateUserBasedOnToken(cookies);
        AppUser appUserAuthorized4 = userService.getOrCreateUserBasedOnToken(cookies);

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

    @Test
    public void actionSavingWhenUserExist(){
        String token = tokenService.generate();
        String sessionToken = tokenService.generate();
        AppUser appUser = new AppUser(token, LocalDateTime.now());
        userRepository.save(appUser);

        Cookie[] cookies = {
                new Cookie(cookieFilter.authCookieName, token),
                new Cookie(cookieFilter.sessionCookieName, sessionToken)};

        List<String> actionList = generateAction(20, 3);

        for(String action : actionList){
            userService.saveAction(action, cookies);
        }

        Assert.assertEquals(actionList.toString(),
                userService.findUserByToken(token).get().getLastSession().get().getActions().toString());
    }

    @Test
    public void actionSavingWhenUserDontExist(){
        String token = tokenService.generate();

        Cookie[] cookies = {
                new Cookie(cookieFilter.authCookieName, token)};

        List<String> actionList = generateAction(20, 3);

        for(String action : actionList){
            userService.saveAction(action, cookies);
        }

        Assert.assertEquals(actionList.toString(),
                userService.findUserByToken(token).get().getLastSession().get().getActions().toString());
    }

    @Test
    public void actionSavingWhenCookiesSwitchOff(){
        int actionCount = 20;

        List<String> actionList = generateAction(actionCount, 3);

        Cookie[] cookies = {};
        for(String action : actionList){
            userService.saveAction(action, cookies);
        }

        //only on user without cookies

        List<Action> allUserWithoutCookiesActions =
                userService.findUserByToken(tokenService.tokenReplacementWhenCookiesSwitchOff).get().getLastSession().get().getActions();
        List<String> receivedActions = getLastBatchOfActions(allUserWithoutCookiesActions, actionCount);

        Assert.assertEquals(actionList.toString(), receivedActions.toString());
    }

    private List<String> getLastBatchOfActions(List<Action> actions, int actionCount){
        int size = actions.size();
        return actions.subList(size-actionCount, size).stream().map(action -> action.getAction()).collect(Collectors.toList());
    }

    private List<String> generateAction(int actionCount, int actionBlocks){
        List<String> actions = new ArrayList<>();
        for(int i=0; i<actionCount; i++){
            String singleAction = "";
            for(int j=0; j<actionBlocks; j++) {
                singleAction += tokenService.generate();
            }
            actions.add(singleAction);
        }
        return actions;
    }
}