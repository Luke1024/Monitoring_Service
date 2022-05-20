package com.service.monitor.app.controller;

import com.service.monitor.app.domain.dto.ContactDto;
import com.service.monitor.app.domain.dto.StringDto;
import com.service.monitor.app.service.UserActivityService;
import com.service.monitor.app.service.user.identity.authorizer.PreAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@CrossOrigin(origins = "https://luke1024.github.io", allowCredentials = "true")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/input")
public class DeveloperPageInputController {

    @Autowired
    private UserActivityService userActivityService;

    @Autowired
    private PreAuthService identityAuthorizer;

    private Logger LOGGER = LoggerFactory.getLogger(DeveloperPageInputController.class);

    @GetMapping(value="/auth")
    public void getToken(HttpServletRequest request, HttpServletResponse response){
        userActivityService.preAuth(request.getCookies(), response);
    }

    @PostMapping(value="/load")
    public boolean loadUserData(@RequestBody StringDto message, HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        return userActivityService.save(message.getMessage(), cookies);
    }

    @PostMapping(value="/contact")
    public boolean saveUserContact(@RequestBody ContactDto contactDto, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String ipAdress = request.getRemoteAddr();
        return userActivityService.saveContact(contactDto, cookies, ipAdress);
    }
}
