package com.service.monitor.app.controller;

import com.service.monitor.app.domain.dto.DescriptionDto;
import com.service.monitor.app.domain.dto.ProjectMiniatureDto;
import com.service.monitor.app.service.ProjectService;
import com.service.monitor.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

//@CrossOrigin(origins = "https://luke1024.github.io", allowCredentials = "true")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userActivityService;

    @GetMapping(value = "/normal")
    @Cacheable("normalProjects")
    public List<ProjectMiniatureDto> getAllNormalProjects(){
        return projectService.getAllNormalDto();
    }

    @Cacheable("miniProjects")
    @GetMapping(value = "/mini")
    public List<ProjectMiniatureDto> getAllMiniProjects(){
        return projectService.getAllMiniDto();
    }

    @Cacheable("descriptions")
    @GetMapping(value = "/description/{id}")
    public ResponseEntity<DescriptionDto> getProjectDescription(@PathVariable long id, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        userActivityService.saveAction("requesting_project_description_" + id, cookies);
        return projectService.getDescription(id);
    }
}