package com.service.monitor.app.controller;

import com.service.monitor.app.domain.dto.ContactDto;
import com.service.monitor.app.domain.dto.StringDto;
import com.service.monitor.app.service.UserService;
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
    private UserService userService;

    private Logger LOGGER = LoggerFactory.getLogger(DeveloperPageInputController.class);

    @GetMapping(value="/auth")
    public void getToken(HttpServletRequest request, HttpServletResponse response){
        userService.preAuth(request.getCookies(), response);
    }

    @PostMapping(value="/load")
    public boolean loadUserData(@RequestBody StringDto message, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        return userService.saveAction(message.getMessage(), cookies);
    }

    @PostMapping(value="/contact")
    public boolean saveUserContact(@RequestBody ContactDto contactDto, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        return userService.saveContact(contactDto, cookies);
    }
}
