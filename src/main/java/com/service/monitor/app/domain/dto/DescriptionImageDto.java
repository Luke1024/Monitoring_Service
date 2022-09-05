package com.service.monitor.app.domain.dto;

public class DescriptionImageDto {
    private int width;
    private int height;
    private String imageUrl;
    private String description;

    public DescriptionImageDto(int width, int height, String imageUrl, String description) {
        this.width = width;
        this.height = height;
        this.imageUrl = imageUrl;
        this.description = description;
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
}
