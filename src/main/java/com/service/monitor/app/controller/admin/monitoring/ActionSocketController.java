package com.service.monitor.app.controller.admin.monitoring;

import com.service.monitor.app.service.monitoring.ActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
public class ActionSocketController {

    private Logger logger = LoggerFactory.getLogger(ActionSocketController.class);

    @Autowired
    private ActivityService activityService;

    /*@MessageMapping("action_feed")
    public Flux<String> requestResponse(String userKey) {
        return activityService.getActionFeed(userKey);
    }*/
}
