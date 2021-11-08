package com.service.monitor.app.domain;

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
    @OneToMany(targetEntity = Action.class, cascade = CascadeType.ALL)
    @OrderColumn
    private List<Action> actions;

    public User() {
    }

    public User(String token) {
        this.token = token;
        this.actions = new ArrayList<>();
    }

    public List<Action> getActions() {
        return actions;
    }

    public String getToken() {
        return token;
    }

    public long getId() {
        return id;
    }
}
