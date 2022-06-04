package com.service.monitor.app.domain;

import com.service.monitor.app.domain.enums.ProjectType;

import javax.persistence.*;
import java.util.List;

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
    private String descriptionPage;
    @OneToMany(targetEntity = Image.class,cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Image> images;

    public Project() {
    }

    public Project(ProjectType type, String title, String technologies, String miniatureUrl, String description, String descriptionPage, List<Image> images) {
        this.type = type;
        this.title = title;
        this.technologies = technologies;
        this.miniatureUrl = miniatureUrl;
        this.description = description;
        this.descriptionPage = descriptionPage;
        this.images = images;
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

    public List<Image> getImages() {
        return images;
    }
}
