package com.service.monitor.app.service;

import com.service.monitor.app.domain.Action;
import com.service.monitor.app.domain.ReportDto;
import com.service.monitor.app.domain.User;
import com.service.monitor.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatsService {

    @Autowired
    private UserRepository userRepository;

    public ReportDto getWeeklyReport(){
        List<User> weeklyUsers = new ArrayList<>();

        Iterable<User> users = userRepository.findAll();
        for(User user : users){
            if(isWeeklyUser(user)){
                weeklyUsers.add(user);
            }
        }
        return buildWeeklyReportOfUsers(weeklyUsers);
    }

    private boolean isWeeklyUser(User user){
        List<Action> userActions = user.getActions();
        LocalDateTime lastActionTimestamp = getUserLastActionTimestamp(userActions);
        return isActionWasNotEarlierThanAWeek(lastActionTimestamp);
    }

    private LocalDateTime getUserLastActionTimestamp(List<Action> userActions){
        return userActions.get(userActions.size()-1).getTimeStamp();
    }

    private boolean isActionWasNotEarlierThanAWeek(LocalDateTime lastActionTimestamp){
        return lastActionTimestamp.isAfter(LocalDateTime.now().minusDays(7));
    }

    private ReportDto buildWeeklyReportOfUsers(List<User> weeklyUsers){
        List<String> userStats = new ArrayList<>();
        for(User user : weeklyUsers){
            userStats.add(generateSingleUserReport(user));
        }
        return new ReportDto(userStats);
    }

    private String generateSingleUserReport(User user){
        return "ID: " + user.getId() + ", OP: " + user.getActions().size();
    }
}
