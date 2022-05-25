package com.service.monitor.app.repository;

import com.service.monitor.app.domain.AppUser;
import com.service.monitor.app.service.TokenService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CachedRepositoryTest {

    @Autowired
    private CachedRepository cachedRepository;

    @Autowired
    private Cache cache;

    @Autowired
    private TokenService tokenService;

    @Test
    public void testCachedUserUpdate() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        String token = tokenService.generate();
        AppUser appUser = new AppUser(token, LocalDateTime.now());

        cachedRepository.saveUser(appUser);

        //cached only
        Assert.assertEquals(0, appUser.getId());

        Method update = cache.getClass().getDeclaredMethod("update");
        update.setAccessible(true);
        update.invoke(cache);

        //should be still in cache with changed id
        Assert.assertNotEquals(0, appUser.getId());

        //should retrieving user from cache
        Assert.assertEquals(appUser.getId(), cachedRepository.findUserByToken(token).get().getId());
    }

    @Test
    public void testRemovingUserFromCache() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String token = tokenService.generate();
        AppUser appUser = new AppUser(token, LocalDateTime.now());

        cachedRepository.saveUser(appUser);

        //cached only
        Assert.assertEquals(0, appUser.getId());

        Method update = cache.getClass().getDeclaredMethod("update");
        update.setAccessible(true);
        update.invoke(cache);

        //should be still in cache with changed id
        Assert.assertNotEquals(0, appUser.getId());

        appUser.getLastActive().minusMinutes(cache.getMinimumTimeOfInactivityInMinutes()+5);
        //user now considered inactive

        update.invoke(cache);
        //user now should be removed from cache

        Assert.assertTrue(findUserInCache(token).size()==0);

        //find user by token
        AppUser userReceivedFromRepository = cachedRepository.findUserByToken(token).get();

        Assert.assertEquals(token, userReceivedFromRepository.getToken());

        //check if user loaded to cache
        Assert.assertTrue(findUserInCache(token).size()>0);
    }

    private List<AppUser> findUserInCache(String token){
        return cache.users.stream().filter(appUser -> appUser.getToken().equals(token)).collect(Collectors.toList());
    }
}