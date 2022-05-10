package com.service.monitor.app.domain.dto.mvc;

public class SessionMvcDto {
    public int actionCount;
    public String started;
    public String ended;
    public boolean active;
    public long userId;

    public SessionMvcDto(int actionCount, String started, String ended, boolean active, long userId) {
        this.actionCount = actionCount;
        this.started = started;
        this.ended = ended;
        this.active = active;
        this.userId = userId;
    }
}
