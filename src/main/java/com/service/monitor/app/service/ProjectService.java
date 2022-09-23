package com.service.monitor.app.service;

import com.service.monitor.app.domain.Project;
import com.service.monitor.app.domain.dto.*;
import com.service.monitor.app.domain.dto.crud.ProjectDto;
import com.service.monitor.app.domain.enums.ProjectType;
import com.service.monitor.app.mapper.ProjectMapper;
import com.service.monitor.app.repository.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectMapper projectMapper;

    private Logger logger = LoggerFactory.getLogger(ProjectService.class);

    public List<ProjectMiniatureDto> getAllNormalDto(){
        return projectMapper.mappingProjectsToMiniatureDtoList(
                sortByDisplayOrder(projectRepository.findByType(ProjectType.NORMAL)));
    }

    public List<ProjectMiniatureDto> getAllMiniDto() {
        return projectMapper.mappingProjectsToMiniatureDtoList(
                sortByDisplayOrder(projectRepository.findByType(ProjectType.MINI)));
    }

    private List<Project> sortByDisplayOrder(List<Project> projects){
        projects.sort(Comparator.comparing(Project::getDisplayOrder));
        return projects;
    }

    public ResponseEntity<DescriptionDto> getDescription(long id){
        Optional<Project> optionalProject = projectRepository.findById(id);
        if(optionalProject.isPresent()) {
            return ResponseEntity.ok(projectMapper.mapToDescriptionDto(optionalProject.get().getDescriptionPage()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public List<ProjectDto> getAllProjects(){
        Iterable<Project> projects = projectRepository.findAll();
        List<ProjectDto> projectDtos = new ArrayList<>();
        for(Project project : projects){
            projectDtos.add(projectMapper.mapToProjectDto(project));
        }
        return projectDtos;
    }

    public ResponseEntity<ProjectDto> getProject(long id){
        Optional<Project> projectOptional = projectRepository.findById(id);
        if(projectOptional.isPresent()){
            return ResponseEntity.ok(projectMapper.mapToProjectDto(projectOptional.get()));
        } else return ResponseEntity.notFound().build();
    }

    public boolean saveProject(ProjectDto projectDto){
        projectRepository.save(projectMapper.mapToProjectFromDto(projectDto));
        return true;
    }

    public boolean saveAllProjects(List<ProjectDto> projectDtos){
        List<Project> projectsToSave = new ArrayList<>();
        for(ProjectDto projectDto : projectDtos){
            projectsToSave.add(projectMapper.mapToProjectFromDto(projectDto));
        }
        projectRepository.saveAll(projectsToSave);
        return true;
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
                    projectMapper.mapToProjectDescription(projectDto.getDescriptionDto()),
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
}
