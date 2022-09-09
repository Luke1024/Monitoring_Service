package com.service.monitor.app.controller.admin;

import com.service.monitor.app.controller.ProjectController;
import com.service.monitor.app.service.AdminAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class CacheController {

    private Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private AdminAuthService adminAuthService;

    @GetMapping("/cache/clear/{authToken}")
    public void clearCache(@PathVariable String authToken){
        if(isKeyValid(authToken)){
            clear();
        }
    }

    private void clear(){
        logger.info("Clearing all caches.");
        cacheManager.getCacheNames().stream()
                .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
    }

    private boolean isKeyValid(String inputKey){
        return adminAuthService.authorize(inputKey);
    }
}
