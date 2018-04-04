package com.malinovski.helpdesk.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "history")
public class History {

    private int id;
    private Timestamp date;
    private HistoryEvents action;
    private User user;
    private Ticket ticket;
    private String discription;

    public History() {
    }

    public History(Timestamp date, HistoryEvents action) {
        this.date = date;
        this.action = action;
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

    @Column(name = "DATE", nullable = false)
    @Basic
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "ACTION", nullable = false, length = 15)
    public HistoryEvents getAction() {
        return action;
    }

    public void setAction(HistoryEvents action) {
        this.action = action;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TICKET_ID")

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @Column(name = "DESCRIPTION", nullable = false)
    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public static Builder newBuilder() {
        return new History().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder setAction(HistoryEvents action) {
            History.this.action = action;
            return this;
        }

        public Builder setDate(Timestamp date) {
            History.this.date = date;
            return this;
        }

        public Builder setUser(User userId) {
            History.this.user = userId;
            return this;
        }

        public Builder setTicket(Ticket ticket) {
            History.this.ticket = ticket;
            return this;
        }

        public Builder setDiscription(String discription) {
            History.this.discription = discription;
            return this;
        }

        public History build() {
            return History.this;
        }
    }

}
