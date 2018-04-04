package com.malinovski.helpdesk.util.convertor;

import com.malinovski.helpdesk.dto.TicketDto;
import com.malinovski.helpdesk.model.Role;
import com.malinovski.helpdesk.model.Ticket;

import java.util.List;

public interface TicketDtoConverter {

    Ticket convertDtoToTicket(TicketDto ticketDto);

    TicketDto convertTicketToDto(Ticket ticket, Role requestOwnerRole,  boolean isUserTicketOwner);

 //  List<TicketDto> convertTicketToDtoList(List<Ticket> ticket, Role requestOwnerRole, boolean isUserTicketOwner);


}
