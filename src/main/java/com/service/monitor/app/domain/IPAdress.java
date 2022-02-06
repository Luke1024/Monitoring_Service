package com.service.monitor.app.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class IPAdress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @OrderColumn
    private long id;
    private LocalDateTime firstUsed;
    private String adress;
    @ManyToOne
    @JoinColumn(name="APPUSER_ID")
    private AppUser appUser;

    public IPAdress() {
    }

    public IPAdress(LocalDateTime firstUsed, String adress, AppUser appUser) {
        this.firstUsed = firstUsed;
        this.adress = adress;
        this.appUser = appUser;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getFirstUsed() {
        return firstUsed;
    }

    public String getAdress() {
        return adress;
    }

    public AppUser getAppUser() {
        return appUser;
    }
}
