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
    @OrderColumn
    private long id;
    private String token;
    @OneToMany(targetEntity = Action.class)
    @OrderColumn
    private List<Action> actionCodes;

    public User() {
    }

    public User(String token) {
        this.token = token;
        this.actionCodes = new ArrayList<>();
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
}
