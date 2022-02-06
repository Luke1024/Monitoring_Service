package com.service.monitor.app.domain;

import org.springframework.core.annotation.Order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @OrderColumn
    private long id;
    private String token;
    private String ipAdress;
    @OneToMany(targetEntity = Action.class, cascade = CascadeType.ALL)
    @OrderColumn
    private List<Action> actions = new ArrayList<>();
    @OneToMany(targetEntity = Contact.class, cascade = CascadeType.ALL)
    @OrderColumn
    private List<Contact> contacts = new ArrayList<>();
    @OneToMany(targetEntity = IPAdress.class, cascade = CascadeType.ALL)
    @OrderColumn
    private List<IPAdress> ipAdresses = new ArrayList<>();

    public AppUser() {
    }

    public AppUser(String token, String ipAdress) {
        IPAdress newAdress = new IPAdress(LocalDateTime.now(), ipAdress, this);
        this.ipAdresses.add(newAdress);
        this.token = token;
    }

    public List<Action> getActions() {
        return actions;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public String getToken() {
        return token;
    }

    public long getId() {
        return id;
    }
}
