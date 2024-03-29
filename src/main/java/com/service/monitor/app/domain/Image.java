package com.service.monitor.app.domain;


import javax.persistence.*;

@Entity
public class Image {

    @Id
    private long id;

    private String name;

    @Column(columnDefinition = "LONGTEXT")
    private String base64image;

    public Image() {
    }

    public Image(long id, String name, String base64image) {
        this.id = id;
        this.name = name;
        this.base64image = base64image;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBase64image() {
        return base64image;
    }
}
