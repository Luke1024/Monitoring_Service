package com.service.monitor.app.domain.dto;

import java.util.List;

public class PulseDto {

    private String token;
    private String code;

    public PulseDto() {
    }

    public PulseDto(String token, String code) {
        this.token = token;
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "PulseDto{" +
                "token='" + token + '\'' +
                ", activityCodes=" + code +
                '}';
    }
}
