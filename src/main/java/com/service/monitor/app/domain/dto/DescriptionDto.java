package com.service.monitor.app.domain.dto;

import java.util.List;

public class DescriptionDto {
    private String title;
    private String description;
    private List<DescriptionImageDto> descriptionImageDtos;
    private List<ButtonDto> buttonDtos;

    public DescriptionDto(String title, String description, List<DescriptionImageDto> descriptionImageDtos, List<ButtonDto> buttonDtos) {
        this.title = title;
        this.description = description;
        this.descriptionImageDtos = descriptionImageDtos;
        this.buttonDtos = buttonDtos;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<DescriptionImageDto> getDescriptionImageDtos() {
        return descriptionImageDtos;
    }

    public List<ButtonDto> getButtonDtos() {
        return buttonDtos;
    }
}
