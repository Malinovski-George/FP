package com.malinovski.helpdesk.dto;

import org.springframework.stereotype.Component;

@Component
public class HistoryDto {

    private String stringDate;
    private String userName;
    private String action;
    private String description;

    public HistoryDto() {
    }

    public HistoryDto(String stringDate, String userName, String action, String description) {
        this.stringDate = stringDate;
        this.userName = userName;
        this.action = action;
        this.description = description;
    }


    public String getStringDate() {
        return stringDate;
    }

    public void setStringDate(String stringDate) {
        this.stringDate = stringDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

