package com.service.monitor.app.domain.dto.monitoring;

import java.time.LocalDateTime;
import java.util.List;

public class SessionDto {
    private LocalDateTime started;
    private LocalDateTime lastActive;
    private List<String> actions;

    public SessionDto() {
    }

    public SessionDto(LocalDateTime started, LocalDateTime lastActive, List<String> actions) {
        this.started = started;
        this.lastActive = lastActive;
        this.actions = actions;
    }

    public LocalDateTime getStarted() {
        return started;
    }

    public LocalDateTime getLastActive() {
        return lastActive;
    }

    public List<String> getActions() {
        return actions;
    }
}
