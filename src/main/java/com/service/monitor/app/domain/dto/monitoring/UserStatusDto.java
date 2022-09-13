package com.service.monitor.app.domain.dto.monitoring;

import java.time.LocalDateTime;
import java.util.List;

public class UserStatusDto {

    private long id;
    private int sessions;
    private List<Long> sessionsAvailableId;
    private int actionInLastSessions;
    private int contacts;
    private LocalDateTime lastActive;

    public UserStatusDto() {
    }

    public UserStatusDto(long id, int sessions, List<Long> sessionsAvailableId,
                         int actionInLastSessions, int contacts, LocalDateTime lastActive) {
        this.id = id;
        this.sessions = sessions;
        this.sessionsAvailableId = sessionsAvailableId;
        this.actionInLastSessions = actionInLastSessions;
        this.contacts = contacts;
        this.lastActive = lastActive;
    }

    public long getId() {
        return id;
    }

    public int getSessions() {
        return sessions;
    }

    public List<Long> getSessionsAvailableId() {
        return sessionsAvailableId;
    }

    public int getActionInLastSessions() {
        return actionInLastSessions;
    }

    public int getContacts() {
        return contacts;
    }

    public LocalDateTime getLastActive() {
        return lastActive;
    }
}
