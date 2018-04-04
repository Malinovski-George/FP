package com.malinovski.helpdesk.service.impl;


import com.malinovski.helpdesk.dao.*;
import com.malinovski.helpdesk.dto.TicketDto;
import com.malinovski.helpdesk.model.*;
import com.malinovski.helpdesk.service.TicketService;
import com.malinovski.helpdesk.service.mail.EmailService;
import com.malinovski.helpdesk.util.convertor.AttachmentConvertor;
import com.malinovski.helpdesk.util.convertor.CommentDtoConverter;
import com.malinovski.helpdesk.util.convertor.HistoryDtoConverter;
import com.malinovski.helpdesk.util.convertor.TicketDtoConverter;
import com.malinovski.helpdesk.util.strategy.TicketStateStrategy;
import com.malinovski.helpdesk.util.strategy.impl.EngineerTicketStateStrategy;
import com.malinovski.helpdesk.util.strategy.impl.ManagerTicketStateStrategy;
import com.malinovski.helpdesk.util.strategy.impl.OwnerTicketStateStrategy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private TicketStateStrategy ticketStateStrategy;

    private TicketDao ticketDao;
    private TicketDtoConverter ticketDtoConverter;
    private CategoryDao categoryDao;
    private CommentDao commentDao;
    private HistoryDao historyDao;
    private UserDao userDao;
    private CommentDtoConverter commentDtoConverter;
    private HistoryDtoConverter historyDtoConverter;
    private EmailService emailService;
    private AttachmentConvertor attachmentConvertor;
    private AttachmentDao attachmentDao;

    public TicketServiceImpl(TicketDao ticketDao,
                             TicketDtoConverter ticketDtoConverter,
                             CategoryDao categoryDao,
                             CommentDao commentDao,
                             CommentDtoConverter commentDtoConverter,
                             HistoryDtoConverter historyDtoConverter,
                             HistoryDao historyDao,
                             UserDao userDao,
                             EmailService emailService,
                             AttachmentConvertor attachmentConvertor,
                             AttachmentDao attachmentDao
    ) {
        this.ticketDao = ticketDao;
        this.ticketDtoConverter = ticketDtoConverter;
        this.categoryDao = categoryDao;
        this.commentDao = commentDao;
        this.commentDtoConverter = commentDtoConverter;
        this.historyDtoConverter = historyDtoConverter;
        this.historyDao = historyDao;
        this.userDao = userDao;
        this.emailService = emailService;
        this.attachmentConvertor = attachmentConvertor;
        this.attachmentDao = attachmentDao;
    }

    @Override
    @Transactional
    public void createTicket(TicketDto ticketDto) {
        if (ticketDto.getId() == -1) {
            User user = userDao.getUserById(ticketDto.getOwner());

            Ticket ticket = ticketDtoConverter.convertDtoToTicket(ticketDto);
            ticket.setOwner(user);
            ticket.setCategory(categoryDao.getCategoryById(ticketDto.getCategoryId()));
            ticketDao.createTicket(ticket);

            if (ticketDto.getComment() != null && !ticketDto.getComment().isEmpty()) {
                Comment comment = commentDtoConverter.getCommentFromTicket(ticketDto);
                comment.setTicket(ticket);
                comment.setUser(user);
                commentDao.createComment(comment);
            }

            History history = historyDtoConverter.getHistoryFromTicket(ticketDto);
            history.setAction(HistoryEvents.CREATE);
            history.setDate(new Timestamp(new Date().getTime()));
            history.setTicket(ticket);
            history.setUser(user);
            history.setDiscription(HistoryEvents.CREATE.toString());
            historyDao.createHistory(history);

            if (!ticketDto.getAttachments().isEmpty()) {
                for (MultipartFile mFile : ticketDto.getAttachments()) {
                    Attachment attachment = attachmentConvertor.convertMultipartFileToAttachment(mFile);
                    attachment.setTicket(ticket);
                    attachmentDao.save(attachment);
                }
            }

            if (ticket.getState() == State.NEW) {
                for (User manager : userDao.getAllManagers()) {
                    emailService.sendEmailStatusNew(ticket.getId(), manager);
                }
            }
        }
    }

    @Override
    public List<TicketDto> getAllTickets(Principal principal) {

        User user = userDao.getUserByEmail(principal.getName());
        Role userRole = user.getRole();
        String userId = String.valueOf(user.getId());
        List<Ticket> ticketList = new LinkedList<>();
        List<TicketDto> ticketDtoList = new LinkedList<>();

        if (userRole.equals(Role.ROLE_EMPLOYEE)) {
            ticketList = ticketDao.getEmployeeAllTickets(userId);
        } else if (userRole.equals(Role.ROLE_MANAGER)) {
            ticketList = ticketDao.getManagerAllTickets(userId);
        } else if (userRole.equals(Role.ROLE_ENGINEER)) {
            ticketList = ticketDao.getEngineerAllTickets(userId);
        }

        for (Ticket ticket : ticketList) {
            boolean isUserTicketOwner = user.getId() == ticket.getOwner().getId();
            ticketDtoList.add(ticketDtoConverter.convertTicketToDto(ticket, userRole, isUserTicketOwner));
        }
        return ticketDtoList;
    }

    @Override
    public List getMyTickets(Principal principal) {
        User user = userDao.getUserByEmail(principal.getName());
        Role userRole = user.getRole();
        String userId = String.valueOf(user.getId());
        List<Ticket> ticketList = new LinkedList<>();
        List<TicketDto> ticketDtoList = new LinkedList<>();

        if (userRole.equals(Role.ROLE_EMPLOYEE)) {
            ticketList = ticketDao.getEmployeeTickets(userId);
        } else if (userRole == Role.ROLE_MANAGER) {
            ticketList = ticketDao.getManagerTickets(userId);
        } else if (userRole == (Role.ROLE_ENGINEER)) {
            ticketList = ticketDao.getEngineerTickets(userId);
        }

        for (Ticket ticket : ticketList) {
            ticketDtoList.add(ticketDtoConverter.convertTicketToDto(ticket, userRole, true));
        }
        return ticketDtoList;
    }

    @Override
    public Ticket getTicketById(int id) {
        return ticketDao.getTicketById(id);
    }

    @Override
    public void updateTicket(TicketDto ticketDto) {
        Ticket currentTicket = getTicketById(ticketDto.getId());


        Ticket ticket = ticketDtoConverter.convertDtoToTicket(ticketDto);
        State oldState = currentTicket.getState();
        State newState = ticketDto.getState();

        if (!currentTicket.equals(ticket)) {
            if (currentTicket.getCategory().getId() != ticketDto.getCategoryId()) {
                Category category = categoryDao.getCategoryById(ticketDto.getCategoryId());
                currentTicket.setCategory(category);
            }

            currentTicket.setName(ticketDto.getName());
            currentTicket.setDescription(ticketDto.getDescription());
            currentTicket.setUrgency(ticketDto.getUrgency());
            currentTicket.setDesiredResolutionDate(ticketDto.getDesiredResolutionDate());

            History history = new History().newBuilder()
                    .setDate(new Timestamp(new Date().getTime()))
                    .setAction(HistoryEvents.EDIT)
                    .setTicket(currentTicket)
                    .setUser(currentTicket.getOwner())
                    .setDiscription(HistoryEvents.EDIT.toString())
                    .build();

            ticketDao.updateTicket(currentTicket);
            historyDao.createHistory(history);
        }

        if (ticketDto.getAttachments().size() > 0) {
            for (MultipartFile mFile : ticketDto.getAttachments()) {
                Attachment attachment = attachmentConvertor.convertMultipartFileToAttachment(mFile);
                attachment.setTicket(currentTicket);
                attachmentDao.save(attachment);
                String discription = String.format("File is attached: [%s]", attachment.getFileName());
                History history = new History().newBuilder()
                        .setDate(new Timestamp(new Date().getTime()))
                        .setAction(HistoryEvents.FILE_ADD)
                        .setTicket(currentTicket)
                        .setUser(currentTicket.getOwner())
                        .setDiscription(discription)
                        .build();
                historyDao.createHistory(history);
            }
        }

        if (!oldState.equals(newState)) {
            updateTicketState(ticketDto.getId(), String.valueOf(newState.ordinal()), currentTicket.getOwner());
        }

        if (ticketDto.getComment() != null) {
            Comment comment = new Comment(ticketDto.getComment(), ticketDto.getCreatedOn());
            comment.setTicket(currentTicket);
            comment.setUser(currentTicket.getOwner());
            commentDao.createComment(comment);
        }
    }

    @Override
    public TicketDto getTicketDto(int id) {
        Ticket ticket = ticketDao.getTicketById(id);
        return ticketDtoConverter.convertTicketToDto(ticket, Role.ROLE_EMPLOYEE, false);
    }

    @Override
    public void updateTicketState(int ticketId, String newStateId, User user) {
        Ticket ticket = ticketDao.getTicketById(ticketId);
        State oldState = ticket.getState();
        State newState = State.values()[Integer.parseInt(newStateId)];
        User ticketOwner = ticket.getOwner();
        User ticketAssignee = ticket.getAssignee();
        User ticketApprover = ticket.getApprover();
        boolean isNewStateCorrect = false;
        boolean isUserTicketOwner = ticketOwner.getId() == user.getId();

        ticketStateStrategy = getTicketStateStrategy(isUserTicketOwner, user.getRole());

        isNewStateCorrect = ticketStateStrategy.isNewStateCorrect(ticket, newState);

        if (isNewStateCorrect) {
            String discription = String.format("Ticket Status is changed from [%s] to [%s]", oldState.toString(), newState.toString());
            ticket.setState(newState);

            if (user.getRole() == Role.ROLE_MANAGER && ticketApprover == null) {
                ticket.setApprover(user);
            } else if (user.getRole() == Role.ROLE_ENGINEER && ticketAssignee == null) {
                ticket.setAssignee(user);
            }
            ticketDao.updateTicket(ticket);

            History history = new History().newBuilder()
                    .setDate(new Timestamp(new Date().getTime()))
                    .setAction(HistoryEvents.STATUS_CHANGE)
                    .setTicket(ticket)
                    .setUser(user)
                    .setDiscription(discription)
                    .build();
            historyDao.createHistory(history);
        }
    }

    @Override
    public boolean isEditable(int id, Principal principal) {
        boolean editable = false;
        Ticket ticket = ticketDao.getTicketById(id);
        State ticketState = ticket.getState();
        Role role = userDao.getUserByEmail(principal.getName()).getRole();

        if (((ticketState == State.DRAFT || ticketState == State.DECLIENED) && role == Role.ROLE_EMPLOYEE) ||
                ((ticketState == State.DRAFT || ticketState == State.DECLIENED) && role == Role.ROLE_MANAGER)) {
            editable = true;
        }
        return editable;
    }


    public TicketStateStrategy getTicketStateStrategy(boolean isOwner, Role role) {
        if (!isOwner) {
            if (role == Role.ROLE_MANAGER) {
                ticketStateStrategy = new ManagerTicketStateStrategy(emailService, userDao);
            }
            if (role == Role.ROLE_ENGINEER) {
                ticketStateStrategy = new EngineerTicketStateStrategy(emailService);
            }
        } else if ((role == Role.ROLE_MANAGER || role == Role.ROLE_EMPLOYEE) && isOwner) {
            ticketStateStrategy = new OwnerTicketStateStrategy(emailService, userDao);
        }
        return ticketStateStrategy;
    }
}
