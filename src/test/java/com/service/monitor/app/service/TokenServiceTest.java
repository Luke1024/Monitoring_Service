package com.service.monitor.app.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TokenServiceTest {

    @Autowired
    private TokenService tokenService;

    @Test
    public void checkIfTokenWasAddedWhenItWasnt(){
        String token = tokenService.generate();
        Assert.assertFalse(tokenService.checkIfTokenWasGeneratedInAuthAndRemove(token));
    }

    @Test
    public void addTokenAndCheckIfTokenWasAddedAndThenIfWasRemoved(){
        String token = tokenService.generate();
        tokenService.addTokenToPreAuth(token);
        Assert.assertTrue(tokenService.checkIfTokenWasGeneratedInAuthAndRemove(token));
        Assert.assertFalse(tokenService.checkIfTokenWasGeneratedInAuthAndRemove(token));
    }
}