package com.service.monitor.app.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime timeStamp;
    private String action;
    @ManyToOne
    @JoinColumn(name="SESSION_ID")
    private UserSession userSession;

    public Action() { }

    public Action(LocalDateTime timeStamp, String action, UserSession userSession) {
        this.timeStamp = timeStamp;
        this.action = action;
        this.userSession = userSession;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public String getAction() {
        return action;
    }

    public UserSession getUserSession() {
        return userSession;
    }

    @Override
    public String toString() {
        return action;
    }
}
