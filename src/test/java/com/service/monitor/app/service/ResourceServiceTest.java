package com.service.monitor.app.service;

import com.service.monitor.app.domain.AuthKey;
import com.service.monitor.app.domain.ProtectedResource;
import com.service.monitor.app.domain.enums.ResourceType;
import com.service.monitor.app.repository.AuthKeyRepository;
import com.service.monitor.app.repository.ProtectedResourceRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Base64;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ResourceServiceTest {

    @Autowired
    private ProtectedResourceRepository resourceRepository;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private AuthKeyRepository authKeyRepository;

    String imageKey1 = "111111111111111111111";
    AuthKey authKey1 = new AuthKey(imageKey1, "me");
    String imageEncoded1 = getTestImage1();

    String imageKey2 = "222222222222222222222";
    AuthKey authKey2 = new AuthKey(imageKey2, "someone");
    String imageEncoded2 = "image";

    String stringKey1 = "33333333333333333333";
    AuthKey authKey3 = new AuthKey(stringKey1, "Bob");
    String string1 = "string1";

    String stringKey2 = "44444444444444444444";
    AuthKey authKey4 = new AuthKey(stringKey2, "Tom");
    String string2 = "string2";

    ProtectedResource imageResource1;
    ProtectedResource imageResource2;
    ProtectedResource stringResource1;
    ProtectedResource stringResource2;

    @Before
    public void createEntries(){
        authKeyRepository.save(authKey1);
        imageResource1 = new ProtectedResource(imageEncoded1, ResourceType.IMAGE, authKey1);
        resourceRepository.save(imageResource1);

        authKeyRepository.save(authKey2);
        imageResource2 = new ProtectedResource(imageEncoded2, ResourceType.IMAGE, authKey2);
        resourceRepository.save(imageResource2);

        authKeyRepository.save(authKey3);
        stringResource1 = new ProtectedResource(string1, ResourceType.STRING, authKey3);
        resourceRepository.save(stringResource1);

        authKeyRepository.save(authKey4);
        stringResource2 = new ProtectedResource(string2, ResourceType.STRING, authKey4);
        resourceRepository.save(stringResource2);

        //necessary because there is no time to cache everything when method calling begins;
        resourceService.cacheReload();
    }

    @Test
    public void testGetImage(){


        byte[] imageReceivedDecode = resourceService.getImage(imageKey1, imageResource1.getId());

        Assert.assertNotNull(imageReceivedDecode);
        Assert.assertEquals(imageEncoded1, Base64.getEncoder().encodeToString(imageReceivedDecode));

        Optional<ProtectedResource> imageResource = resourceService.findResourceInCache(imageResource1.getId(), ResourceType.IMAGE);

        Assert.assertTrue(imageResource.get().getKeyRegisters().size()==1);
        Assert.assertTrue(imageResource.get().getKeyRegisters().get(0).getAccessTimeList().size()==1);
    }

    @Test
    public void testGetImageWithIncorrectKey(){
        Assert.assertNull(resourceService.getImage(imageKey2, imageResource1.getId()));
    }

    private String getTestImage1(){
        return "/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAIBAQIBAQICAgICAgICAwUDAwMDAwYEBAMFBwYHBwcGBwcICQsJCAgKCAcHCg0KCgsMDAwMBwkODw0MDgsMDAz/2wBDAQICAgMDAwYDAwYMCAcIDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAz/wAARCAAgACADASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD9rPjX+278F/2a/FVvoXxF+Lvww8Aa3d2i38Gn+JPFVjpV1Nbs7osyxTyo5jLxyKGAwTGwzlTX5Qf8HHX/AAcZ6V8Gfg1pHwx/Zi+Jfg/xH4r8fWlw+v8Ai7wrrSajJ4UsAVjSO2ng3QpeXB84eYJfNtkhLBFeaCeP80f+Dnr/AIJsa7+wv/wUc8SeNc/a/AXx51XUPFmgXkl7HNci8d45tUtZY1RDH5V1dbo/lZTBPAPNkkWYJ93/APBEf/g2R+A3xj074bftI6h8UtQ+N/w01S0k1LTPCeq+Cf7Ajkv4ZzC0Woq15cieO3ninR4F/dTPGp8yaDckwB2H/Bub/wAHJln8YPAl18If2m/Gvh/QvEHgvSvtei/EHxPrtrp0PiCzSSKH7JezXMieZqCeapWVdzXESSNIBLE8tx+t3wU/bd+C/wC0p4quNC+HXxd+GHj/AFu0tGv59P8ADfiqx1W6ht1dEaZooJXcRh5I1LEYBkUZywr8gf8Agt//AMGzHwjn8VfGD9qRvjVqHwl8KfZLrxb4l0WbQY9aa81R3eSb7FNPqFqFkvJ3RYrZ2INxPtR1R44o/wA8P+DYX/gmxrv7dH/BRzw341z9k8BfAbVdP8Wa/eR3scNybxHkm0u1ijZHMnm3Vruk+VVEEE482ORoQ4Af8HPX/BSfXf26P+CjniTwVj7J4C+A2q6h4T0Czkso4bk3iPHDql1LIruZPNurXbH8yqIIID5UcjTF/v8A/wCCDf8AwchfBvw1rHwk/ZRs/g14g+FXg+XHh7w3rc3i1vE813rF3db0W8UWVuU+13U8pMsYMccsyKIooMtD+v3xS/4J7fAL44+O77xT41+B/wAIPGHibVPL+2avrfg3TtQv7vy41ij8yeWFpH2xoiDcThUUDgAV+UP/AAcZf8G2ln8YPAlr8Xv2ZPBXh/QvEHgvSvsmtfD7wxoVrp0PiCzSSWb7XZQ20aeZqCeawaJtzXESRrGRLEkVwAZ//Bb/AP4OZ/hHB4q+MH7LbfBXUPi14U+yXXhLxLrU2vR6K1nqiO8c32KGfT7oNJZzojRXLqALiDciMiRyyfnh/wAGwv8AwUn139hf/go54b8FY+1+Avjzqun+E9fs47KOa5F47yQ6XdRSM6GPyrq62yfMymCec+VJIsJT9H/+Dc3/AINtLP4P+BLr4vftN+CvD+u+IPGmlfZNF+H3ifQrXUYfD9m8kU32u9huY38vUH8pQsS7Wt4nkWQmWV4rf9Xvhb/wT2+AXwO8d2PinwV8D/hB4P8AE2l+Z9j1fRPBunaff2nmRtFJ5c8UKyJujd0O0jKuwPBIoA//2Q==";
    }

    @Test
    public void testGetString(){
        ResponseEntity<String> stringReceived = resourceService.getStringResource(stringKey1, stringResource1.getId());

        Assert.assertEquals(200, stringReceived.getStatusCode().value());
        Assert.assertEquals(string1, stringReceived.getBody());
    }

    @Test
    public void testGetStringWithIncorrectKey(){
        ResponseEntity<String> stringReceived = resourceService.getStringResource(stringKey2, stringResource1.getId());

        Assert.assertEquals(404, stringReceived.getStatusCode().value());
        Assert.assertNull(stringReceived.getBody());
    }
}