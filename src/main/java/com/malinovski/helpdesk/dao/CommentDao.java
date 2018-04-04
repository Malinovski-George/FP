package com.malinovski.helpdesk.dao;

import com.malinovski.helpdesk.model.Comment;
import com.malinovski.helpdesk.model.Ticket;

import java.util.List;

public interface CommentDao {

    void createComment(Comment comment);

    List getAllComments();

    List getCommentsByTicketId(Ticket ticket);
}
