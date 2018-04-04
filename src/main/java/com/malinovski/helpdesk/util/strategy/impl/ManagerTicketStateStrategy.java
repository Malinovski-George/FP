package com.malinovski.helpdesk.util.strategy.impl;

import com.malinovski.helpdesk.dao.UserDao;
import com.malinovski.helpdesk.model.State;
import com.malinovski.helpdesk.model.Ticket;
import com.malinovski.helpdesk.model.User;
import com.malinovski.helpdesk.service.mail.EmailService;
import com.malinovski.helpdesk.util.strategy.TicketStateStrategy;

public class ManagerTicketStateStrategy implements TicketStateStrategy {
    private EmailService emailService;
    private UserDao userDao;

    public ManagerTicketStateStrategy(EmailService emailService, UserDao userDao) {
        this.emailService = emailService;
        this.userDao = userDao;
    }

    @Override
    public boolean isNewStateCorrect(Ticket ticket, State newState) {

        boolean isNewStateCorrect = false;
        State oldState = ticket.getState();

        if (oldState == State.NEW) {
            if (newState == State.APPROVED) {
                isNewStateCorrect = true;
                emailService.sendEmailStatusApprove(ticket.getId(), ticket.getOwner());
                for (User engineer : userDao.getAllEngineers()) {
                    emailService.sendEmailStatusApprove(ticket.getId(), engineer);
                }
            }
            if (newState == State.DECLIENED) {
                isNewStateCorrect = true;
                emailService.sendEmailStatusDecline(ticket.getId(), ticket.getOwner());
            }

            if (newState == State.CANCELED) {
                isNewStateCorrect = true;
                emailService.sendEmailStatusCancelledManager(ticket.getId(), ticket.getOwner());
            }
        }
        return isNewStateCorrect;
    }
}