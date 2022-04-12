package com.service.monitor.app.domain.dto;

import java.util.List;

public class PulseDto {

    private List<String> actions;

    public PulseDto() {
    }

    public PulseDto(List<String> actions) {
        this.actions = actions;
    }

    public List<String> getActions() {
        return actions;
    }

    @Override
    public String toString() {
        return "PulseDto{" +
                ", actions=" + actions +
                '}';
    }
}
