package com.service.monitor.app.domain.dto;

public class ProjectMiniatureDto {
    private long id;
    private String title;
    private String technologies;
    private String miniatureUrl;
    private String description;

    public ProjectMiniatureDto() {
    }

    public ProjectMiniatureDto(long id, String title, String technologies, String miniatureUrl, String description) {
        this.id = id;
        this.title = title;
        this.technologies = technologies;
        this.miniatureUrl = miniatureUrl;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTechnologies() {
        return technologies;
    }

    public String getMiniatureUrl() {
        return miniatureUrl;
    }

    public String getDescription() {
        return description;
    }
}
