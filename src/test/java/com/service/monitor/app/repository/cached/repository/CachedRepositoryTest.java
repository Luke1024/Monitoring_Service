package com.service.monitor.app.repository.cached.repository;


import com.service.monitor.app.repository.CachedRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class CachedRepositoryTest {

    @Autowired
    private CachedRepository cachedRepository;

    @Test
    public void retrieveCachedUser(){}
}