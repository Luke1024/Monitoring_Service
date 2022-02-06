package com.service.monitor.app.domain.dto;

public class ContactDto {
    private String token;
    private String name;
    private String email;
    private String message;

    public ContactDto() {
    }

    public ContactDto(String token ,String name, String email, String message) {
        this.token = token;
        this.name = name;
        this.email = email;
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ContactDto{" +
                "token='" + token + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
