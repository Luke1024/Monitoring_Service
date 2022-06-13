package com.service.monitor.app.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ProtectedResourceAccessAuthKey {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name = "resource_id")
    private ProtectedResource resource;
    @ManyToOne
    @JoinColumn(name = "authkey_id")
    private AuthKey authKey;
    @OneToMany(mappedBy = "resourceKeyRegister",targetEntity = AccessTime.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderColumn
    private List<AccessTime> accessTimeList = new ArrayList<>();

    public ProtectedResourceAccessAuthKey() {
    }

    public ProtectedResourceAccessAuthKey(ProtectedResource resource, AuthKey authKey) {
        this.resource = resource;
        this.authKey = authKey;
    }

    public long getId() {
        return id;
    }

    public ProtectedResource getResource() {
        return resource;
    }

    public AuthKey getAuthKey() {
        return authKey;
    }

    public void addAccessTime(){
        accessTimeList.add(new AccessTime(LocalDateTime.now(),this));
    }

    public List<AccessTime> getAccessTimeList() {
        return accessTimeList;
    }
}
