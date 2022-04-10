package com.service.monitor.app.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @OrderColumn
    public long id;
    public String token;
    @OneToMany(targetEntity = Action.class, cascade = CascadeType.ALL)
    @OrderColumn
    public List<Action> actions = new ArrayList<>();
    @OneToMany(targetEntity = Contact.class, cascade = CascadeType.ALL)
    @OrderColumn
    public List<Contact> contacts = new ArrayList<>();
    @OneToMany(targetEntity = IPAdress.class, cascade = CascadeType.ALL)
    @OrderColumn
    public List<IPAdress> ipAdresses = new ArrayList<>();
    public LocalDateTime lastActive;

    public AppUser() {
    }

    public AppUser(String token, String ipAdress, LocalDateTime lastActive) {
        IPAdress newAdress = new IPAdress(LocalDateTime.now(), ipAdress, this);
        this.ipAdresses.add(newAdress);
        this.token = token;
        this.lastActive = lastActive;
    }

    public void updateLastActive() {
        if(actions.size()>0){
            Action lastAction = actions.get(actions.size()-1);
            if(lastAction != null){
                this.lastActive = lastAction.getTimeStamp();
            }
        }
    }
}
