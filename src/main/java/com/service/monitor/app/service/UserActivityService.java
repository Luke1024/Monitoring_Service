package com.service.monitor.app.service;

import com.service.monitor.app.domain.Action;
import com.service.monitor.app.domain.Contact;
import com.service.monitor.app.domain.dto.ContactDto;
import com.service.monitor.app.domain.dto.PulseDto;
import com.service.monitor.app.domain.AppUser;
import com.service.monitor.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

    public boolean save(PulseDto pulseDto, String ipAdress){
        if(pulseDto != null){
            Optional<AppUser> userOptional = findUser(pulseDto.getToken());
            if(userOptional.isPresent()) {
                List<Action> actions = mapDtoToActionList(pulseDto.getActions(), userOptional.get());
                userOptional.get().getActions().addAll(actions);
                userRepository.save(userOptional.get());
                return true;
            }
        }
        return false;
    }

    public boolean saveContact(ContactDto contactDto) {
        Optional<AppUser> appUserOptional = findUser(contactDto.getToken());
        if(appUserOptional.isPresent()){
            appUserOptional.get().getContacts().add(
                    new Contact(contactDto.getName(),
                            contactDto.getEmail(),
                            contactDto.getMessage(),
                            appUserOptional.get()));

            userRepository.save(appUserOptional.get());
            return true;
        } else return false;
    }

    private List<Action> mapDtoToActionList(List<String> actionsString, AppUser appUser) {
        List<Action> actions = new ArrayList<>();
        for (int i = 0; i < actionsString.size(); i++) {
            actions.add(mapToAction(actionsString.get(i), appUser));
        }
        return actions;
    }

    private Action mapToAction(String action, AppUser appUser){
        return new Action(LocalDateTime.now(), action, appUser);
    }

    private Optional<AppUser> findUser(String token){
        return userRepository.findByToken(token);
    }

    private String createUser(){
        String token = generateToken();
        AppUser newAppUser = new AppUser(token);
        userRepository.save(newAppUser);
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
