package com.malinovski.helpdesk.util.convertor.impl;

import com.malinovski.helpdesk.dto.FeedbackDto;
import com.malinovski.helpdesk.model.Feedback;
import com.malinovski.helpdesk.util.convertor.FeedbackDtoConverter;
import org.springframework.stereotype.Component;

@Component
public class FeedbackDtoConverterImpl implements FeedbackDtoConverter {


    @Override
    public Feedback convertDtoToFeedback(FeedbackDto feedbackDto) {
        return Feedback.newBuilder()
                .setDate(feedbackDto.getDate())
                .setRate(feedbackDto.getRate())
                .build();
    }

    @Override
    public FeedbackDto convertFeedbackToDto(Feedback feedback) {

        FeedbackDto feedbackDto = new FeedbackDto();
        feedbackDto.setRate(feedback.getRate());

        if (feedback.getText()!= null) {
            feedbackDto.setText(feedback.getText());
        }

        feedbackDto.setDate(feedback.getDate());
        return feedbackDto;
    }
}
