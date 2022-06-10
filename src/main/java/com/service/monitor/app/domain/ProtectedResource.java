package com.service.monitor.app.domain;

import com.service.monitor.app.domain.enums.ResourceType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ProtectedResource {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToMany(mappedBy = "resource",targetEntity = ProtectedResourceAccessAuthKey.class, cascade = CascadeType.ALL)
    private List<ProtectedResourceAccessAuthKey> keyRegisters = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private ResourceType resourceType;
    @Column(columnDefinition = "LONGTEXT")
    private String stringResource;

    public ProtectedResource() {
    }

    public ProtectedResource(String stringResource, ResourceType resourceType) {
        this.stringResource = stringResource;
        this.resourceType = resourceType;
    }

    public long getId() {
        return id;
    }

    public List<ProtectedResourceAccessAuthKey> getKeyRegisters() {
        return keyRegisters;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public String getStringResource() {
        return stringResource;
    }

    public void addProtectedResourceAuthKey(ProtectedResourceAccessAuthKey protectedResourceAccessAuthKey){
        keyRegisters.add(protectedResourceAccessAuthKey);
    }
}
