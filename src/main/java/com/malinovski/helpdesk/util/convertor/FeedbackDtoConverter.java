package com.malinovski.helpdesk.util.convertor;

import com.malinovski.helpdesk.dto.FeedbackDto;
import com.malinovski.helpdesk.model.Feedback;

public interface FeedbackDtoConverter {

    Feedback convertDtoToFeedback(FeedbackDto feedbackDto);

    FeedbackDto convertFeedbackToDto(Feedback feedback);

}
