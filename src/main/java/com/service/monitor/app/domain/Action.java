package com.service.monitor.app.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime timeStamp;
    private String action;
    @ManyToOne
    @JoinColumn(name="APPUSER_ID")
    private AppUser appUser;

    public Action() { }

    public Action(LocalDateTime timeStamp, String action, AppUser appUser) {
        this.timeStamp = timeStamp;
        this.action = action;
        this.appUser = appUser;
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

    public AppUser getAppUser() {
        return appUser;
    }
}
