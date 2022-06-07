package com.service.monitor.app.domain.enums;

public enum ResourceType {
    STRING("Normal"),
    IMAGE("Image");

    private String displayName;

    ResourceType(String displayName) {
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
