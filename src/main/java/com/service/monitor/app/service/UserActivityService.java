package com.service.monitor.app.service;

import com.service.monitor.app.domain.Action;
import com.service.monitor.app.domain.Contact;
import com.service.monitor.app.domain.dto.ContactDto;
import com.service.monitor.app.domain.dto.PulseDto;
import com.service.monitor.app.domain.AppUser;
import com.service.monitor.app.repository.UserRepository;
import com.service.monitor.app.service.user.identity.authorizer.UserIdentityAuthorizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class UserActivityService {

    private Random random = new Random();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserIdentityAuthorizer identityAuthorizer;

    private Logger LOGGER = LoggerFactory.getLogger(UserActivityService.class);

    public boolean save(PulseDto pulseDto, Cookie[] cookies, String ipAdress) {
        if(pulseDto==null){
            return true;
        }
        AppUser appUser = identityAuthorizer.auth(cookies, ipAdress);
        saveAction(pulseDto, appUser);
        return true;
    }

    public boolean saveContact(ContactDto contactDto, Cookie[] cookies, String ipAdress) {
        AppUser appUser = identityAuthorizer.auth(cookies, ipAdress);
        appUser.contacts.add(
                new Contact(contactDto.getName(),
                        contactDto.getEmail(),
                        contactDto.getMessage(),
                        appUser));

        userRepository.save(appUser);
        return true;
    }

    private void saveAction(PulseDto pulseDto, AppUser user){
        List<Action> actions = mapDtoToActionList(pulseDto.getActions(), user);
        user.actions.addAll(actions);
        user.updateLastActive();
        userRepository.save(user);
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
}
