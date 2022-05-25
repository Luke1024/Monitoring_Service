package com.service.monitor.app.service;

import com.service.monitor.app.domain.Action;
import com.service.monitor.app.domain.AppUser;
import com.service.monitor.app.repository.Cache;
import com.service.monitor.app.repository.CachedRepository;
import com.service.monitor.app.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.Cookie;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserActivityServiceTest {

    @Autowired
    private UserActivityService userActivityService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private CookieFilter cookieFilter;

    @Autowired
    private UserService userService;

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
            userActivityService.saveAction(action, cookies);
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
            userActivityService.saveAction(action, cookies);
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
            userActivityService.saveAction(action, cookies);
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