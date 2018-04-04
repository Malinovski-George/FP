package com.malinovski.helpdesk.util.convertor.impl;

import com.malinovski.helpdesk.dao.CategoryDao;
import com.malinovski.helpdesk.dto.TicketDto;
import com.malinovski.helpdesk.model.*;
import com.malinovski.helpdesk.util.convertor.FeedbackDtoConverter;
import com.malinovski.helpdesk.util.convertor.TicketDtoConverter;
import com.malinovski.helpdesk.util.strategy.TicketActionsStrategy;
import com.malinovski.helpdesk.util.strategy.impl.EmployeeTicketActionsStrategy;
import com.malinovski.helpdesk.util.strategy.impl.EngineerTicketActionsStrategy;
import com.malinovski.helpdesk.util.strategy.impl.ManagerTicketActionsStrategy;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
public class TicketDtoConverterImpl implements TicketDtoConverter {
    private TicketActionsStrategy ticketActionsStrategy;
    private CategoryDao categoryDao;
    private FeedbackDtoConverter feedbackDtoConverter;

    public TicketDtoConverterImpl(CategoryDao categoryDao, FeedbackDtoConverter feedbackDtoConverter) {
        this.categoryDao = categoryDao;
        this.feedbackDtoConverter = feedbackDtoConverter;
    }

    @Override
    public Ticket convertDtoToTicket(TicketDto ticketDto) {
        Ticket ticket = new Ticket().newBuilder()
                .setState(ticketDto.getState())
                .setName(ticketDto.getName())
                .setDesiredResolutionDate(ticketDto.getDesiredResolutionDate())
                .setUrgency(ticketDto.getUrgency())
                .setCreatedOn(ticketDto.getCreatedOn())
                .build();

        ticket.setCategory(categoryDao.getCategoryById(ticketDto.getCategoryId()));
        if (ticketDto.getDescription() != null && ticketDto.getDescription().length() > 1) {
            ticket.setDescription(ticketDto.getDescription());
        }
        return ticket;
    }

    @Override
    public TicketDto convertTicketToDto(Ticket ticket, Role requestOwnerRole, boolean isUserTicketOwner) {
        Feedback feedback = ticket.getFeedback();
        Category category = ticket.getCategory();
        State state = ticket.getState();
        Urgency urgency = ticket.getUrgency();

        TicketDto ticketDto =
                TicketDto.newBuilder()
                        .setId(ticket.getId())
                        .setName(ticket.getName())
                        .setUrgencyId(urgency.ordinal())
                        .setUrgencyName(urgency.toString())
                        .setState_id(state)
                        .setStateName(state.toString())
                        .setCategory_id(category.getId())
                        .setCategoryName(category.getName())
                        .setCreated_on(ticket.getCreatedOn())
                        .setOwnerName(ticket.getOwner().toString())
                        .build();

        if (ticket.getApprover() != null) {
            ticketDto.setApproverName(ticket.getApprover().toString());
        }
        if (ticket.getAssignee() != null) {
            ticketDto.setAssigneeName(ticket.getAssignee().toString());
        }
        if (ticket.getDesiredResolutionDate() != null) {
            ticketDto.setDesiredResolutionDate(ticket.getDesiredResolutionDate());
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM dd, yyyy");
            String stringDate = dateFormat.format(ticket.getDesiredResolutionDate());
            ticketDto.setDesiredResolutionDateString(stringDate);
        }
        if (ticket.getDescription() != null) {
            ticketDto.setDescription(ticket.getDescription());
        }

        if (feedback != null) {
            ticketDto.setFeedbackDto(feedbackDtoConverter.convertFeedbackToDto(feedback));
        }
        ticketActionsStrategy = chooseActionStrategy(requestOwnerRole);
        boolean hasFeedback = ticket.getFeedback() != null;
        ticketDto.setActions(ticketActionsStrategy.getActions(ticket.getState(), isUserTicketOwner, hasFeedback));
        return ticketDto;
    }

    //TODO map
    private TicketActionsStrategy chooseActionStrategy(Role role) {
        if (role == Role.ROLE_EMPLOYEE) {
            return new EmployeeTicketActionsStrategy();
        } else if (role == Role.ROLE_MANAGER) {
            return new ManagerTicketActionsStrategy();
        } else {
            return new EngineerTicketActionsStrategy();
        }
    }
}
