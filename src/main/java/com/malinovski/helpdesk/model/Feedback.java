package com.malinovski.helpdesk.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "feedback")
public class Feedback {

    private int id;
    private User user;

    @NotNull(message = "Feedback rate can't be empty")
    private int rate;
    private Date date;

    @Size(min = 1, max = 500, message = "Feedback comment must be between 1 and 500 characters long.")
    @Pattern(regexp = "^[a-zA-Z][\\sa-zA-Z0-9-\\~\\.\\\"\\(\\)\\,\\:\\;\\<\\>\\@\\[\\]\\!\\#\\$\\%\\&\\'\\*\\+\\-\\/\\=\\?\\^\\_\\`\\{\\|\\}]{1,100}$",
            message = "Ticket name pattern error")
    private String text;
    private Ticket ticket;

    public Feedback() {
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")

    public User getUser() {
        return user;
    }

    public void setUser(User user_id) {
        this.user = user_id;
    }

    @Column(name = "RATE", nullable = false)
    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Column(name = "DATE", nullable = false)
    @Basic
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "TEXT")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TICKET_ID")

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }


    public static Builder newBuilder() {
        return new Feedback().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder setUser(User user) {
            Feedback.this.user = user;
            return this;
        }

        public Builder setRate(int rate) {
            Feedback.this.rate = rate;
            return this;
        }

        public Builder setDate(Date date) {
            Feedback.this.date = date;
            return this;
        }

        public Builder setText(String text) {
            Feedback.this.text = text;
            return this;
        }

        public Builder setTicket(Ticket ticket) {
            Feedback.this.ticket = ticket;
            return this;
        }

        public Feedback build() {
            return Feedback.this;

        }
    }
}
