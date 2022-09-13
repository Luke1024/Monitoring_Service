package com.service.monitor.app.domain.dto.monitoring;

public class StatusDto {
    private int visits;
    private int newUsers;
    private int newContacts;
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
        newContacts++;
    }

    public void addNewAction(){
        newActions++;
    }
}
