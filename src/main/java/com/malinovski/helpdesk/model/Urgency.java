package com.malinovski.helpdesk.model;

import com.google.gson.annotations.SerializedName;

public enum Urgency {

    @SerializedName("0")
    CRITICAL("Critical"),

    @SerializedName("1")
    HIGH("High"),

    @SerializedName("2")
    MEDIUM("Medium"),

    @SerializedName("3")
    LOW("Low");

    private String displayName;

    Urgency(String displayName) {
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
