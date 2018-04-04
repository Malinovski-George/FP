package com.malinovski.helpdesk.dao;

import com.malinovski.helpdesk.model.Ticket;

import java.util.List;

public interface TicketDao {

    void createTicket(Ticket ticket);

    Ticket getTicketById(int id);

    void updateTicket(Ticket ticket);

    List<Ticket> getEmployeeTickets(String employeeId);

    List<Ticket> getEmployeeAllTickets(String employeeId);

    List<Ticket> getManagerTickets(String managerId);

    List<Ticket> getManagerAllTickets(String managerId);

    List<Ticket> getEngineerTickets(String engineerId);

    List<Ticket> getEngineerAllTickets(String engineerId);
}
