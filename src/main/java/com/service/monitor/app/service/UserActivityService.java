package com.service.monitor.app.service;

import com.service.monitor.app.domain.Action;
import com.service.monitor.app.domain.PulseDto;
import com.service.monitor.app.domain.User;
import com.service.monitor.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class UserActivityService {

    private Random random = new Random();

    @Autowired
    private UserRepository userRepository;

    public String getToken(){
        return createUser();
    }

    public boolean save(PulseDto pulseDto){
        if(pulseDto != null){
            Optional<User> userOptional = findUser(pulseDto.getToken());
            if(userOptional.isPresent()) {
                Action action = mapActivityCodesToAction(pulseDto.getCode(), userOptional.get());
                userOptional.get().getActions().add(action);
                userRepository.save(userOptional.get());
                return true;
            }
        }
        return false;
    }

    private Action mapActivityCodesToAction(String code, User user){
        return new Action(LocalDateTime.now(), code, user);
    }

    private Optional<User> findUser(String token){
        return userRepository.findByToken(token);
    }

    private String createUser(){
        String token = generateToken();
        User newUser = new User(token);
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
