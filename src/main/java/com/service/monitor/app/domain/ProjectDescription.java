package com.service.monitor.app.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class ProjectDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String description;
    @OneToMany(targetEntity = DescriptionImage.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderColumn
    private List<DescriptionImage> descriptionImageList;
    @OneToMany(targetEntity = Button.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderColumn
    private List<Button> buttonList;

    public ProjectDescription() {
    }

    public ProjectDescription(String title, String description,
                              List<DescriptionImage> descriptionImageList, List<Button> buttonList) {
        this.title = title;
        this.description = description;
        initializeDescriptionImageList(descriptionImageList);
        initializeButtonList(buttonList);
    }

    private void initializeDescriptionImageList(List<DescriptionImage> descriptionImages){
        descriptionImages.stream().forEach(descriptionImage -> descriptionImage.setProjectDescription(this));
        this.descriptionImageList = descriptionImages;
    }

    private void initializeButtonList(List<Button> buttons){
        buttons.stream().forEach(button -> button.setProjectDescription(this));
        this.buttonList = buttons;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<DescriptionImage> getDescriptionImageList() {
        return descriptionImageList;
    }

    public List<Button> getButtonList() {
        return buttonList;
    }
}
