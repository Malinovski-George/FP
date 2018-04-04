package com.malinovski.helpdesk.util.strategy;

import com.malinovski.helpdesk.model.State;

import java.util.List;

public interface TicketActionsStrategy {

    List getActions(State state, boolean isUserTicketOwner, boolean hasFeedback);

}
