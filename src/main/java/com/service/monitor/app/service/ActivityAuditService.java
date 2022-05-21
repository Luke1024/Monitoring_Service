package com.service.monitor.app.service;

import com.service.monitor.app.domain.*;
import com.service.monitor.app.domain.dto.mvc.IpMvcDto;
import com.service.monitor.app.domain.dto.mvc.SessionMvcDto;
import com.service.monitor.app.domain.dto.mvc.UserMvcDto;
//import com.service.monitor.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityAuditService {

  //  @Autowired
    //private UserRepository userRepository;

    public List<UserMvcDto> getAllUsers() {
        //Iterable<AppUser> appUsers = userRepository.findAll();
        //return mapUsers(appUsers);
        return new ArrayList<>();
    }

    public List<UserMvcDto> getUsersActiveInRecentWeek() {
        //Iterable<AppUser> appUsers = userRepository.findAll();
        //return mapUsers(appUsers);
        return new ArrayList<>();
    }
/*
    private List<UserMvcDto> mapUsers(Iterable<AppUser> appUsers){
        List<UserMvcDto> users = new ArrayList<>();
        for(AppUser user : appUsers){
            users.add(mapToUserDto(user));
        }
        return users;
    }
 */

    public List<SessionMvcDto> getActionsByUserId(int id) {
      //  Optional<AppUser> appUserOptional = getUserById(id);
        //if (appUserOptional.isPresent()) {
          //  return mapToSessionMvcDtoList(appUserOptional.get().getSessions());
        //} else return new ArrayList<>();
        return new ArrayList<>();
    }
/*
    private List<SessionMvcDto> mapToSessionMvcDtoList(List<UserSession> sessions){
        List<SessionMvcDto> sessionMvcDtos = new ArrayList<>();
        for(UserSession session : sessions){
            sessionMvcDtos.add(mapToMvcDto(session));
        }
        return sessionMvcDtos;
    }

    private SessionMvcDto mapToMvcDto(UserSession userSession){
        return new SessionMvcDto(
                userSession.getActions().size(),
                userSession.getStarted().toString(),
                userSession.getEnded().toString(),
                userSession.isActive(),
                userSession.getAppUser().id);
    }
*/

    public List<Contact> getUserContacts(long id) {
    //    Optional<AppUser> appUserOptional = getUserById(id);
      //  if (appUserOptional.isPresent()) {
        //    return appUserOptional.get().contacts;
        //} else return new ArrayList<>();
        return new ArrayList<>();
    }

    public List<IpMvcDto> getUserAdresses(long id) {
        //Optional<AppUser> appUserOptional = getUserById(id);
        //if (appUserOptional.isPresent()) {
         //   return mapToIpMvcDtoList(appUserOptional.get().ipAdresses);
        //} else return new ArrayList<>();
        return new ArrayList<>();
    }
    /*
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
                appUser.getId(),
                getLastIpAdress(appUser),
                appUser.ipAdresses.size(),
                appUser.sessions.size(),
                appUser.contacts.size(),
                appUser.lastActive.toString());
    }

    private String getLastIpAdress(AppUser appUser) {
        List<IPAdress> ipAdresses = appUser.ipAdresses;
        if(ipAdresses.size()>0){
            return ipAdresses.get(ipAdresses.size()-1).getAdress();
        } else return "";
    }
    */

    public List<Action> getActionsByUserId(long id) {
        //    Optional<AppUser> userOptional = getUserById(id);
        //  if(userOptional.isPresent()){
        //    return userOptional.get().actions;
        //    } else {
        //      return new ArrayList<>();
        // }
        return new ArrayList<>();
    }
}
/*
    private Optional<AppUser> getUserById(long id){
        return userRepository.findById(id);
    }
}
*/
