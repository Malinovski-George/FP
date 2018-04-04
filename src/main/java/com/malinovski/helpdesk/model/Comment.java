package com.malinovski.helpdesk.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "comment")
public class Comment {

    private int id;

    @Size(min = 1, max = 500, message = "Comment text must be between 1 and 500 characters long.")
    @NotNull(message = "Comment text cannot be empty")
    @Pattern(regexp = "^[a-zA-Z][\\sa-zA-Z0-9-\\~\\.\\\"\\(\\)\\,\\:\\;\\<\\>\\@\\[\\]\\!\\#\\$\\%\\&\\'\\*\\+\\-\\/\\=\\?\\^\\_\\`\\{\\|\\}]{1,500}$",
            message = "Ticket name pattern error")
    private String text;

    @NotNull(message = "Comment creatin time cannot be empty")
    private Timestamp date;

    @NotNull(message = "Comment owner cannot be empty")
    private User user;

    @NotNull(message = "Comment ticket cannot be empty")
        private Ticket ticket;

    public Comment() {
    }

    public Comment(String text, Timestamp date) {
        this.text = text;
        this.date = date;
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

    @Column(name = "TEXT", nullable = false, length = 500)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Column(name = "DATE", nullable = false)
    @Basic
    public Date getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional=false)
    @JoinColumn(name = "USER_ID")

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional=false)
    @JoinColumn(name = "TICKET_ID")

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }


    public static Builder newBuilder() {
        return new Comment().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder setText(String text) {
            Comment.this.text = text;
            return this;
        }

        public Builder setDate(Timestamp date) {
            Comment.this.date = date;
            return this;
        }

        public Builder setUserId(User userId) {
            Comment.this.user = userId;
            return this;
        }

        public Comment build() {
            return Comment.this;
        }
    }
}
