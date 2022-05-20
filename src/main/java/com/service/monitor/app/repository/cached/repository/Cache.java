package com.service.monitor.app.repository.cached.repository;

import com.service.monitor.app.domain.AppUser;
import com.service.monitor.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class Cache {

    public List<AppUser> users = new ArrayList<>();

    private LocalDateTime now;
    private int minimumTimeOfInactivityInMinutes = 5;

    @Autowired
    private UserRepository userRepository;

    @Scheduled(fixedRate = 900000)
    private void update(){
        if(isCacheActive()){
            saveActiveUsersToDatabase();
            removeInactiveUsersFromCache();
        }
    }

    private boolean isCacheActive(){
        return users.size()>0;
    }

    private void saveActiveUsersToDatabase(){
        userRepository.saveAll(users);
    }

    private void removeInactiveUsersFromCache(){
        List<AppUser> inactiveUsers = new ArrayList<>();
        now = LocalDateTime.now();
        for(AppUser user : users){
            if(isUserInactive(user)){
                inactiveUsers.add(user);
            }
        }
        users.removeAll(inactiveUsers);
    }

    private boolean isUserInactive(AppUser user){
        return user.getLastActive().plusMinutes(minimumTimeOfInactivityInMinutes).isAfter(now);
    }
}
