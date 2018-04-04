package com.malinovski.helpdesk.dto;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class FeedbackDto {

    private int rate;
    private Date date;
    private String text;
    private int ticketId;

    public FeedbackDto() {
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }
}
