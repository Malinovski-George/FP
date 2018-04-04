package com.malinovski.helpdesk.service;

import com.malinovski.helpdesk.dto.TicketDto;
import com.malinovski.helpdesk.model.Ticket;
import com.malinovski.helpdesk.model.User;

import java.security.Principal;
import java.util.List;

public interface TicketService {

    void createTicket(TicketDto ticketDto);

    List getAllTickets(Principal principal);

    List getMyTickets(Principal principal);

    Ticket getTicketById(int id);

    void updateTicket(TicketDto ticketDto);

    TicketDto getTicketDto(int id);

    void updateTicketState(int ticketId, String newState, User user);

    boolean isEditable(int id, Principal principal);
}
