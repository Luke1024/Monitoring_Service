package com.service.monitor.app.domain.dto;

public class ButtonDto {
    private String buttonDescription;
    private String buttonUrl;

    public ButtonDto(String buttonDescription, String buttonUrl) {
        this.buttonDescription = buttonDescription;
        this.buttonUrl = buttonUrl;
    }

    public String getButtonDescription() {
        return buttonDescription;
    }

    public String getButtonUrl() {
        return buttonUrl;
    }
}
