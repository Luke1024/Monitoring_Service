package com.service.monitor.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class AdminController {

    private Logger logger = LoggerFactory.getLogger(ProjectsController.class);

    @Autowired
    private CacheManager cacheManager;

    private String authKey = "example_token";

    @GetMapping("/cache/clear/{authToken}")
    public void clearCache(@PathVariable String authToken){
        if(isKeyValid(authToken)){
            logger.info("Clearing all caches.");
            cacheManager.getCacheNames().stream()
                    .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
        }
    }

    private boolean isKeyValid(String inputKey){
        return inputKey.equals(authKey);
    }
}
