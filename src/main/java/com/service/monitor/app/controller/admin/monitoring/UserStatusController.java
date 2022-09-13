package com.service.monitor.app.controller.admin.monitoring;

import com.service.monitor.app.domain.dto.ContactDto;
import com.service.monitor.app.domain.dto.monitoring.SessionDto;
import com.service.monitor.app.domain.dto.monitoring.UserStatusDto;
import com.service.monitor.app.service.AdminAuthService;
import com.service.monitor.app.service.monitoring.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/user_status")
public class UserStatusController {

    @Autowired
    private AdminAuthService adminAuthService;

    @Autowired
    private ActivityService activityService;

    @GetMapping(value = "users/{adminKey}")
    public List<UserStatusDto> getUserStatuses(@PathVariable String adminKey){
        if(authorize(adminKey)){
            return activityService.getUserStatuses();
        } else return new ArrayList<>();
    }

    @GetMapping(value = "contact/{userId}/{adminKey}")
    public List<ContactDto> getUserContacts(@PathVariable long userId, @PathVariable String adminKey){
        if(authorize(adminKey)){
            return activityService.getUserContacts(userId);
        } else return new ArrayList<>();
    }

    @GetMapping(value = "session/{sessionId}/{adminKey}")
    public SessionDto getUserSessions(@PathVariable long sessionId, @PathVariable String adminKey){
        if(authorize(adminKey)){
            return activityService.getSession(sessionId);
        } else {
            return new SessionDto();
        }
    }

    private boolean authorize(String adminKey){
        return adminAuthService.authorize(adminKey);
    }
}
