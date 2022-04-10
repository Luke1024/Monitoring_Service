package com.service.monitor.app.domain.dto.mvc;

public class UserMvcDto {
    public long id;
    public String lastIpAdress;
    public int adressesUsed;
    public int numberOfActions;
    public int contacts;
    public String lastActive;

    public UserMvcDto(long id, String lastIpAdress, int adressesUsed, int numberOfActions, int contacts, String lastActive) {
        this.id = id;
        this.lastIpAdress = lastIpAdress;
        this.adressesUsed = adressesUsed;
        this.numberOfActions = numberOfActions;
        this.contacts = contacts;
        this.lastActive = lastActive;
    }
}
