package com.service.monitor.app.domain;

import com.service.monitor.app.domain.enums.ProjectType;

import javax.persistence.*;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Enumerated(EnumType.STRING)
    private ProjectType type;
    private String title;
    private String technologies;
    private String miniatureUrl;
    private String description;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_description_id")
    private ProjectDescription descriptionPage;
    private int displayOrder;

    public Project() {
    }

    public Project(ProjectType type, String title, String technologies, String miniatureUrl,
                   String description, ProjectDescription descriptionPage, int displayOrder) {
        this.type = type;
        this.title = title;
        this.technologies = technologies;
        this.miniatureUrl = miniatureUrl;
        this.description = description;
        this.descriptionPage = descriptionPage;
        this.displayOrder = displayOrder;
    }

    public Project(long id, ProjectType type, String title, String technologies, String miniatureUrl,
                   String description, ProjectDescription descriptionPage, int displayOrder) {
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

    public ProjectDescription getDescriptionPage() {
        return descriptionPage;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }
}
