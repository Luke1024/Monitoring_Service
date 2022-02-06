package com.service.monitor.app.domain.dto;

import java.util.List;

public class PulseDto {

    private String token;
    private List<String> actions;

    public PulseDto() {
    }

    public PulseDto(String token, List<String> actions) {
        this.token = token;
        this.actions = actions;
    }

    public String getToken() {
        return token;
    }

    public List<String> getActions() {
        return actions;
    }

    @Override
    public String toString() {
        return "PulseDto{" +
                "token='" + token + '\'' +
                ", actions=" + actions +
                '}';
    }
}
