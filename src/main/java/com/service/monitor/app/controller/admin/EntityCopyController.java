package com.service.monitor.app.controller.admin;

import com.service.monitor.app.domain.AppUser;
import com.service.monitor.app.repository.UserRepository;
import com.service.monitor.app.service.AdminAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/entity")
public class EntityCopyController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminAuthService adminAuthService;

    @GetMapping(value = "/user/{id}/{authKey}")
    public ResponseEntity<AppUser> getFullUserEntity(@PathVariable long id, @PathVariable String authKey){
        if(adminAuthService.authorize(authKey)){
            Optional<AppUser> appUserOptional = userRepository.findById(id);
            if(appUserOptional.isPresent()){
                return ResponseEntity.ok(appUserOptional.get());
            }
        }
        return ResponseEntity.badRequest().build();
    }
}
