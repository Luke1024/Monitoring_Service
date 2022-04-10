package com.service.monitor.app.domain.dto.mvc;

public class ActionMvcDto {
    public String timestamp;
    public String action;

    public ActionMvcDto(String timestamp, String action) {
        this.timestamp = timestamp;
        this.action = action;
    }
}
