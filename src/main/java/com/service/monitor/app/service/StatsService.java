package com.service.monitor.app.service;

import com.service.monitor.app.domain.Action;
import com.service.monitor.app.domain.User;
import com.service.monitor.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatsService {

    @Autowired
    private UserRepository userRepository;

    public String getWeeklyReport(){
        List<User> weeklyUsers = new ArrayList<>();

        Iterable<User> users = userRepository.findAll();
        for(User user : users){
            if(isWeeklyUser(user)){
                weeklyUsers.add(user);
            }
        }
        return "Reporting string";
    }

    private boolean isWeeklyUser(User user){
        List<Action> userActionCodes = user.getActionCodes();
        Action lastAction = userActionCodes.get(userActionCodes.size()-1);
        //continue here with timestamp checking

        return true;
    }
}
