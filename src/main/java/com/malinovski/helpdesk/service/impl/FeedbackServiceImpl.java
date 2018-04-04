package com.malinovski.helpdesk.service.impl;

import com.malinovski.helpdesk.dao.FeedbackDao;
import com.malinovski.helpdesk.dao.TicketDao;
import com.malinovski.helpdesk.dao.UserDao;
import com.malinovski.helpdesk.dto.FeedbackDto;
import com.malinovski.helpdesk.model.Feedback;
import com.malinovski.helpdesk.model.State;
import com.malinovski.helpdesk.model.Ticket;
import com.malinovski.helpdesk.model.User;
import com.malinovski.helpdesk.service.FeedbackService;
import com.malinovski.helpdesk.service.mail.EmailService;
import com.malinovski.helpdesk.util.convertor.FeedbackDtoConverter;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private FeedbackDao feedbackDao;
    private TicketDao ticketDao;
    private UserDao userDao;
    private FeedbackDtoConverter feedbackDtoConverter;
    private EmailService emailService;

    public FeedbackServiceImpl(FeedbackDao feedbackDao, TicketDao ticketDao, UserDao userDao, FeedbackDtoConverter feedbackDtoConverter, EmailService emailService) {
        this.feedbackDao = feedbackDao;
        this.ticketDao = ticketDao;
        this.userDao = userDao;
        this.feedbackDtoConverter = feedbackDtoConverter;
        this.emailService = emailService;
    }

    @Override
    public void createFeedback(FeedbackDto feedbackDto, Principal principal) {
        int ticketId = feedbackDto.getTicketId();
        Ticket ticket = ticketDao.getTicketById(ticketId);
        User user = userDao.getUserByEmail(principal.getName());
        int ownerId = ticket.getOwner().getId();
        int userId = user.getId();
        int feedbackRate = feedbackDto.getRate();
        int minFeedbackRate = 1;
        int maxFeedbackRate = 5;

        if (ownerId == userId && feedbackRate <= maxFeedbackRate && feedbackRate >= minFeedbackRate) {
            Feedback feedback = feedbackDtoConverter.convertDtoToFeedback(feedbackDto);

            if (feedbackDto.getText().length()>1){
                feedback.setText(feedbackDto.getText());
            }

            feedback.setTicket(ticket);
            feedback.setUser(user);
            feedbackDao.createFeedback(feedback);

            User assignee = ticket.getAssignee();
            emailService.sendEmailStatusFeedback(ticketId, assignee);
        }
    }

    @Override
    public boolean isReadyToFeedback(int ticketId, Principal principal) {
        Ticket ticket = ticketDao.getTicketById(ticketId);
        User currentUser = userDao.getUserByEmail(principal.getName());

        return ticket.getOwner().getId() == currentUser.getId() && ticket.getState() == State.DONE;
    }
}
