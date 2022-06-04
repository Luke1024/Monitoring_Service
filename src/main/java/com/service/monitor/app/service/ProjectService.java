package com.service.monitor.app.service;

import com.service.monitor.app.domain.Project;
import com.service.monitor.app.domain.dto.ProjectMiniatureDto;
import com.service.monitor.app.domain.enums.ProjectType;
import com.service.monitor.app.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<ProjectMiniatureDto> getAllNormalDto(){
        return mappingProjectsToMiniatureDtoList(
                projectRepository.findByType(ProjectType.NORMAL));
    }

    public List<ProjectMiniatureDto> getAllMiniDto(){
        return mappingProjectsToMiniatureDtoList(
                projectRepository.findByType(ProjectType.MINI));
    }

    public ResponseEntity<String> getDescription(long id){
        Optional<Project> optionalProject = projectRepository.findById(id);
        if(optionalProject.isPresent()) {
            return ResponseEntity.ok(optionalProject.get().getDescriptionPage());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private List<ProjectMiniatureDto> mappingProjectsToMiniatureDtoList(List<Project> projects){
        return projects.stream().map(project -> projectToDto(project)).collect(Collectors.toList());
    }

    private ProjectMiniatureDto projectToDto(Project project){
        return new ProjectMiniatureDto(project.getId(), project.getTitle(), project.getTechnologies(),
                project.getMiniatureUrl(), project.getDescription());
    }
}
