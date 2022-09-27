package com.service.monitor.app.service.monitoring;

import com.service.monitor.app.domain.AppUser;
import com.service.monitor.app.domain.Contact;
import com.service.monitor.app.domain.UserSession;
import com.service.monitor.app.domain.dto.ContactDto;
import com.service.monitor.app.domain.dto.monitoring.SessionDto;
import com.service.monitor.app.domain.dto.monitoring.UserStatusDto;
import com.service.monitor.app.repository.SessionRepository;
import com.service.monitor.app.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActivityService {

    private Logger logger = LoggerFactory.getLogger(ActivityService.class);


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    public void passAction(String actionDescription){
        logger.info(actionDescription);
    }

    public List<UserStatusDto> getUserStatuses(){
        List<UserStatusDto> userStatusDtos = new ArrayList<>();
        for(AppUser appUser : userRepository.findAll()){
            userStatusDtos.add(mapToStatusDto(appUser));
        }
        return userStatusDtos;
    }

    private UserStatusDto mapToStatusDto(AppUser user){
        return new UserStatusDto(user.getId(),
                user.getSessions().size(),
                user.getSessions().stream().map(session -> session.getId()).collect(Collectors.toList()),
                getLastSessionActionCount(user),
                user.getContacts().size(),
                user.getLastActive());
    }

    private int getLastSessionActionCount(AppUser user){
        Optional<UserSession> userSessionOptional = user.getLastSession();
        if(userSessionOptional.isPresent()){
            return userSessionOptional.get().getActions().size();
        } else return 0;
    }

    public List<ContactDto> getUserContacts(long userId){
        Optional<AppUser> appUserOptional = userRepository.findById(userId);
        if(appUserOptional.isPresent()){
            return mapToContactDtoList(appUserOptional.get().getContacts());
        } else return new ArrayList<>();
    }

    private List<ContactDto> mapToContactDtoList(List<Contact> contacts){
        return contacts.stream()
                .map(contact -> new ContactDto(contact.getName(), contact.getEmail(), contact.getMessage()))
                .collect(Collectors.toList());
    }

    public SessionDto getSession(long sessionId){
        Optional<UserSession> userSessionOptional = sessionRepository.findById(sessionId);
        if(userSessionOptional.isPresent()){
            return new SessionDto(userSessionOptional.get().getStarted(),
                    userSessionOptional.get().getLastActive(),
                    userSessionOptional.get().getActions().stream().map(action -> action.getAction()).collect(Collectors.toList()));
        } else return new SessionDto();
    }
}
