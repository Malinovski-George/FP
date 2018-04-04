package com.malinovski.helpdesk.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    private int id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String position;
    private String phone;
    private Address address;
    private Role role;

    private Set<Ticket> ticketsOwner = new HashSet<>();
    private Set<Ticket> ticketsAssignee = new HashSet<>();
    private Set<Ticket> ticketsApprover = new HashSet<>();
    private Set<Comment> ticketComments = new HashSet<>();
    private Set<History> userHistory = new HashSet<>();
    private Set<Feedback> feedback = new HashSet<>();

    public User() {
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

    @Column(name = "EMAIL", nullable = false, length = 100)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "PASSWORD", nullable = false, length = 20)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "FIRSTNAME", nullable = false, length = 50)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "LASTNAME", nullable = false, length = 50)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "POSITION", nullable = false, length = 20)
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Column(name = "PHONE", length = 20)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "ROLE_ID", nullable = false, length = 3)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner", cascade = CascadeType.ALL)

    public Set<Ticket> getTicketsOwner() {
        return ticketsOwner;
    }

    public void setTicketsOwner(Set<Ticket> ticketsOwner) {
        this.ticketsOwner = ticketsOwner;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "assignee", cascade = CascadeType.ALL)

    public Set<Ticket> getTicketsAssignee() {
        return ticketsAssignee;
    }

    public void setTicketsAssignee(Set<Ticket> ticketsAssignee) {
        this.ticketsAssignee = ticketsAssignee;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "approver", cascade = CascadeType.ALL)

    public Set<Ticket> getTicketsApprover() {
        return ticketsApprover;
    }

    public void setTicketsApprover(Set<Ticket> ticketsApprover) {
        this.ticketsApprover = ticketsApprover;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)

    public Set<Comment> getTicketComments() {
        return ticketComments;
    }

    public void setTicketComments(Set<Comment> ticketComments) {
        this.ticketComments = ticketComments;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)

    public Set<History> getTicketHistory() {
        return userHistory;
    }

    public void setTicketHistory(Set<History> userHistory) {
        this.userHistory = userHistory;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)

    public Set<Feedback> getFeedback() {
        return feedback;
    }

    public void setFeedback(Set<Feedback> feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
