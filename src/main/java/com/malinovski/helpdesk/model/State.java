package com.malinovski.helpdesk.model;

import com.google.gson.annotations.SerializedName;

public enum State {

    @SerializedName("0")
    DRAFT("Draft", "Create"),

    @SerializedName("1")
    NEW("New", "Submit"),

    @SerializedName("2")
    APPROVED("Approved", "Approve"),

    @SerializedName("3")
    DECLIENED("Declined", "Decline"),

    @SerializedName("4")
    IN_PROGRESS("In Progress", "Assignee to me"),

    @SerializedName("5")
    DONE("Done", "Done"),

    @SerializedName("6")
    CANCELED("Cancelled", "Cancel");


    private String displayName;
    private String action;

    State(String displayName, String action) {
        this.displayName = displayName;
        this.action = action;
    }

    public String displayName() {
        return displayName;
    }

    public String getAction() {
        return action;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
