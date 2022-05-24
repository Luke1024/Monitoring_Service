package com.service.monitor.app.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.Cookie;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CookieFilterTest {

    @Autowired
    private CookieFilter cookieFilter;

    @Autowired
    private TokenService tokenService;

    @Test
    public void filterMultipleCookies() {
        Cookie cookie1 = new Cookie(cookieFilter.authCookieName, tokenService.generate());
        Cookie cookie2 = new Cookie(cookieFilter.authCookieName, tokenService.generate());
        Cookie cookie3 = new Cookie(cookieFilter.sessionCookieName, tokenService.generate());
        Cookie[] cookies = {cookie1, cookie2, cookie3};

        Assert.assertEquals(cookie1.getValue(), cookieFilter.filterCookiesToValue(cookies, cookieFilter.authCookieName).get());
    }
}
