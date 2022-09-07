package com.service.monitor.app.domain;

import javax.persistence.*;

@Entity
public class DescriptionImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int width;
    private int height;
    private String imageUrl;
    private String description;
    @OneToOne
    @JoinColumn(name="DESCRIPTION_PART_ID")
    private DescriptionPart descriptionPart;

    public DescriptionImage() {
    }

    public DescriptionImage(int width, int height, String imageUrl, String description) {
        this.width = width;
        this.height = height;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    public DescriptionImage(int width, int height, String imageUrl, String description, DescriptionPart descriptionPart) {
        this.width = width;
        this.height = height;
        this.imageUrl = imageUrl;
        this.description = description;
        this.descriptionPart = descriptionPart;
    }

    public long getId() {
        return id;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public DescriptionPart getDescriptionPart() {
        return descriptionPart;
    }
}
