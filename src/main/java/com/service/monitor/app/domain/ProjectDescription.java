package com.service.monitor.app.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class ProjectDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    @Column(columnDefinition = "MEDIUMTEXT")
    private String intro;
    @OneToMany(targetEntity = DescriptionPart.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderColumn
    private List<DescriptionPart> descriptionParts;
    @OneToMany(targetEntity = Button.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderColumn
    private List<Button> buttonList;

    public ProjectDescription() {
    }

    public ProjectDescription(String title, String intro, List<DescriptionPart> descriptionParts, List<Button> buttonList) {
        this.title = title;
        this.intro = intro;
        initializeDescriptionPartList(descriptionParts);
        initializeButtonList(buttonList);
    }

    private void initializeDescriptionPartList(List<DescriptionPart> descriptionParts){
        descriptionParts.stream().forEach(descriptionPart -> descriptionPart.setProjectDescription(this));
        this.descriptionParts = descriptionParts;
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

    public List<DescriptionPart> getDescriptionParts() {
        return descriptionParts;
    }

    public List<Button> getButtonList() {
        return buttonList;
    }

    public String getIntro() {
        return intro;
    }
}
