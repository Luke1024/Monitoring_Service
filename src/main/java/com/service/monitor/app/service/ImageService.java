package com.service.monitor.app.service;

import com.service.monitor.app.domain.Image;
import com.service.monitor.app.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public byte[] getProjectImage(long imageId){
        Optional<Image> image = imageRepository.findById(imageId);
        if(image.isPresent()){
            return decodeImage(image.get().getBase64image());
        }
        return null;
    }

    private byte[] decodeImage(String base64encodedImage){
        return Base64.getDecoder().decode(base64encodedImage);
    }
}
