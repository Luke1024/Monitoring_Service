package com.service.monitor.app.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AccessTime {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private LocalDateTime now;
    //@ManyToOne(name = "access_id")
    //private ProtectedResourceAccessAuthKey resourceKey;

    public AccessTime() {
    }


}
