package com.service.monitor.app.service.monitoring;

import com.service.monitor.app.domain.dto.messaging.StatusDto;
import org.springframework.stereotype.Service;

@Service
public class StatusService {

    private StatusDto statusDto = new StatusDto();

    public StatusDto getStatusDto(){
        return statusDto;
    }

    public void resetStatusDto(){
        statusDto = new StatusDto();
    }
}
