package com.malinovski.helpdesk.dto;

import org.springframework.stereotype.Component;

@Component
public class AttachmentDto {

    private int id;
    private String fileName;
    private boolean isDeleted;

    public AttachmentDto() {
        this.isDeleted = false;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
