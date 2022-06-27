package com.service.monitor.app.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @Column(columnDefinition = "LONGTEXT")
    private String base64image;

    public Image() {
    }

    public Image(String name, String base64image) {
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
