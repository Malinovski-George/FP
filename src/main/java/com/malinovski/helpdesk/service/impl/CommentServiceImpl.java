package com.malinovski.helpdesk.service.impl;

import com.malinovski.helpdesk.dao.CommentDao;
import com.malinovski.helpdesk.dao.TicketDao;
import com.malinovski.helpdesk.dao.UserDao;
import com.malinovski.helpdesk.dto.CommentDto;
import com.malinovski.helpdesk.model.Comment;
import com.malinovski.helpdesk.model.Ticket;
import com.malinovski.helpdesk.model.User;
import com.malinovski.helpdesk.service.CommentService;
import com.malinovski.helpdesk.util.convertor.CommentDtoConverter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentDao commentDao;
    private TicketDao ticketDao;
    private UserDao userDao;
    private CommentDtoConverter commentDtoConverter;

    public CommentServiceImpl(CommentDao commentDao, TicketDao ticketDao, UserDao userDao, CommentDtoConverter commentDtoConverter) {
        this.commentDao = commentDao;
        this.ticketDao = ticketDao;
        this.userDao = userDao;
        this.commentDtoConverter = commentDtoConverter;
    }

    @Override
    public void createComment(Comment comment) {
        commentDao.createComment(comment);
    }

    @Override
    public List getAllComments() {
        return commentDao.getAllComments();
    }

    @Override
    public List getCommentsDtoByTicketId(int id) {

        Ticket ticket = ticketDao.getTicketById(id);
        List commts = commentDao.getCommentsByTicketId(ticket);
        return commentDtoConverter.converCommentToDtoList(commts);
    }

    @Override
    public void createComment(int id, CommentDto commentDto) {

        Ticket ticket = ticketDao.getTicketById(id);
        User commentUser = userDao.getUserById(commentDto.getOwner());
        Comment comment = commentDtoConverter.convertDtoToComment(commentDto);
        comment.setTicket(ticket);
        comment.setUser(commentUser);
        createComment(comment);
    }
}
