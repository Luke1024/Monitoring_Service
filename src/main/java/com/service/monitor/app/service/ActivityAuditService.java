package com.service.monitor.app.service;

import com.service.monitor.app.domain.Action;
import com.service.monitor.app.domain.AppUser;
import com.service.monitor.app.domain.Contact;
import com.service.monitor.app.domain.IPAdress;
import com.service.monitor.app.domain.dto.mvc.ActionMvcDto;
import com.service.monitor.app.domain.dto.mvc.IpMvcDto;
import com.service.monitor.app.domain.dto.mvc.UserMvcDto;
import com.service.monitor.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ActivityAuditService {

    @Autowired
    private UserRepository userRepository;

    public List<UserMvcDto> getAllUsers() {
        Iterable<AppUser> appUsers = userRepository.findAll();
        return mapUsers(appUsers);
    }

    public List<UserMvcDto> getUsersActiveInRecentWeek() {
        Iterable<AppUser> appUsers = userRepository.findAll();
        return mapUsers(appUsers);
    }

    private List<UserMvcDto> mapUsers(Iterable<AppUser> appUsers){
        List<UserMvcDto> users = new ArrayList<>();
        for(AppUser user : appUsers){
            users.add(mapToUserDto(user));
        }
        return users;
    }

    public List<ActionMvcDto> getActionsByUserId(int id){
       Optional<AppUser> appUserOptional = getUserById(id);
       if(appUserOptional.isPresent()){
           return mapToActionMvcDtoList(appUserOptional.get().actions);
       } else return new ArrayList<>();
    }

    private List<ActionMvcDto> mapToActionMvcDtoList(List<Action> actions){
        List<ActionMvcDto> actionMvcDtos = new ArrayList<>();
        for(Action action : actions){
            actionMvcDtos.add(mapToMvcDto(action));
        }
        return actionMvcDtos;
    }

    private ActionMvcDto mapToMvcDto(Action action){
        return new ActionMvcDto(action.getTimeStamp().toString(), action.getAction());
    }

    public List<Contact> getUserContacts(long id) {
        Optional<AppUser> appUserOptional = getUserById(id);
        if(appUserOptional.isPresent()){
            return appUserOptional.get().contacts;
        } else return new ArrayList<>();
    }

    public List<IpMvcDto> getUserAdresses(long id){
        Optional<AppUser> appUserOptional = getUserById(id);
        if(appUserOptional.isPresent()){
            return mapToIpMvcDtoList(appUserOptional.get().ipAdresses);
        } else return new ArrayList<>();
    }

    private List<IpMvcDto> mapToIpMvcDtoList(List<IPAdress> ipAdresses){
        List<IpMvcDto> ipMvcDtos = new ArrayList<>();
        for(IPAdress ipAdress : ipAdresses){
            ipMvcDtos.add(mapToIpMvcDto(ipAdress));
        }
        return ipMvcDtos;
    }

    private IpMvcDto mapToIpMvcDto(IPAdress ipAdress){
        return new IpMvcDto(ipAdress.getFirstUsed().toString(), ipAdress.getAdress());
    }

    private UserMvcDto mapToUserDto(AppUser appUser) {
        return new UserMvcDto(
                appUser.id,
                getLastIpAdress(appUser),
                appUser.ipAdresses.size(),
                appUser.actions.size(),
                appUser.contacts.size(),
                appUser.lastActive.toString());
    }

    private String getLastIpAdress(AppUser appUser) {
        List<IPAdress> ipAdresses = appUser.ipAdresses;
        if(ipAdresses.size()>0){
            return ipAdresses.get(ipAdresses.size()-1).getAdress();
        } else return "";
    }

    public List<Action> getActionsByUserId(long id) {
        Optional<AppUser> userOptional = getUserById(id);
        if(userOptional.isPresent()){
            return userOptional.get().actions;
        } else {
            return new ArrayList<>();
        }
    }

    private Optional<AppUser> getUserById(long id){
        return userRepository.findById(id);
    }
}
