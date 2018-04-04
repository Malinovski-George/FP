package com.malinovski.helpdesk.dto;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FormFieldForFormExtenderDto {

    private List category;
    private List urgency;

    public FormFieldForFormExtenderDto() {
    }

    public FormFieldForFormExtenderDto(List category, List urgency) {
        this.category = category;
        this.urgency = urgency;
    }

    public List getCategory() {
        return category;
    }

    public void setCategory(List category) {
        this.category = category;
    }

    public List getUrgency() {
        return urgency;
    }

    public void setUrgency(List urgency) {
        this.urgency = urgency;
    }
}
