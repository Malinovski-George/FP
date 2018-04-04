package com.malinovski.helpdesk.util.convertor;

import com.malinovski.helpdesk.dto.CommentDto;
import com.malinovski.helpdesk.dto.TicketDto;
import com.malinovski.helpdesk.model.Comment;

import java.util.List;

public interface CommentDtoConverter {

    CommentDto convertCommentToDto(Comment comment);

    Comment convertDtoToComment(CommentDto commentDto);

    List converCommentToDtoList(List<Comment> commentList);

    Comment getCommentFromTicket(TicketDto ticketDto);

}
