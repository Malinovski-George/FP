package com.malinovski.helpdesk.util.strategy.impl;

import com.malinovski.helpdesk.model.State;
import com.malinovski.helpdesk.model.Ticket;
import com.malinovski.helpdesk.service.mail.EmailService;
import com.malinovski.helpdesk.util.strategy.TicketStateStrategy;

public class EngineerTicketStateStrategy  implements TicketStateStrategy {

    private EmailService emailService;

    public EngineerTicketStateStrategy(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public boolean isNewStateCorrect(Ticket ticket, State newState) {

        boolean isNewStateCorrect = false;
        State oldState = ticket.getState();

        if (oldState == State.APPROVED) {
            if (newState == State.IN_PROGRESS) {
                isNewStateCorrect = true;
            }
            if (newState == State.CANCELED) {
                isNewStateCorrect = true;
                emailService.sendEmailStatusCancelledEngineer(ticket.getId(), ticket.getOwner());
                emailService.sendEmailStatusCancelledEngineer(ticket.getId(), ticket.getApprover());
            }
        }

        if (oldState == State.IN_PROGRESS && newState == State.DONE) {
            isNewStateCorrect = true;
            emailService.sendEmailStatusDone(ticket.getId(), ticket.getOwner());
        }
        return isNewStateCorrect;
    }
}
