package com.service.monitor.app.domain.dto.messaging;

public class StatusDto {
    private int visits;
    private int newUsers;
    private int newContacs;
    private int newActions;

    public StatusDto() {
    }

    public void addVisit(){
        visits++;
    }

    public void addNewUser(){
        newUsers++;
    }

    public void addNewContact(){
        newContacs++;
    }

    public void addNewAction(){
        newActions++;
    }
}
