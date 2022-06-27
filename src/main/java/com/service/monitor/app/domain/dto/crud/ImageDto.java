package com.service.monitor.app.domain.dto.crud;

public class ImageDto {

    private long id;
    private String name;
    private String base64image;
    private int base64StringSize;

    public ImageDto() {
    }

    public ImageDto(String name, String base64image, int base64StringSize) {
        this.name = name;
        this.base64image = base64image;
        this.base64StringSize = base64StringSize;
    }

    public ImageDto(long id, String name, String base64image, int base64StringSize) {
        this.id = id;
        this.name = name;
        this.base64image = base64image;
        this.base64StringSize = base64StringSize;
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

    public int getBase64StringSize() {
        return base64StringSize;
    }
}
