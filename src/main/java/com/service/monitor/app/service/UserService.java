package com.service.monitor.app.service;

import com.service.monitor.app.domain.Contact;
import com.service.monitor.app.domain.dto.ContactDto;
import com.service.monitor.app.domain.AppUser;
import com.service.monitor.app.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private PreAuthService identityAuthorizer;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    private CookieFilter cookieFilter;

    @Autowired
    private TokenService tokenService;

    private Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    public void preAuth(Cookie[] cookies, HttpServletResponse response) {
        identityAuthorizer.preAuth(cookies, response);
    }

    public synchronized boolean saveAction(String action, Cookie[] cookies) {
        AppUser appUser = getOrCreateUserBasedOnToken(cookies);
        Optional<String> sessionToken = cookieFilter.filterCookiesToValue(cookies, cookieFilter.sessionCookieName);
        sessionManager.addSessionIfNecessary(appUser, sessionToken);
        addActionToLastSession(appUser,action);
        return true;
    }

    public synchronized Optional<AppUser> findUserByToken(String token){
        return userRepository.findByToken(token);
    }

    public synchronized boolean saveContact(ContactDto contactDto, Cookie[] cookies) {
        AppUser appUser = getOrCreateUserBasedOnToken(cookies);
        Contact contact = new Contact(contactDto.getName(),
                        contactDto.getEmail(),
                        contactDto.getMessage(),
                        appUser);

        appUser.addContact(contact);
        userRepository.save(appUser);
        LOGGER.info("Contact added: " + contactDto.toString() + ", by user with id:" +
                appUser.getId() + ", token: " + appUser.getToken());
        return true;
    }

    private void addActionToLastSession(AppUser appUser,String action){
        appUser.getLastSession().get().addAction(action);
        userRepository.save(appUser);
    }

    private AppUser createUser(String token){
        if( ! tokenService.checkIfTokenWasGeneratedInAuthAndRemove(token)){
            if( ! token.equals(tokenService.tokenReplacementWhenCookiesSwitchOff)) {
                LOGGER.warn("Someone generated valid token from outside.");
            }
        }
        AppUser user = new AppUser(token, LocalDateTime.now());
        userRepository.save(user);
        LOGGER.info("Authorized new user with token: " + user.getToken());
        return user;
    }

    protected AppUser getOrCreateUserBasedOnToken(Cookie[] cookies){
        Optional<String> token = cookieFilter.filterCookiesToValue(cookies, cookieFilter.authCookieName);
        if(token.isPresent()){
            return findOrCreateUser(token.get());
        } else {
            return findOrCreateUser(tokenService.tokenReplacementWhenCookiesSwitchOff);
        }
    }

    private AppUser findOrCreateUser(String token){
        Optional<AppUser> appUserOptional = userRepository.findByToken(token);
        if(appUserOptional.isPresent()){
            return appUserOptional.get();
        } else {
            return createUser(token);
        }
    }
}
