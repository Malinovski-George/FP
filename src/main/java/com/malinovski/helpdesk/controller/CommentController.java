package com.malinovski.helpdesk.controller;


import com.malinovski.helpdesk.dto.CommentDto;

import com.malinovski.helpdesk.service.CommentService;
import com.malinovski.helpdesk.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class CommentController {

    private UserService userService;
    private CommentService commentService;

    public CommentController(UserService userService, CommentService commentService) {
        this.userService = userService;
        this.commentService = commentService;
    }

    @RequestMapping(value = "/tickets/{id}/comments", method = RequestMethod.GET)
    public ResponseEntity<List<CommentDto>> getComments(@PathVariable("id") int id) {
        List commentsList = commentService.getCommentsDtoByTicketId(id);
        return new ResponseEntity<List<CommentDto>>(commentsList, HttpStatus.OK);
    }

       @RequestMapping(value = "/tickets/{id}/comments", method = RequestMethod.POST)
    public void createComment(@PathVariable("id") int id, @RequestBody CommentDto commentDto,
                              Principal principal
    ) {
        commentDto.setOwner(userService.getUserByEmail(principal.getName()).getId());
        commentService.createComment(id, commentDto);
    }
}
