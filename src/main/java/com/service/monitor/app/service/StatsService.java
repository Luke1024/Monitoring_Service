package com.service.monitor.app.service;

import com.service.monitor.app.domain.Action;
import com.service.monitor.app.domain.dto.ReportDto;
import com.service.monitor.app.domain.AppUser;
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
        List<AppUser> recentAppUsers = new ArrayList<>();

        Iterable<AppUser> users = userRepository.findAll();
        for(AppUser appUser : users){
            if(isRecentUser(appUser, daysFromNow)){
                recentAppUsers.add(appUser);
            }
        }
        return buildReportOfRecentUsers(recentAppUsers);
    }

    public ReportDto getUserReport(long id){
        List<AppUser> appUserOperations = new ArrayList<>();

        Optional<AppUser> userOptional = userRepository.findById(id);

        if(userOptional.isPresent()){
            return buildUserReport(userOptional.get());
        } else {
            return new ReportDto(Collections.singletonList("User not found."));
        }
    }

    private ReportDto buildUserReport(AppUser appUser){
        List<String> operations = appUser.getActions().stream()
                .map(action -> action.toString()).collect(Collectors.toList());
        return new ReportDto(operations);
    }

    private boolean isRecentUser(AppUser appUser, int daysFromNow){
        List<Action> userActions = appUser.getActions();
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

    private ReportDto buildReportOfRecentUsers(List<AppUser> recentAppUsers){
        List<String> userStats = new ArrayList<>();
        for(AppUser appUser : recentAppUsers){
            userStats.add(generateSingleUserReport(appUser));
        }
        return new ReportDto(userStats);
    }

    private String generateSingleUserReport(AppUser appUser){
        return "ID: " + appUser.getId() + ", OP: " + appUser.getActions().size();
    }
}
