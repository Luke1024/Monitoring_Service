package com.service.monitor.app.controller;

import com.service.monitor.app.domain.dto.ProjectMiniatureDto;
import com.service.monitor.app.service.ImageService;
import com.service.monitor.app.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectsController {

    private Logger logger = LoggerFactory.getLogger(ProjectsController.class);

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private CacheManager cacheManager;

    private String authKey = "example_token";

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
    public ResponseEntity<String> getProjectDescription(@PathVariable long id){
        return projectService.getDescription(id);
    }

    @Cacheable("images")
    @GetMapping(value = "/image/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getProjectImage(@PathVariable long imageId){
        return imageService.getProjectImage(imageId);
    }

    @GetMapping("/cache/clear/{authToken}")
    public void clearCache(@PathVariable String authToken){
        if(authToken.equals(authKey)){
            logger.info("Clearing all caches.");
            cacheManager.getCacheNames().stream()
                    .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
        }
    }
}