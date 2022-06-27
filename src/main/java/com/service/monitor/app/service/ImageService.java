package com.service.monitor.app.service;

import com.service.monitor.app.domain.Image;
import com.service.monitor.app.domain.dto.crud.ImageDto;
import com.service.monitor.app.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
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

    public List<ImageDto> getImageDataList(){
        List<ImageDto> imageDtos = new ArrayList<>();
        Iterable<Image> imageList = imageRepository.findAll();
        for(Image image : imageList){
            imageDtos.add(mapToImageData(image));
        }
        return imageDtos;
    }

    public ResponseEntity<ImageDto> getImageData(long id){
        Optional<Image> imageOptional = imageRepository.findById(id);
        if(imageOptional.isPresent()){
            return ResponseEntity.ok(mapToImageData(imageOptional.get()));
        } else return ResponseEntity.notFound().build();
    }

    private ImageDto mapToImageData(Image image){
        return new ImageDto(
                image.getId(),
                image.getName(),
                "",
                image.getBase64image().length());
    }

    public boolean saveImage(ImageDto imageDto){
        imageRepository.save(new Image(
                imageDto.getName(),
                imageDto.getBase64image()
        ));
        return true;
    }

    public boolean updateImage(ImageDto imageDto){
        Optional<Image> imageToUpdate = imageRepository.findById(imageDto.getId());
        if(imageToUpdate.isPresent()){
            imageRepository.save(imageToUpdate.get());
            return true;
        } else return false;
    }

    public boolean deleteImage(long id){
        imageRepository.deleteById(id);
        return true;
    }

    public boolean deleteAllImages(){
        imageRepository.deleteAll();
        return true;
    }
}
