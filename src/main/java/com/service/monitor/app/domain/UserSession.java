package com.service.monitor.app.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
public class UserSession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @OrderColumn
    private long id;
    private String sessionToken;
    @OneToMany(targetEntity = Action.class, cascade = CascadeType.ALL)
    @OrderColumn
    private List<Action> actions = new ArrayList<>();
    private LocalDateTime started;
    private LocalDateTime lastActive;
    @ManyToOne
    @JoinColumn(name="APPUSER_ID")
    private AppUser appUser;

    public UserSession() {
    }

    public UserSession(String sessionToken, AppUser appUser){
        this.sessionToken = sessionToken;
        this.appUser = appUser;
        this.started = LocalDateTime.now();
        this.lastActive = this.started;
    }

    public Optional<Action> getLastAction() {
        if(actions.size()>0){
            return Optional.of(actions.get(actions.size()-1));
        } else {
            return Optional.empty();
        }
    }

    public void addAction(String action) {
        LocalDateTime now = LocalDateTime.now();
        actions.add(new Action(now,action, this));
        this.lastActive = now;
    }

    public long getId() {
        return id;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public List<Action> getActions() {
        return actions;
    }

    public LocalDateTime getLastActive() {
        return lastActive;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public LocalDateTime getStarted() {
        return started;
    }
}
