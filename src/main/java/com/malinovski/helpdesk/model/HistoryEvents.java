package com.malinovski.helpdesk.model;

public enum HistoryEvents {

    CREATE("Ticket is created"),
    STATUS_CHANGE("Ticket status is changed"),
    EDIT("Ticket is edited"),
    FILE_ADD("File is attached"),
    FILE_REMOVE("File is remove");

    private String displayName;

    HistoryEvents(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
