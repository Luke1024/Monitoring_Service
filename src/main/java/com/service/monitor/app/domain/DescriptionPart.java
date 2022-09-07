package com.service.monitor.app.domain;

import javax.persistence.*;

@Entity
public class DescriptionPart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String description;
    private boolean containImage;
    private boolean imageTop;
    @OneToOne(targetEntity = DescriptionImage.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private DescriptionImage descriptionImage;
    @ManyToOne
    @JoinColumn(name="PROJECT_DESCRIPTION_ID")
    private ProjectDescription projectDescription;

    public DescriptionPart() {
    }

    public DescriptionPart(String description, boolean containImage, boolean imageTop,
                           DescriptionImage descriptionImage, ProjectDescription projectDescription) {
        this.description = description;
        this.containImage = containImage;
        this.imageTop = imageTop;
        this.descriptionImage = descriptionImage;
        this.projectDescription = projectDescription;
    }

    public void setProjectDescription(ProjectDescription projectDescription) {
        this.projectDescription = projectDescription;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean isContainImage() {
        return containImage;
    }

    public boolean isImageTop() {
        return imageTop;
    }

    public DescriptionImage getDescriptionImage() {
        return descriptionImage;
    }

    public ProjectDescription getProjectDescription() {
        return projectDescription;
    }
}
