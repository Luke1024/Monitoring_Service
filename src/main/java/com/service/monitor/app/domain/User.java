package com.service.monitor.app.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@NamedNativeQuery(
        name = "User.findByToken",
        query = "SELECT * FROM user WHERE token=:TOKEN",
        resultClass = User.class
)

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String token;
    private LocalDateTime firstActive;
    private LocalDateTime lastActive;
    @OneToMany(targetEntity = Action.class)
    @OrderColumn
    private List<Action> actionCodes;

    public User() {
    }

    public User(LocalDateTime firstActive, String token) {
        this.token = token;
        this.firstActive = firstActive;
        this.actionCodes = new ArrayList<>();
    }

    public LocalDateTime getFirstActive() {
        return firstActive;
    }

    public LocalDateTime getLastActive() {
        return lastActive;
    }

    public List<Action> getActionCodes() {
        return actionCodes;
    }

    public String getToken() {
        return token;
    }

    public long getId() {
        return id;
    }

    public void setFirstActive(LocalDateTime firstActive) {
        this.firstActive = firstActive;
    }

    public void setLastActive(LocalDateTime lastActive) {
        this.lastActive = lastActive;
    }
}
