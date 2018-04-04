package com.malinovski.helpdesk.dto;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class CommentDto {

    private String userName;
    private String text;
    private String stringDate;
    private Timestamp date;
    private int owner;

    public CommentDto() {
    }

    public CommentDto(String userName, String text, String stringDate, Timestamp date) {
        this.userName = userName;
        this.text = text;
        this.stringDate = stringDate;
        this.date = date;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getStringDate() {
        return stringDate;
    }

    public void setStringDate(String stringDate) {
        this.stringDate = stringDate;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }
}
