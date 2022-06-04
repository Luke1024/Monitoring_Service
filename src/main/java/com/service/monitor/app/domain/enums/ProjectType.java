package com.service.monitor.app.domain.enums;

public enum ProjectType {
    NORMAL("Normal"),
    MINI("Mini");

    private String displayName;

    ProjectType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return getDisplayName();
    }
}
