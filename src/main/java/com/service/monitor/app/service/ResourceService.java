package com.service.monitor.app.service;

import com.service.monitor.app.domain.AuthKey;
import com.service.monitor.app.domain.ProtectedResource;
import com.service.monitor.app.repository.ProtectedResourceRepository;
import com.service.monitor.app.domain.enums.ResourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class ResourceService { ;

    @Autowired
    private ProtectedResourceRepository protectedResourceRepository;

    private Iterable<ProtectedResource> protectedResourcesCache;

    @PostConstruct
    public void init() {
        protectedResourcesCache = protectedResourceRepository.findAll();
    }

    public byte[] getImage(String key, long imageId){
        Optional<ProtectedResource> imageOptional = findResourceInCache(imageId, key, ResourceType.IMAGE);
        if(imageOptional.isPresent()) {
            return Base64.getDecoder().decode(imageOptional.get().getStringResource());
        } else {
            return null;
        }
    }

    public ResponseEntity<String> getStringResource(String key, long stringId){
        Optional<ProtectedResource> stringResource = findResourceInCache(stringId, key, ResourceType.STRING);
        if(stringResource.isPresent()) {
            return ResponseEntity.ok(stringResource.get().getStringResource());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public void cacheReload(){
        protectedResourcesCache = new ArrayList<>();
        protectedResourcesCache = protectedResourceRepository.findAll();
    }

    public Optional<ProtectedResource> findResourceInCache(long resourceId, String keyInput, ResourceType resourceType){
        for(ProtectedResource resource : protectedResourcesCache){
            /*long id = resource.getId();
            List<AuthKey> authKeyList = resource.getAuthKeys();
            ResourceType type = resource.getResourceType();
            if(id==resourceId && isKeyExist(authKeyList, keyInput) && type.equals(resourceType)){
                resource.resourceWasUsed();
                return Optional.of(resource);
            }*/
        }
        return Optional.empty();
    }

    private boolean isKeyExist(List<AuthKey> authKeyList, String keyInput){
        for(AuthKey authKey : authKeyList){
            if(authKey.getKeyValue().equals(keyInput)){
                //authKey.keyWasUsed();
                return true;
            }
        }
        return false;
    }
}
