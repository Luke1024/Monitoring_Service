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
    @JoinColumn(name="USER_ID")
    private User user;

    public Action() {
    }

    public Action(LocalDateTime timeStamp, String actionCode, User user) {
        this.timeStamp = timeStamp;
        this.actionCode = actionCode;
        this.user = user;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public String getActionCode() {
        return actionCode;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return timeStamp.toString() + ", op= " + actionCode;
    }
}
