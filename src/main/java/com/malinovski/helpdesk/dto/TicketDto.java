package com.malinovski.helpdesk.dto;

import com.google.gson.annotations.SerializedName;
import com.malinovski.helpdesk.model.State;
import com.malinovski.helpdesk.model.Urgency;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class TicketDto {

    private int id;

    private String name;
    private String description;
    private Timestamp createdOn;
    private String createdOnString;
    private Timestamp desiredResolutionDate;
    private String desiredResolutionDateString;

    @SerializedName("state")
    private State state;
    private String stateName;
    private int categoryId;
    private String categoryName;

    @SerializedName("urgency")
    private Urgency urgency;
    private String urgencyName;
    private int urgencyId;
    private String comment;
    private int owner;
    private String ownerName;
    private String approverName;
    private String assigneeName;
    private List actions = new LinkedList();
    private FeedbackDto feedbackDto;
    private List<MultipartFile> attachments = new LinkedList<>();

    public TicketDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public Timestamp getDesiredResolutionDate() {
        return desiredResolutionDate;
    }

    public void setDesiredResolutionDate(Timestamp desiredResolutionDate) {
        this.desiredResolutionDate = desiredResolutionDate;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Urgency getUrgency() {
        return urgency;
    }

    public void setUrgency(Urgency urgency) {
        this.urgency = urgency;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public int getUrgencyId() {
        return urgencyId;
    }

    public void setUrgencyId(int urgencyId) {
        this.urgencyId = urgencyId;
    }

    public String getCreatedOnString() {
        return createdOnString;
    }

    public void setCreatedOnString(String createdOnString) {
        this.createdOnString = createdOnString;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getUrgencyName() {
        return urgencyName;
    }

    public void setUrgencyName(String urgencyName) {
        this.urgencyName = urgencyName;
    }

    public String getDesiredResolutionDateString() {
        return desiredResolutionDateString;
    }

    public FeedbackDto getFeedbackDto() {
        return feedbackDto;
    }

    public void setFeedbackDto(FeedbackDto feedbackDto) {
        this.feedbackDto = feedbackDto;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getApproverName() {
        return approverName;
    }

    public void setApproverName(String approverName) {
        this.approverName = approverName;
    }

    public String getAssigneeName() {
        return assigneeName;
    }

    public void setAssigneeName(String assigneeName) {
        this.assigneeName = assigneeName;
    }

    public List getActions() {
        return actions;
    }

    public void setActions(List actions) {
        this.actions = actions;
    }

    public void setDesiredResolutionDateString(String desiredResolutionDateString) {
        this.desiredResolutionDateString = desiredResolutionDateString;
    }

    public List<MultipartFile> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<MultipartFile> attachments) {
        this.attachments = attachments;
    }

    public void addAttachment(MultipartFile mFile) {
        attachments.add(mFile);
    }

    public static Builder newBuilder() {
        return new TicketDto().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder setId(int id) {
            TicketDto.this.id = id;
            return this;
        }

        public Builder setName(String name) {
            TicketDto.this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            TicketDto.this.description = description;
            return this;
        }

        public Builder setCreated_on(Timestamp created_on) {
            TicketDto.this.createdOn = created_on;

            SimpleDateFormat dateFormat = new SimpleDateFormat("MM dd, yyyy");
            String stringDate = dateFormat.format(created_on);
            setCreatedOnString(stringDate);
            return this;
        }

        public Builder setCreatedOnString(String createdOnString) {
            TicketDto.this.createdOnString = createdOnString;
            return this;
        }

        public Builder setDesired_resolution_date(Timestamp desired_resolution_date) {
            TicketDto.this.desiredResolutionDate = desired_resolution_date;

            return this;
        }

        public Builder setDesiredResolutionDateString(String desiredResolutionDateString) {
            TicketDto.this.desiredResolutionDateString = desiredResolutionDateString;
            return this;
        }

        public Builder setState_id(State state_id) {
            TicketDto.this.state = state_id;
            setStateName(state_id.toString());
            return this;
        }

        public Builder setStateName(String stateName) {
            TicketDto.this.stateName = stateName;
            return this;
        }

        public Builder setCategory_id(int category_id) {
            TicketDto.this.categoryId = category_id;
            return this;
        }

        public Builder setCategoryName(String categoryName) {
            TicketDto.this.categoryName = categoryName;
            return this;
        }

        public Builder setUrgency(Urgency urgency) {
            TicketDto.this.urgency = urgency;
            setUrgencyName(urgency.toString());
            return this;
        }

        public Builder setUrgencyName(String urgencyName) {
            TicketDto.this.urgencyName = urgencyName;
            return this;
        }

        public Builder setUrgencyId(int urgencyId) {
            TicketDto.this.urgencyId = urgencyId;
            return this;
        }

        public Builder setComment(String comment) {
            TicketDto.this.comment = comment;
            return this;
        }

        public Builder setOwnerName(String name) {
            TicketDto.this.ownerName = name;
            return this;
        }

        public Builder setApproverName(String name) {
            TicketDto.this.approverName = name;
            return this;
        }

        public Builder setAssigneeName(String name) {
            TicketDto.this.assigneeName = name;
            return this;
        }

        public Builder setOwner(int ownerId) {
            TicketDto.this.owner = ownerId;
            return this;
        }

        public TicketDto build() {
            return TicketDto.this;
        }
    }
}



