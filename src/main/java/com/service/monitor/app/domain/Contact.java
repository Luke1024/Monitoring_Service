package com.service.monitor.app.domain;

import javax.persistence.*;

@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String email;
    private String message;
    @ManyToOne
    @JoinColumn(name="APPUSER_ID")
    private AppUser appUser;

    public Contact() {
    }

    public Contact(String name, String email, String message, AppUser appUser) {
        this.name = name;
        this.email = email;
        this.message = message;
        this.appUser = appUser;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMessage() {
        return message;
    }

    public AppUser getAppUser() {
        return appUser;
    }
}
