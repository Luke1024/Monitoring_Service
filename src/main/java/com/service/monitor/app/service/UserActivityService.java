package com.service.monitor.app.service;

import com.service.monitor.app.domain.Action;
import com.service.monitor.app.domain.PulseDto;
import com.service.monitor.app.domain.User;
import com.service.monitor.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UserActivityService {

    private Random random = new Random();

    @Autowired
    private UserRepository userRepository;

    public String getToken(){
        return createUser();
    }

    public void save(PulseDto pulseDto){
        Optional<User> userOptional = findUser(pulseDto);
        if(userOptional.isPresent()){
            List<Action> actions = mapActivityCodesToAction(pulseDto.getActivityCodes(), userOptional.get());
            userOptional.get().getActionCodes().addAll(actions);
            userRepository.save(userOptional.get());
        }
    }

    private List<Action> mapActivityCodesToAction(List<String> activityCodes, User user){
        return activityCodes.stream().map(code -> new Action(LocalDateTime.now(), code, user)).collect(Collectors.toList());
    }

    private Optional<User> findUser(PulseDto pulseDto){
        if(pulseDto != null){
            String token = pulseDto.getToken();
            return userRepository.findByToken(token);
        }
        return Optional.empty();
    }

    private String createUser(){
        String token = generateToken();
        User newUser = new User(LocalDateTime.now(), token);
        userRepository.save(newUser);
        return token;
    }

    private String generateToken(){
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 15;
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}
