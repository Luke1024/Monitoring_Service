package com.service.monitor.app.controller;

import com.service.monitor.app.domain.dto.ReportDto;
import com.service.monitor.app.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/stats")
public class StatsController {

    @Autowired
    private StatsService statsService;

    @GetMapping(value="/report/{days}")
    public ReportDto getFullReport(@PathVariable int days){
        return statsService.getWeeklyReport(days);
    }

    @GetMapping(value = "/report/user/{id}")
    public ReportDto getUserReport(@PathVariable long id){
        return statsService.getUserReport(id);
    }
}
