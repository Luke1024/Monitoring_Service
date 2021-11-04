package com.service.monitor.app.domain;

import java.util.List;

public class PulseDto {

    private String token;
    private List<String> activityCodes;

    public PulseDto() {
    }

    public PulseDto(String token, List<String> activityCodes) {
        this.token = token;
        this.activityCodes = activityCodes;
    }

    public String getToken() {
        return token;
    }

    public List<String> getActivityCodes() {
        return activityCodes;
    }
}
