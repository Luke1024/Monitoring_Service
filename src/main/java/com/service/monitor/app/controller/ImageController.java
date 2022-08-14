package com.service.monitor.app.controller;

import com.service.monitor.app.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "https://luke1024.github.io", allowCredentials = "true")
//@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Cacheable("images")
    @GetMapping(value = "/image/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getProjectImage(@PathVariable long imageId){
        return imageService.getProjectImage(imageId);
    }
}
