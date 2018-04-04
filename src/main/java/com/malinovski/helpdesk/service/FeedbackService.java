package com.malinovski.helpdesk.service;

import com.malinovski.helpdesk.dto.FeedbackDto;

import java.security.Principal;

public interface FeedbackService {

    void createFeedback(FeedbackDto feedbackDto, Principal principal);

    boolean isReadyToFeedback(int ticketId, Principal principal);
}
