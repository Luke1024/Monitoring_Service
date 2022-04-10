package com.service.monitor.app.domain.dto.mvc;

public class IpMvcDto {
    public String firstUsed;
    public String adress;

    public IpMvcDto(String firstUsed, String adress) {
        this.firstUsed = firstUsed;
        this.adress = adress;
    }
}
