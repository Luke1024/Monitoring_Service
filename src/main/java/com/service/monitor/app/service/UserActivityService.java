package com.service.monitor.app.service;

import com.service.monitor.app.domain.Contact;
import com.service.monitor.app.domain.dto.ContactDto;
import com.service.monitor.app.domain.AppUser;
import com.service.monitor.app.repository.SessionRepository;
import com.service.monitor.app.service.user.identity.authorizer.CookieFilter;
import com.service.monitor.app.service.user.identity.authorizer.PreAuthService;
import com.service.monitor.app.service.user.identity.authorizer.SessionManager;
import com.service.monitor.app.service.user.identity.authorizer.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.Random;

@Service
public class UserActivityService {

    private Random random = new Random();

    @Autowired
    private PreAuthService identityAuthorizer;

    @Autowired
    private UserService userService;

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private CookieFilter cookieFilter = new CookieFilter();

    private Logger LOGGER = LoggerFactory.getLogger(UserActivityService.class);

    public void preAuth(Cookie[] cookies, HttpServletResponse response) {
        identityAuthorizer.preAuth(cookies, response);
    }

    public boolean save(String action, Cookie[] cookies, String ipAdress) {
        AppUser appUser = userService.auth(cookies, ipAdress);
        Optional<String> sessionToken = cookieFilter.filterCookiesToValue(cookies, cookieFilter.sessionCookieName);
        sessionManager.addSessionIfNecessary(appUser, sessionToken);
        appUser.getLastSession().get().addAction(action);
        userService.save(appUser);
        return true;
    }

    public boolean saveContact(ContactDto contactDto, Cookie[] cookies, String ipAdress) {
        AppUser appUser = userService.auth(cookies, ipAdress);
        Contact contact = new Contact(contactDto.getName(),
                        contactDto.getEmail(),
                        contactDto.getMessage(),
                        appUser);

        userService.saveContact(appUser, contact);
        LOGGER.info("Contact added: " + contactDto.toString() + ", by user with id:" +
                appUser.getId() + ", token: " + appUser.getToken());
        return true;
    }
}
