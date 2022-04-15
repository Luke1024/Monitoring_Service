package com.service.monitor.app.controller;

import com.service.monitor.app.domain.dto.ContactDto;
import com.service.monitor.app.domain.dto.PulseDto;
import com.service.monitor.app.service.UserActivityService;
import com.service.monitor.app.service.UserIdentityAuthorizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        identityAuthorizer.preAuth(request, response);
    }

    @PostMapping(value="/load")
    public boolean loadUserData(@RequestBody PulseDto pulseDto, HttpServletRequest request){
        LOGGER.info(pulseDto.toString());
        return userActivityService.save(pulseDto, request);
    }

    @PutMapping(value="/contact")
    public boolean saveUserContact(@RequestBody ContactDto contactDto, HttpServletRequest request){
        LOGGER.info(contactDto.toString());
        return userActivityService.saveContact(contactDto, request);
    }
}
