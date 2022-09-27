package com.service.monitor.app.domain.dto;

import java.util.ArrayList;
import java.util.List;

public class DescriptionDto {
    private String title;
    private String intro;
    private List<DescriptionPartDto> descriptionPartDtos;
    private List<ButtonDto> buttonDtos;

    public DescriptionDto() {
        this.descriptionPartDtos = new ArrayList<>();
        this.buttonDtos = new ArrayList<>();
    }

    public DescriptionDto(String title, String intro, List<DescriptionPartDto> descriptionPartDtos, List<ButtonDto> buttonDtos) {
        this.title = title;
        this.intro = intro;
        this.descriptionPartDtos = descriptionPartDtos;
        this.buttonDtos = buttonDtos;
    }

    public String getTitle() {
        return title;
    }

    public String getIntro() {
        return intro;
    }

    public List<DescriptionPartDto> getDescriptionPartDtos() {
        return descriptionPartDtos;
    }

    public List<ButtonDto> getButtonDtos() {
        return buttonDtos;
    }
}
