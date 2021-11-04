package com.service.monitor.app.service;

import com.service.monitor.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsService {

    @Autowired
    private UserRepository userRepository;

    public String getFullReport(){

        return "Reporting string";
    }
}
