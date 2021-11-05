package com.service.monitor.app.controller;

import com.service.monitor.app.domain.PulseDto;
import com.service.monitor.app.service.UserActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/input")
public class DeveloperPageInputController {

    @Autowired
    private UserActivityService userActivityService;

    @GetMapping(value="/token")
    public String getToken(){
        return userActivityService.getToken();
    }

    @GetMapping(value="/load")
    public void loadUserData(@RequestBody PulseDto pulseDto){
        userActivityService.save(pulseDto);
    }
}
