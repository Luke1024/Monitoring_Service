package com.service.monitor.app.service;

import com.service.monitor.app.domain.Action;
import com.service.monitor.app.domain.ReportDto;
import com.service.monitor.app.domain.User;
import com.service.monitor.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StatsService {

    @Autowired
    private UserRepository userRepository;

    public ReportDto getWeeklyReport(int daysFromNow){
        List<User> recentUsers = new ArrayList<>();

        Iterable<User> users = userRepository.findAll();
        for(User user : users){
            if(isRecentUser(user, daysFromNow)){
                recentUsers.add(user);
            }
        }
        return buildReportOfRecentUsers(recentUsers);
    }

    public ReportDto getUserReport(long id){
        List<User> userOperations = new ArrayList<>();

        Optional<User> userOptional = userRepository.findById(id);

        if(userOptional.isPresent()){
            return buildUserReport(userOptional.get());
        } else {
            return new ReportDto(Collections.singletonList("User not found."));
        }
    }

    private ReportDto buildUserReport(User user){
        List<String> operations = user.getActions().stream()
                .map(action -> action.toString()).collect(Collectors.toList());
        return new ReportDto(operations);
    }

    private boolean isRecentUser(User user, int daysFromNow){
        List<Action> userActions = user.getActions();
        Optional<LocalDateTime> lastActionTimestamp = getUserLastActionTimestamp(userActions);
        if(lastActionTimestamp.isPresent()) {
            return isActionRecent(lastActionTimestamp.get(), daysFromNow);
        } else return false;
    }

    private Optional<LocalDateTime> getUserLastActionTimestamp(List<Action> userActions){
        if(userActions.size()>0) {
            return Optional.of(userActions.get(userActions.size() - 1).getTimeStamp());
        } else {
            return Optional.empty();
        }
    }

    private boolean isActionRecent(LocalDateTime lastActionTimestamp, int daysFromNow){
        return lastActionTimestamp.isAfter(LocalDateTime.now().minusDays(daysFromNow));
    }

    private ReportDto buildReportOfRecentUsers(List<User> recentUsers){
        List<String> userStats = new ArrayList<>();
        for(User user : recentUsers){
            userStats.add(generateSingleUserReport(user));
        }
        return new ReportDto(userStats);
    }

    private String generateSingleUserReport(User user){
        return "ID: " + user.getId() + ", OP: " + user.getActions().size();
    }
}
