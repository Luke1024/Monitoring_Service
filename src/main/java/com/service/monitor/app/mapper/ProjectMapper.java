package com.service.monitor.app.mapper;

import com.service.monitor.app.domain.*;
import com.service.monitor.app.domain.dto.*;
import com.service.monitor.app.domain.dto.crud.ProjectDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProjectMapper {

    private Logger logger = LoggerFactory.getLogger(ProjectMapper.class);

    public DescriptionDto mapToDescriptionDto(ProjectDescription projectDescription){
        return new DescriptionDto(
                projectDescription.getTitle(),
                mapToDescriptionPartsDtoList(projectDescription.getDescriptionParts()),
                mapToButtonList(projectDescription.getButtonList()));
    }

    private List<DescriptionPartDto> mapToDescriptionPartsDtoList(List<DescriptionPart> descriptionParts){
        return descriptionParts.stream()
                .map(descriptionPart -> mapToDescriptionPartDto(descriptionPart)).collect(Collectors.toList());
    }

    private DescriptionPartDto mapToDescriptionPartDto(DescriptionPart descriptionPart){
        return new DescriptionPartDto(descriptionPart.getDescription(), descriptionPart.isContainImage(),
                descriptionPart.isImageTop(), mapToDescriptionImageDto(descriptionPart.getDescriptionImage()));
    }

    private DescriptionImageDto mapToDescriptionImageDto(DescriptionImage descriptionImage){
        return new DescriptionImageDto(descriptionImage.getWidth(), descriptionImage.getHeight(),
                descriptionImage.getImageUrl(),descriptionImage.getDescription());
    }

    private List<ButtonDto> mapToButtonList(List<Button> buttons){
        return buttons.stream()
                .map(button -> mapToButtonDto(button)).collect(Collectors.toList());
    }

    private ButtonDto mapToButtonDto(Button button){
        return new ButtonDto(button.getDescription(), button.getUrl());
    }

    public ProjectDto mapToProjectDto(Project project){
        return new ProjectDto(
                project.getId(),
                project.getType(),
                project.getTitle(),
                project.getTechnologies(),
                project.getMiniatureUrl(),
                project.getDescription(),
                mapToDescriptionDto(project.getDescriptionPage()),
                project.getDisplayOrder());
    }

    public Project mapToProjectFromDto(ProjectDto projectDto){
        return new Project(
                projectDto.getId(),
                projectDto.getType(),
                projectDto.getTitle(),
                projectDto.getTechnologies(),
                projectDto.getMiniatureUrl(),
                projectDto.getDescription(),
                mapToProjectDescription(projectDto.getDescriptionDto()),
                projectDto.getDisplayOrder());
    }

    public ProjectDescription mapToProjectDescription(DescriptionDto descriptionDto){
        return new ProjectDescription(
                descriptionDto.getTitle(),
                maptoDescriptionParts(descriptionDto.getDescriptionPartDtos()),
                mapToButtons(descriptionDto.getButtonDtos()));
    }

    private List<DescriptionPart> maptoDescriptionParts(List<DescriptionPartDto> descriptionPartDtos){
        return descriptionPartDtos.stream().map(descriptionPartDto -> mapToDescriptionPart(descriptionPartDto)).collect(Collectors.toList());
    }

    private DescriptionPart mapToDescriptionPart(DescriptionPartDto descriptionPartDto){
        return new DescriptionPart(descriptionPartDto.getDescription(), descriptionPartDto.isContainImage(),
                descriptionPartDto.isImageTop(), mapToDescriptionImage(descriptionPartDto.getImage()),null);
    }

    private List<DescriptionImage> mapToDescriptionImages(List<DescriptionImageDto> descriptionImageDtos){
        return descriptionImageDtos.stream().map(descriptionImageDto -> mapToDescriptionImage(descriptionImageDto)).collect(Collectors.toList());
    }

    private DescriptionImage mapToDescriptionImage(DescriptionImageDto descriptionImageDto){
        return new DescriptionImage(descriptionImageDto.getWidth(), descriptionImageDto.getHeight(), descriptionImageDto.getImageUrl(),
                descriptionImageDto.getDescription());
    }

    private List<Button> mapToButtons(List<ButtonDto> buttonDtos){
        return buttonDtos.stream().map(buttonDto -> mapToButton(buttonDto)).collect(Collectors.toList());
    }

    private Button mapToButton(ButtonDto buttonDto){
        return new Button(buttonDto.getButtonDescription(), buttonDto.getButtonUrl());
    }

    public List<ProjectMiniatureDto> mappingProjectsToMiniatureDtoList(List<Project> projects){
        return projects.stream().map(project -> mapProjectToMiniatureDto(project)).collect(Collectors.toList());
    }

    public ProjectMiniatureDto mapProjectToMiniatureDto(Project project){
        return new ProjectMiniatureDto(project.getId(), project.getTitle(), project.getTechnologies(),
                project.getMiniatureUrl(), project.getDescription());
    }
}
