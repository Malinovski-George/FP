package com.malinovski.helpdesk.util.convertor.impl;

import com.malinovski.helpdesk.dto.CommentDto;
import com.malinovski.helpdesk.dto.TicketDto;
import com.malinovski.helpdesk.model.Comment;
import com.malinovski.helpdesk.util.convertor.CommentDtoConverter;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

@Component
public class CommentDtoConverterImpl implements CommentDtoConverter {

    @Override
    public CommentDto convertCommentToDto(Comment comment) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM dd, yyyy HH:mm:ss");
        String stringDate = dateFormat.format(comment.getDate());
        String ownerName = comment.getUser().getFirstName() + " " + comment.getUser().getLastName();
        return new CommentDto(ownerName, comment.getText(), stringDate, null);
    }

    @Override
    public Comment convertDtoToComment(CommentDto commentDto) {

        return new Comment(commentDto.getText(), commentDto.getDate());
    }

    @Override
    public List converCommentToDtoList(List<Comment> commentList) {

        List<CommentDto> commentDtoList = new LinkedList<>();
        for (Comment comment : commentList) {
            commentDtoList.add(convertCommentToDto(comment));
        }
        return commentDtoList;
    }

    @Override
    public Comment getCommentFromTicket(TicketDto ticketDto) {
        return Comment.newBuilder()
                .setDate(ticketDto.getCreatedOn())
                .setText(ticketDto.getComment())
                .build();

    }
}
