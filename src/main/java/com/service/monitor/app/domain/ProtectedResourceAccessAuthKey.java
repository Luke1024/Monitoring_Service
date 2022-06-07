package com.service.monitor.app.domain;

import javax.persistence.*;
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

    public ProtectedResourceAccessAuthKey(ProtectedResource resource, AuthKey authKey) {
        this.resource = resource;
        this.authKey = authKey;
    }

    public ProtectedResource getResource() {
        return resource;
    }

    public AuthKey getAuthKey() {
        return authKey;
    }
}
