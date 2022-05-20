package com.service.monitor.app.service.user.identity.authorizer.user.service;

import com.service.monitor.app.domain.AppUser;
import com.service.monitor.app.domain.Contact;
import com.service.monitor.app.repository.cached.repository.CachedRepository;
import com.service.monitor.app.service.user.identity.authorizer.CookieFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private CachedRepository cachedRepository;

    @Autowired
    private CookieFilter cookieFilter;

    @Autowired
    private UserFinder userFinder;

    @Autowired
    private UserCreator userCreator;

    public void save(AppUser user){
        cachedRepository.saveUser(user);
    }

    public void saveContact(AppUser appUser, Contact contact){
        appUser.addContact(contact);
    }

    public Optional<AppUser> findUserByToken(String token){
        return userFinder.findUserByToken(token);
    }

    public AppUser auth(Cookie[] cookies, String ipAdress){
        Optional<String> token = cookieFilter.filterCookiesToValue(cookies, cookieFilter.authCookieName);
        return findOrCreateUser(token, ipAdress);
    }

    public AppUser findOrCreateUser(Optional<String> token, String ipAdress){

        Optional<AppUser> appUser = userFinder.findUser(token, ipAdress);
        if(appUser.isPresent()){
            return appUser.get();
        } else {
            return userCreator.createUser(token, ipAdress);
        }
    }
}
