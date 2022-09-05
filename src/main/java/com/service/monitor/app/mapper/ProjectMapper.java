package com.service.monitor.app.mapper;

import com.service.monitor.app.domain.Button;
import com.service.monitor.app.domain.DescriptionImage;
import com.service.monitor.app.domain.Project;
import com.service.monitor.app.domain.ProjectDescription;
import com.service.monitor.app.domain.dto.ButtonDto;
import com.service.monitor.app.domain.dto.DescriptionDto;
import com.service.monitor.app.domain.dto.DescriptionImageDto;
import com.service.monitor.app.domain.dto.ProjectMiniatureDto;
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
                projectDescription.getDescription(),
                mapToDescriptionImageDtoList(projectDescription.getDescriptionImageList()),
                mapToButtonList(projectDescription.getButtonList()));
    }

    private List<DescriptionImageDto> mapToDescriptionImageDtoList(List<DescriptionImage> descriptionImages){
        return descriptionImages.stream()
                .map(descriptionImage -> mapToDescriptionImageDto(descriptionImage)).collect(Collectors.toList());
    }

    private DescriptionImageDto mapToDescriptionImageDto(DescriptionImage descriptionImage){
        return new DescriptionImageDto(descriptionImage.getWidth(), descriptionImage.getHeight(),
                descriptionImage.getImageUrl(), descriptionImage.getDescription());
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
                descriptionDto.getDescription(),
                mapToDescriptionImages(descriptionDto.getDescriptionImageDtos()),
                mapToButtons(descriptionDto.getButtonDtos()));
    }

    private List<DescriptionImage> mapToDescriptionImages(List<DescriptionImageDto> descriptionImageDtos){
        return descriptionImageDtos.stream().map(descriptionImageDto -> mapToDescriptionImage(descriptionImageDto)).collect(Collectors.toList());
    }

    private DescriptionImage mapToDescriptionImage(DescriptionImageDto descriptionImageDto){
        return new DescriptionImage();
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
