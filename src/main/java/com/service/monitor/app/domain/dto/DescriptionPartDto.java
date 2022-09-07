package com.service.monitor.app.domain.dto;

public class DescriptionPartDto {
    private String description;
    private boolean containImage;
    private boolean imageTop;
    private DescriptionImageDto image;

    public DescriptionPartDto() {
    }

    public DescriptionPartDto(String description, boolean containImage,
                              boolean imageTop, DescriptionImageDto image) {
        this.description = description;
        this.containImage = containImage;
        this.imageTop = imageTop;
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public boolean isContainImage() {
        return containImage;
    }

    public boolean isImageTop() {
        return imageTop;
    }

    public DescriptionImageDto getImage() {
        return image;
    }
}
