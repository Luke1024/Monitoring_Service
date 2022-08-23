package com.service.monitor.app.domain.dto.crud;

import com.service.monitor.app.domain.dto.DescriptionDto;
import com.service.monitor.app.domain.enums.ProjectType;

public class ProjectDto {

    private long id;
    private ProjectType type;
    private String title;
    private String technologies;
    private String miniatureUrl;
    private String description;
    private DescriptionDto descriptionDto;
    private int displayOrder;

    public ProjectDto() {
    }

    public ProjectDto(ProjectType type, String title, String technologies, String miniatureUrl,
                      String description, DescriptionDto descriptionDto, int displayOrder) {
        this.type = type;
        this.title = title;
        this.technologies = technologies;
        this.miniatureUrl = miniatureUrl;
        this.description = description;
        this.descriptionDto = descriptionDto;
        this.displayOrder = displayOrder;
    }

    public ProjectDto(long id, ProjectType type, String title, String technologies, String miniatureUrl,
                      String description, DescriptionDto descriptionDto, int displayOrder) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.technologies = technologies;
        this.miniatureUrl = miniatureUrl;
        this.description = description;
        this.descriptionDto = descriptionDto;
        this.displayOrder = displayOrder;
    }

    public long getId() {
        return id;
    }

    public ProjectType getType() {
        return type;
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

    public DescriptionDto getDescriptionDto() {
        return descriptionDto;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }
}
