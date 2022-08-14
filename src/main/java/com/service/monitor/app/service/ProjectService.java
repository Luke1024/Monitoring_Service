package com.service.monitor.app.service;

import com.service.monitor.app.domain.Project;
import com.service.monitor.app.domain.dto.ProjectMiniatureDto;
import com.service.monitor.app.domain.dto.StringDto;
import com.service.monitor.app.domain.dto.crud.ProjectDto;
import com.service.monitor.app.domain.enums.ProjectType;
import com.service.monitor.app.repository.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    private Logger logger = LoggerFactory.getLogger(ProjectService.class);

    public List<ProjectMiniatureDto> getAllNormalDto(){
        return mappingProjectsToMiniatureDtoList(
                projectRepository.findByType(ProjectType.NORMAL));
    }

    public List<ProjectMiniatureDto> getAllMiniDto(){
        return mappingProjectsToMiniatureDtoList(
                projectRepository.findByType(ProjectType.MINI));
    }

    public ResponseEntity<StringDto> getDescription(long id){
        Optional<Project> optionalProject = projectRepository.findById(id);
        if(optionalProject.isPresent()) {
            return ResponseEntity.ok(new StringDto(optionalProject.get().getDescriptionPage()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public List<ProjectDto> getAllProjects(){
        Iterable<Project> projects = projectRepository.findAll();
        List<ProjectDto> projectDtos = new ArrayList<>();
        for(Project project : projects){
            projectDtos.add(mapFromProjectToProjectDtoWithoutDescription(project));
        }
        return projectDtos;
    }

    public ResponseEntity<ProjectDto> getProject(long id){
        Optional<Project> projectOptional = projectRepository.findById(id);
        if(projectOptional.isPresent()){
            return ResponseEntity.ok(mapFromProjectToProjectDtoWithoutDescription(projectOptional.get()));
        } else return ResponseEntity.notFound().build();
    }

    private ProjectDto mapFromProjectToProjectDtoWithoutDescription(Project project){
        return new ProjectDto(
                project.getId(),
                project.getType(),
                project.getTitle(),
                project.getTechnologies(),
                project.getMiniatureUrl(),
                project.getDescription(),
                "Html string size: " + project.getDescriptionPage().length(),
                project.getDisplayOrder());
    }

    public boolean saveProject(ProjectDto projectDto){
        projectRepository.save(mapToProjectFromDto(projectDto));
        return true;
    }

    public boolean saveAllProjects(List<ProjectDto> projectDtos){
        List<Project> projectsToSave = new ArrayList<>();
        for(ProjectDto projectDto : projectDtos){
            projectsToSave.add(mapToProjectFromDto(projectDto));
        }
        projectRepository.saveAll(projectsToSave);
        return true;
    }

    private Project mapToProjectFromDto(ProjectDto projectDto){
        logger.info("Receiving project transfer: " + projectDto.toString());
        return new Project(
                projectDto.getId(),
                projectDto.getType(),
                projectDto.getTitle(),
                projectDto.getTechnologies(),
                projectDto.getMiniatureUrl(),
                projectDto.getDescription(),
                decode64(projectDto.getDescriptionPageBase64()),
                projectDto.getDisplayOrder());
    }

    private String decode64(String encoded){
        if(encoded==null){
            return "";
        }
        if(encoded.isEmpty()) {
            return "";
        }
        return new String(Base64.getDecoder().decode(encoded.getBytes()));
    }

    public boolean updateProject(ProjectDto projectDto){
        Optional<Project> projectOptional = projectRepository.findById(projectDto.getId());
        if(projectOptional.isPresent()){
            Project newProject = new Project(
                    projectDto.getId(),
                    projectDto.getType(),
                    projectDto.getTitle(),
                    projectDto.getTechnologies(),
                    projectDto.getMiniatureUrl(),
                    projectDto.getDescription(),
                    projectDto.getDescriptionPageBase64(),
                    projectDto.getDisplayOrder());
            projectRepository.save(newProject);
            return true;
        } else return false;
    }

    public boolean deleteProject(long projectId){
        projectRepository.deleteById(projectId);
        return true;
    }

    public boolean deleteAllProjects(){
        projectRepository.deleteAll();
        return true;
    }

    private List<ProjectMiniatureDto> mappingProjectsToMiniatureDtoList(List<Project> projects){
        return projects.stream().map(project -> projectToDto(project)).collect(Collectors.toList());
    }

    private ProjectMiniatureDto projectToDto(Project project){
        return new ProjectMiniatureDto(project.getId(), project.getTitle(), project.getTechnologies(),
                project.getMiniatureUrl(), project.getDescription());
    }
}
