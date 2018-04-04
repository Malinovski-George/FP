package com.malinovski.helpdesk.util.strategy.impl;

import com.malinovski.helpdesk.model.State;
import com.malinovski.helpdesk.util.strategy.TicketActionsStrategy;

import java.util.LinkedList;
import java.util.List;

public class ManagerTicketActionsStrategy implements TicketActionsStrategy {

    @Override
    public List getActions(State state, boolean isUserTicketOwner, boolean hasFeedback) {

        LinkedList<String> actionsList = new LinkedList();

        if (isUserTicketOwner) {
            if (state == State.DRAFT || state == State.DECLIENED) {
                actionsList.add(State.NEW.getAction());
                actionsList.add(State.CANCELED.getAction());
            }
            if (state == State.DONE && !hasFeedback) {
                actionsList.add("Leave feedback");
            }
        }

        if (!isUserTicketOwner && state == State.NEW) {
                actionsList.add(State.APPROVED.getAction());
                actionsList.add(State.DECLIENED.getAction());
                actionsList.add(State.CANCELED.getAction());
        }
        return actionsList;
    }
}
