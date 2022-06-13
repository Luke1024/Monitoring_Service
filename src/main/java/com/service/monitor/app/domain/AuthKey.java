package com.service.monitor.app.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AuthKey {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String keyValue;
    private String sendTo;
    @OneToMany(mappedBy = "authKey", targetEntity = ProtectedResourceAccessAuthKey.class, cascade = CascadeType.ALL)
    private List<ProtectedResourceAccessAuthKey> resourceKey = new ArrayList<>();

    public AuthKey() {
    }

    public AuthKey(String keyValue, String sendTo) {
        this.keyValue = keyValue;
        this.sendTo = sendTo;
    }

    public long getId() {
        return id;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void addProtectedResourceAccessAuthKey(ProtectedResourceAccessAuthKey protectedResourceAccessAuthKey){
        resourceKey.add(protectedResourceAccessAuthKey);
    }
}
