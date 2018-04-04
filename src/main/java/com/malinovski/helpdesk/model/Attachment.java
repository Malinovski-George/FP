package com.malinovski.helpdesk.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "attachment")
public class Attachment {

    private int id;

    @NotNull(message = "Attachment's name cannot be empty")
    private String fileName;
    private byte[] content;

    @NotNull(message = "Attachment's ticket cannot be empty")
    private Ticket ticket;
    private String type;

    public Attachment() {
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

    @Lob
    @Column(name = "DATA")
    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TICKET_ID")
    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticketId) {
        this.ticket = ticketId;
    }

    @Column(name = "NAME")
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Column(name = "TYPE", length = 100, nullable = false)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
