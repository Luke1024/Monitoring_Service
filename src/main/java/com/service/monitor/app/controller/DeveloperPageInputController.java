package com.service.monitor.app.controller;

import com.service.monitor.app.domain.dto.ContactDto;
import com.service.monitor.app.domain.dto.PulseDto;
import com.service.monitor.app.service.UserActivityService;
import com.service.monitor.app.service.user.identity.authorizer.UserIdentityAuthorizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/input")
public class DeveloperPageInputController {

    @Autowired
    private UserActivityService userActivityService;

    @Autowired
    private UserIdentityAuthorizer identityAuthorizer;

    private Logger LOGGER = LoggerFactory.getLogger(DeveloperPageInputController.class);

    @GetMapping(value="/auth")
    public void getToken(HttpServletRequest request, HttpServletResponse response){
        identityAuthorizer.preAuth(request.getCookies(), response);
    }

    @PostMapping(value="/load")
    public boolean loadUserData(@RequestBody PulseDto pulseDto, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String ipAdress = request.getRemoteAddr();
        LOGGER.info(pulseDto.toString());
        return userActivityService.save(pulseDto, cookies, ipAdress);
    }

    @PutMapping(value="/contact")
    public boolean saveUserContact(@RequestBody ContactDto contactDto, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String ipAdress = request.getRemoteAddr();
        LOGGER.info(contactDto.toString());
        return userActivityService.saveContact(contactDto, cookies, ipAdress);
    }
}
