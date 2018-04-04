package com.malinovski.helpdesk.service;

import com.malinovski.helpdesk.dto.CommentDto;
import com.malinovski.helpdesk.model.Comment;

import java.util.List;

public interface CommentService {

    void createComment(Comment comment);

    List getAllComments();

    List getCommentsDtoByTicketId(int id);

    void createComment(int id, CommentDto commentDto);
}
