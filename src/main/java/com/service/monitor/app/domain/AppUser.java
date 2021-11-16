package com.service.monitor.app.domain;

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
    @OneToMany(targetEntity = Action.class, cascade = CascadeType.ALL)
    @OrderColumn
    private List<Action> actions;

    public AppUser() {
    }

    public AppUser(String token) {
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
