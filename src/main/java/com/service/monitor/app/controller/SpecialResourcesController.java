package com.service.monitor.app.controller;

import com.service.monitor.app.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RequestMapping("/resource")
public class SpecialResourcesController {

    @Autowired
    private ResourceService resourceService;

    @GetMapping(value = "/image/{key}/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getResourceImage(
            @PathVariable String key,
            @PathVariable long id){
        return resourceService.getImage(key,id);
    }

    @GetMapping(value = "/string/{key}/{id}")
    public ResponseEntity<String> getStringResource(
            @PathVariable String key,
            @PathVariable long id){
        return resourceService.getStringResource(key,id);
    }
}
