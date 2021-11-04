package com.service.monitor.app.controller;

import com.service.monitor.app.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/stats")
public class StatsController {

    @Autowired
    private StatsService statsService;

    @GetMapping(value="/report/weekly")
    public String getFullReport(){
        return statsService.getWeeklyReport();
    }
}
