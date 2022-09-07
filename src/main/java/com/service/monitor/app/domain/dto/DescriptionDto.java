package com.service.monitor.app.domain.dto;

import java.util.List;

public class DescriptionDto {
    private String title;
    private List<DescriptionPartDto> descriptionPartDtos;
    private List<ButtonDto> buttonDtos;

    public DescriptionDto(String title, List<DescriptionPartDto> descriptionPartDtos,
                          List<ButtonDto> buttonDtos) {
        this.title = title;
        this.descriptionPartDtos = descriptionPartDtos;
        this.buttonDtos = buttonDtos;
    }

    public String getTitle() {
        return title;
    }

    public List<DescriptionPartDto> getDescriptionPartDtos() {
        return descriptionPartDtos;
    }

    public List<ButtonDto> getButtonDtos() {
        return buttonDtos;
    }
}
