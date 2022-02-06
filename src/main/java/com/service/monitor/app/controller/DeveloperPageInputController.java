package com.service.monitor.app.controller;

import com.service.monitor.app.domain.dto.ContactDto;
import com.service.monitor.app.domain.dto.PulseDto;
import com.service.monitor.app.domain.dto.StringDto;
import com.service.monitor.app.service.UserActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/input")
public class DeveloperPageInputController {

    @Autowired
    private UserActivityService userActivityService;

    private Logger LOGGER = LoggerFactory.getLogger(DeveloperPageInputController.class);

    @GetMapping(value="/status")
    public boolean status(){
        return true;
    }

    @GetMapping(value="/token")
    public StringDto getToken(HttpServletRequest request){
        String token = userActivityService.getToken(request.getRemoteAddr());
        return new StringDto(token);
    }

    @PostMapping(value="/load")
    public boolean loadUserData(@RequestBody PulseDto pulseDto){
        LOGGER.info(pulseDto.toString());
        return userActivityService.save(pulseDto);
    }

    @PutMapping(value="/contact")
    public boolean saveUserContact(@RequestBody ContactDto contactDto){
        LOGGER.info(contactDto.toString());
        return userActivityService.saveContact(contactDto);
    }
}
