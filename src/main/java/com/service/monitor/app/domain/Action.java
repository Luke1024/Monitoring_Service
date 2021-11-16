package com.service.monitor.app.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime timeStamp;
    private String actionCode;
    @ManyToOne
    @JoinColumn(name="APPUSER_ID")
    private AppUser appUser;

    public Action() {
    }

    public Action(LocalDateTime timeStamp, String actionCode, AppUser appUser) {
        this.timeStamp = timeStamp;
        this.actionCode = actionCode;
        this.appUser = appUser;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public String getActionCode() {
        return actionCode;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    @Override
    public String toString() {
        return timeStamp.toString() + ", op= " + actionCode;
    }
}
