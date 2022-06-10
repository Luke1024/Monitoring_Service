package com.service.monitor.app.service;

import com.service.monitor.app.domain.AccessTime;
import com.service.monitor.app.domain.ProtectedResource;
import com.service.monitor.app.domain.ProtectedResourceAccessAuthKey;
import com.service.monitor.app.repository.ProtectedResourceRepository;
import com.service.monitor.app.domain.enums.ResourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
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
        Optional<ProtectedResource> imageOptional = findResourceInCache(imageId, ResourceType.IMAGE);
        if(imageOptional.isPresent()){
            if(isResourceAuthorizedLogUsage(imageOptional.get(), key)){
                return Base64.getDecoder().decode(imageOptional.get().getStringResource());
            }
        }
        return null;
    }

    public ResponseEntity<String> getStringResource(String key, long stringId){
        Optional<ProtectedResource> stringResource = findResourceInCache(stringId, ResourceType.STRING);
        if(stringResource.isPresent()) {
            if (isResourceAuthorizedLogUsage(stringResource.get(), key)) {
                return ResponseEntity.ok(stringResource.get().getStringResource());
            }
        }
        return ResponseEntity.notFound().build();
    }

    public void cacheReload(){
        protectedResourcesCache = new ArrayList<>();
        protectedResourcesCache = protectedResourceRepository.findAll();
    }

    private Optional<ProtectedResource> findResourceInCache(long resourceId, ResourceType resourceType){
        for(ProtectedResource resource : protectedResourcesCache){
            long id = resource.getId();
            ResourceType type = resource.getResourceType();
            if(id==resourceId && type.equals(resourceType)){
                return Optional.of(resource);
            }
        }
        return Optional.empty();
    }

    private boolean isResourceAuthorizedLogUsage(ProtectedResource resource, String key){
        for(ProtectedResourceAccessAuthKey authKeyRegister : resource.getKeyRegisters()){
           if(authKeyRegister.getAuthKey() != null){
               if(authKeyRegister.getAuthKey().equals(key)){
                   authKeyRegister.getAccessTimeList().add(new AccessTime(LocalDateTime.now(), authKeyRegister));
                   return true;
               }
           }
        }
        return false;
    }
}
