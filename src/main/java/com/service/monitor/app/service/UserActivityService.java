package com.service.monitor.app.service;

import com.service.monitor.app.domain.Action;
import com.service.monitor.app.domain.Contact;
import com.service.monitor.app.domain.dto.ContactDto;
import com.service.monitor.app.domain.dto.PulseDto;
import com.service.monitor.app.domain.AppUser;
import com.service.monitor.app.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    private List<String> tokensInPreAuth = new ArrayList<>();
    private String authCookieName = "AUTH";
    private int tokenLenght = 16;

    private Logger LOGGER = LoggerFactory.getLogger(UserActivityService.class);

    public void userAuth(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        if(cookies.length>0){
            Optional<String> token = filterValidCookiesToToken(cookies);
            if(token.isPresent()){
                if(findUser(token.get()).isPresent()){
                    return;
                }
            }
        }
        String token = generateToken();
        tokensInPreAuth.add(token);
        response.addCookie(new Cookie(authCookieName, token));
    }

    private Optional<String> filterValidCookiesToToken(Cookie[] cookies){
        List<String> tokenList = new ArrayList<>();
        for(Cookie cookie : cookies){
            if(cookie.getName()==authCookieName){
                if(cookie.getValue().length()==tokenLenght) {
                    tokenList.add(cookie.getValue());
                }
            }
        }
        if(tokenList.size()>0) {
            if(tokenList.size()>1) {
                LOGGER.warn("Double auth token detected.");
            }
            return Optional.of(tokenList.get(0));
        }
        return Optional.empty();
    }

    private boolean checkIfUserWithTokenExists(String token){
        Optional<AppUser> appUserOptional = userRepository.findByToken(token);
        return appUserOptional.isPresent();
    }

    public void save(PulseDto pulseDto, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String ip = request.getRemoteAddr();
        if (cookies.length > 0) {
            saveBasedOnToken(pulseDto, cookies, ip);
        } else {
            saveBasedOnIp(pulseDto, ip);
        }
    }


    private void saveBasedOnToken(PulseDto pulseDto, Cookie[] cookies, String ipAdress) {
        Optional<String> token = filterValidCookiesToToken(cookies);
        if(token.isPresent()) {
            saveAction(pulseDto, ipAdress, token.get());
        }
    }



    private boolean saveAction(PulseDto pulseDto, String ipAdress, String token) {
        Optional<AppUser> userOptional = findUser(token);
        if(pulseDto != null){
            if(userOptional.isPresent()) {
                List<Action> actions = mapDtoToActionList(pulseDto.getActions(), userOptional.get());
                userOptional.get().actions.addAll(actions);
                userOptional.get().updateLastActive();
                userRepository.save(userOptional.get());
                return true;
            } else {
                if(checkIfTokenWasGeneratedInAuth(token)) {
                    createUser(ipAdress, token);
                    saveAction(pulseDto, ipAdress, token);
                }
            }
        }
        return false;
    }

    private boolean checkIfTokenWasGeneratedInAuth(String token){
        int index = tokensInPreAuth.indexOf(token);
        if(index != -1) {
            tokensInPreAuth.remove(tokensInPreAuth);
            return true;
        } else return false;
    }



    public boolean saveContact(ContactDto contactDto) {
        Optional<AppUser> appUserOptional = findUser(contactDto.getToken());
        if(appUserOptional.isPresent()){
            appUserOptional.get().contacts.add(
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

    private void createUser(String ip, String token){
        AppUser newAppUser = new AppUser(token,ip, LocalDateTime.now());
        userRepository.save(newAppUser);
    }

    private String generateToken(){
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = tokenLenght;
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}
