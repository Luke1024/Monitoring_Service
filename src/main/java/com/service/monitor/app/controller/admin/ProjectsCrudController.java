package com.service.monitor.app.controller.admin;

import com.service.monitor.app.domain.dto.crud.ProjectDto;
import com.service.monitor.app.service.AdminAuthService;
import com.service.monitor.app.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/crud/")
public class ProjectsCrudController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private AdminAuthService adminAuthService;

    @GetMapping(value = "projects/{adminKey}")
    public List<ProjectDto> getProjects(@PathVariable String adminKey){
        if(authorize(adminKey)){
            return projectService.getAllProjects();
        } else return new ArrayList<>();
    }

    @GetMapping(value = "project/{id}/{adminKey}")
    public ResponseEntity<ProjectDto> getProject(@PathVariable long id, @PathVariable String adminKey){
        if(authorize(adminKey)){
            return projectService.getProject(id);
        } else return ResponseEntity.ok().build();
    }

    @PostMapping(value = "projects/{adminKey}")
    public boolean saveProject(@RequestBody ProjectDto projectDto, @PathVariable String adminKey){
        if(authorize(adminKey)) {
            return projectService.saveProject(projectDto);
        } else return false;
    }

    @PutMapping(value = "projects/{adminKey}")
    public boolean updateProject(@RequestBody ProjectDto projectDto, @PathVariable String adminKey){
        if(authorize(adminKey)) {
            return projectService.updateProject(projectDto);
        } else return false;
    }

    @DeleteMapping(value = "projects/{projectId}/{adminKey}")
    public boolean saveProject(@PathVariable long projectId, @PathVariable String adminKey){
        if(authorize(adminKey)) {
            return projectService.deleteProject(projectId);
        } else return false;
    }

    private boolean authorize(String adminKey){
        return adminAuthService.authorize(adminKey);
    }
}
