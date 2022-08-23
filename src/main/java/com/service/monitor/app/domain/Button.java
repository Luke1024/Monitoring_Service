package com.service.monitor.app.domain;

import javax.persistence.*;

@Entity
public class Button {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String description;
    private String url;
    @ManyToOne
    @JoinColumn(name="PROJECT_DESCRIPTION_ID")
    private ProjectDescription projectDescription;

    public Button() {
    }

    public Button(String description, String url) {
        this.description = description;
        this.url = url;
    }

    public void setProjectDescription(ProjectDescription projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public ProjectDescription getProjectDescription() {
        return projectDescription;
    }
}
