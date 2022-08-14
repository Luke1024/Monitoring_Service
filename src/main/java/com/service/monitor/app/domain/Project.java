package com.service.monitor.app.domain;

import com.service.monitor.app.domain.enums.ProjectType;

import javax.persistence.*;

@Entity
public class Project {
    @Id
    private long id;
    @Enumerated(EnumType.STRING)
    private ProjectType type;
    private String title;
    private String technologies;
    private String miniatureUrl;
    private String description;
    @Column(columnDefinition = "LONGTEXT")
    private String descriptionPage;
    private int displayOrder;

    public Project() {
    }

    public Project(long id, ProjectType type, String title, String technologies, String miniatureUrl, String description, String descriptionPage, int displayOrder) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.technologies = technologies;
        this.miniatureUrl = miniatureUrl;
        this.description = description;
        this.descriptionPage = descriptionPage;
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

    public String getDescriptionPage() {
        return descriptionPage;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }
}
