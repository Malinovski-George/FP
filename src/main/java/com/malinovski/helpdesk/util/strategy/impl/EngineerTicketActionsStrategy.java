package com.malinovski.helpdesk.util.strategy.impl;

import com.malinovski.helpdesk.model.State;
import com.malinovski.helpdesk.util.strategy.TicketActionsStrategy;

import java.util.LinkedList;
import java.util.List;

public class EngineerTicketActionsStrategy implements TicketActionsStrategy {

    @Override
    public List getActions(State state, boolean isUserTicketOwner, boolean hasFeedback) {

        LinkedList<String> actionsList = new LinkedList();

        if (state == State.APPROVED) {
            actionsList.add(State.IN_PROGRESS.getAction());
            actionsList.add(State.CANCELED.getAction());
        }

        if (!isUserTicketOwner && state == State.IN_PROGRESS) {
            actionsList.add(State.DONE.getAction());
        }
        return actionsList;
    }
}