package com.service.monitor.app.domain.dto;

import java.util.List;

public class ReportDto {
    private List<String> userStats;

    public ReportDto() {
    }

    public ReportDto(List<String> userStats) {
        this.userStats = userStats;
    }

    public List<String> getUserStats() {
        return userStats;
    }
}
