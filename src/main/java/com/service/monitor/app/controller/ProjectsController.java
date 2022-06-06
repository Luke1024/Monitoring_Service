package com.service.monitor.app.controller;

import com.service.monitor.app.domain.dto.ProjectMiniatureDto;
import com.service.monitor.app.service.ImageService;
import com.service.monitor.app.service.ProjectService;
import com.service.monitor.app.service.UserActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

//@CrossOrigin(origins = "https://luke1024.github.io", allowCredentials = "true")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/projects")
public class ProjectsController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private UserActivityService userActivityService;

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
    public ResponseEntity<String> getProjectDescription(@PathVariable long id, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        userActivityService.saveAction("requesting_project_description" + id, cookies);
        return projectService.getDescription(id);
    }

    @Cacheable("images")
    @GetMapping(value = "/image/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getProjectImage(@PathVariable long imageId){
        return imageService.getProjectImage(imageId);
    }
}