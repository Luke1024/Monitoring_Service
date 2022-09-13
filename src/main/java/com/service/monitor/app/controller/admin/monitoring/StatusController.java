package com.service.monitor.app.controller.admin.monitoring;

import com.service.monitor.app.domain.dto.monitoring.StatusDto;
import com.service.monitor.app.service.AdminAuthService;
import com.service.monitor.app.service.monitoring.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/status")
public class StatusController {

    @Autowired
    private StatusService statusService;

    @Autowired
    private AdminAuthService adminAuthService;

    @GetMapping(value = "/status/{adminKey}")
    public ResponseEntity<StatusDto> getActivityStatus(@PathVariable String adminKey){
        if(authorize(adminKey)){
            return ResponseEntity.ok(statusService.getStatusDto());
        } else return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "/status/{adminKey}")
    public void resetActivityStatus(@PathVariable String adminKey){
        if(authorizeDelete(adminKey)){
            statusService.resetStatusDto();
        }
    }

    private boolean authorize(String adminKey){
        return adminAuthService.authorize(adminKey);
    }

    private boolean authorizeDelete(String adminDeleteKey){
        return adminAuthService.authorizeDelete(adminDeleteKey);
    }
}
