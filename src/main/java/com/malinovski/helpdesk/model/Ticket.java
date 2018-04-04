package com.malinovski.helpdesk.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "ticket")
public class Ticket {

    private int id;

    @Size(min = 1, max = 100, message = "Ticket name must be between 1 and 100 characters long.")
    @NotNull(message = "Ticket name cannot be empty")
    @Pattern(regexp = "^[a-zA-Z][\\sa-zA-Z0-9-\\~\\.\\\"\\(\\)\\,\\:\\;\\<\\>\\@\\[\\]\\!\\#\\$\\%\\&\\'\\*\\+\\-\\/\\=\\?\\^\\_\\`\\{\\|\\}]{1,100}$",
            message = "Ticket name pattern error")
    private String name;

    @Size(min = 1, max = 500, message = "Ticket description must be between 1 and 500 characters long.")
    @Pattern(regexp = "^[a-zA-Z][\\sa-zA-Z0-9-\\~\\.\\\"\\(\\)\\,\\:\\;\\<\\>\\@\\[\\]\\!\\#\\$\\%\\&\\'\\*\\+\\-\\/\\=\\?\\^\\_\\`\\{\\|\\}]{1,500}$",
            message = "Ticket description pattern error")
    private String description;

    @NotNull(message = "Ticket creatin time cannot be empty")
    private Timestamp createdOn;

    private Timestamp desiredResolutionDate;
    private User assignee;

    @NotNull(message = "Ticket owner name cannot be empty")
    private User owner;

    private State state;

    @NotNull(message = "Ticket category cannot be empty")
    private Category category;

    @NotNull(message = "Ticket urgency cannot be empty")
    private Urgency urgency;
    private User approver;

    private List<Comment> userComments = new LinkedList<>();
    private List<History> ticketHistory = new LinkedList<>();
    private List<Attachment> ticketAttachments = new LinkedList<>();
    private Feedback feedback;

    public Ticket() {
    }

    public Ticket(State state,
                  String name,

                  Timestamp desiredResolutionDate,
                  Urgency urgency,
                  Timestamp createdOn,
                  User owner) {


        this.name = name;

        this.desiredResolutionDate = desiredResolutionDate;
        this.urgency = urgency;
        this.state = state;
        this.createdOn = createdOn;
        this.owner = owner;
        this.approver = null;
        this.assignee = null;

    }

    @Id
    @GeneratedValue
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "NAME", nullable = false, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "DISCRIPTION", length = 500)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "CREATED_ON", nullable = false)
    @Basic
    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    @Column(name = "DESIRED_RESOLUTION_DATE")
    @Basic
    public Timestamp getDesiredResolutionDate() {
        return desiredResolutionDate;
    }

    public void setDesiredResolutionDate(Timestamp desiredResolutionDate) {
        this.desiredResolutionDate = desiredResolutionDate;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ASSIGNEE")
    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OWNER")
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @ManyToOne
    @JoinColumn(name = "CATEGORY")

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "APPROVER")
    public User getApprover() {
        return approver;
    }

    public void setApprover(User approver) {
        this.approver = approver;
    }

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "STATE", nullable = false, length = 15)
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "URGENCY", nullable = false, length = 15)
    public Urgency getUrgency() {
        return urgency;
    }

    public void setUrgency(Urgency urgency) {
        this.urgency = urgency;
    }

    @OneToMany(mappedBy = "ticket", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<Comment> getUserComments() {
        return userComments;
    }

    public void setUserComments(List<Comment> userComments) {
        this.userComments = userComments;
    }

    @OneToMany(mappedBy = "ticket", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<History> getTicketHistory() {
        return ticketHistory;
    }

    public void setTicketHistory(List<History> ticketHistory) {
        this.ticketHistory = ticketHistory;
    }

    @OneToMany(mappedBy = "ticket", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<Attachment> getTicketAttachments() {
        return ticketAttachments;
    }

    public void setTicketAttachments(List<Attachment> ticketAttachments) {
        this.ticketAttachments = ticketAttachments;
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "ticket", cascade = CascadeType.ALL)
    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public static Builder newBuilder() {
        return new Ticket().new Builder();
    }

    public class Builder {

        private Builder() {
        }


        public Builder setId(int id) {
            Ticket.this.id = id;
            return this;
        }

        public Builder setName(String name) {
            Ticket.this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            Ticket.this.description = description;
            return this;
        }

        public Builder setCreatedOn(Timestamp createdOn) {
            Ticket.this.createdOn = createdOn;
            return this;
        }

        public Builder setDesiredResolutionDate(Timestamp desiredResolutionDate) {
            Ticket.this.desiredResolutionDate = desiredResolutionDate;
            return this;
        }

        public Builder setAssignee(User assignee) {
            Ticket.this.assignee = assignee;
            return this;
        }

        public Builder setOwner(User owner) {
            Ticket.this.owner = owner;
            return this;
        }

        public Builder setState(State state) {
            Ticket.this.state = state;
            return this;
        }

        public Builder setCategory(Category category) {
            Ticket.this.category = category;
            return this;
        }

        public Builder setUrgency(Urgency urgency) {
            Ticket.this.urgency = urgency;
            return this;
        }

        public Builder setApprover(User approver) {
            Ticket.this.approver = approver;
            return this;
        }

        public Builder setUserComments(List<Comment> userComments) {
            Ticket.this.userComments = userComments;
            return this;
        }

        public Builder setTicketHistory(List<History> ticketHistory) {
            Ticket.this.ticketHistory = ticketHistory;
            return this;
        }

        public Builder setFeedback(Feedback feedback) {
            Ticket.this.feedback = feedback;
            return this;
        }

        public Ticket build() {
            return Ticket.this;
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        if (!name.equals(ticket.name)) {
            return false;
        }
        if (description != null ? !description.equals(ticket.description) : ticket.description != null) {
            return false;
        }
        if (desiredResolutionDate != null ? !desiredResolutionDate.equals(ticket.desiredResolutionDate) : ticket.desiredResolutionDate != null) {
            return false;
        }
        if (!category.equals(ticket.category)) {
            return false;
        }
        return urgency == ticket.urgency;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (desiredResolutionDate != null ? desiredResolutionDate.hashCode() : 0);
        result = 31 * result + category.hashCode();
        result = 31 * result + urgency.hashCode();
        return result;
    }

}
