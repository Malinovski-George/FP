package com.malinovski.helpdesk.util.strategy;

import com.malinovski.helpdesk.model.State;
import com.malinovski.helpdesk.model.Ticket;

public interface TicketStateStrategy {

    boolean isNewStateCorrect(Ticket ticket, State newState);

}
