package com.service.monitor.app.service;

import com.service.monitor.app.domain.AppUser;
import com.service.monitor.app.domain.Contact;

import com.service.monitor.app.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@Service
class UserService {

    @Autowired
    private CookieFilter cookieFilter;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    public void saveContact(Contact contact){
        contact.getAppUser().addContact(contact);
        userRepository.save(contact.getAppUser());
    }

    public Optional<AppUser> findUserByToken(String token){
        return userRepository.findByToken(token);
    }

    public AppUser auth(Cookie[] cookies){
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
}
