package com.service.monitor.app.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.*;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String token;
    @OneToMany(targetEntity = UserSession.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderColumn
    private List<UserSession> sessions = new ArrayList<>();
    @OneToMany(targetEntity = Contact.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderColumn
    private List<Contact> contacts = new ArrayList<>();
    private LocalDateTime lastActive;

    public AppUser() {
    }

    public AppUser(String token, LocalDateTime lastActive) {
        this.token = token;
        this.lastActive = lastActive;
    }

    public Optional<UserSession> getLastSession(){
        if(sessions.size()>0){
            return Optional.of(sessions.get(sessions.size()-1));
        } else return Optional.empty();
    }

    public void addSession(String sessionToken){
        sessions.add(new UserSession(sessionToken, this));
    }

    public void addContact(Contact contact){
        contacts.add(contact);
    }

    public long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public List<UserSession> getSessions() {
        return sessions;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public LocalDateTime getLastActive() {
        return lastActive;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", token='" + token + '\'' +
                '}';
    }
}
