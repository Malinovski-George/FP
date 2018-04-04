package com.malinovski.helpdesk.util.strategy.impl;

import com.malinovski.helpdesk.dao.UserDao;
import com.malinovski.helpdesk.model.State;
import com.malinovski.helpdesk.model.Ticket;
import com.malinovski.helpdesk.model.User;
import com.malinovski.helpdesk.service.mail.EmailService;
import com.malinovski.helpdesk.util.strategy.TicketStateStrategy;


public class OwnerTicketStateStrategy implements TicketStateStrategy {
    private EmailService emailService;
    private UserDao userDao;

    public OwnerTicketStateStrategy(EmailService emailService, UserDao userDao) {
        this.emailService = emailService;
        this.userDao = userDao;
    }

    @Override
    public boolean isNewStateCorrect(Ticket ticket, State newState) {
        boolean isNewStateCorrect = false;
        State oldState = ticket.getState();

        if (oldState == State.DRAFT || oldState == State.DECLIENED) {
            if (newState == State.NEW) {
                isNewStateCorrect = true;
                for (User manager : userDao.getAllManagers()) {
                    emailService.sendEmailStatusNew(ticket.getId(), manager);
                }
            }
            if (newState == State.DRAFT || newState == State.CANCELED) {
                isNewStateCorrect = true;
            }
        }
        return isNewStateCorrect;
    }
}
