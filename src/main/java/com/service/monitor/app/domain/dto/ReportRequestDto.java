package com.service.monitor.app.domain.dto;

public class ReportRequestDto {
    private int days;

    public ReportRequestDto(int days) {
        this.days = days;
    }

    public int getDays() {
        return days;
    }
}
