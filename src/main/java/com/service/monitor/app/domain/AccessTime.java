package com.service.monitor.app.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AccessTime {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private LocalDateTime now;
    @ManyToOne
    @JoinColumn(name = "access_id")
    private ProtectedResourceAccessAuthKey resourceKeyRegister;

    public AccessTime() {
    }

    public AccessTime(LocalDateTime now, ProtectedResourceAccessAuthKey resourceKeyRegister) {
        this.now = now;
        this.resourceKeyRegister = resourceKeyRegister;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getNow() {
        return now;
    }

    public ProtectedResourceAccessAuthKey getResourceKeyRegister() {
        return resourceKeyRegister;
    }
}
