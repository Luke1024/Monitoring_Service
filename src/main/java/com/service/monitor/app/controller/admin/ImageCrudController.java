package com.service.monitor.app.controller.admin;

import com.service.monitor.app.domain.dto.crud.ImageDto;
import com.service.monitor.app.service.AdminAuthService;
import com.service.monitor.app.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/crud/")
public class ImageCrudController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private AdminAuthService adminAuthService;

    @GetMapping(value = "images/{adminKey}")
    public List<ImageDto> getImages(@PathVariable String adminKey){
        if(authorize(adminKey)){
            return imageService.getImageDataList();
        } else return new ArrayList<>();
    }

    @GetMapping(value = "images/{id}/{adminKey}")
    public ResponseEntity<ImageDto> getImage(@PathVariable long id, @PathVariable String adminKey){
        if(authorize(adminKey)){
            return imageService.getImageData(id);
        } else return ResponseEntity.ok().build();
    }

    @PostMapping(value = "images/{adminKey}")
    public boolean saveImage(@RequestBody ImageDto imageDto, @PathVariable String adminKey){
        if(authorize(adminKey)){
            return imageService.saveImage(imageDto);
        } else return false;
    }

    @PutMapping(value = "images/{adminKey}")
    public boolean updateImage(@RequestBody ImageDto imageDto, @PathVariable String adminKey){
        if(authorize(adminKey)){
            return imageService.updateImage(imageDto);
        } else return false;
    }

    @DeleteMapping(value = "images/{imageId}/{adminKey}")
    public boolean deleteProject(@PathVariable long id, @PathVariable String adminKey){
        if(authorize(adminKey)){
            return imageService.deleteImage(id);
        } else return false;
    }

    @DeleteMapping(value = "images/{adminKey}/{deleteAdminKey}")
    public boolean purgeImageData(@PathVariable String adminKey, @PathVariable String deleteAdminKey){
        if(authorize(adminKey) && authorize(deleteAdminKey)){
            return imageService.deleteAllImages();
        } else return false;
    }

    private boolean authorize(String adminKey){
        return adminAuthService.authorize(adminKey);
    }

    private boolean authorizeDelete(String adminDeleteKey){
        return adminAuthService.authorizeDelete(adminDeleteKey);
    }
}
