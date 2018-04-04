package com.malinovski.helpdesk.util.convertor.impl;

import com.malinovski.helpdesk.dto.HistoryDto;
import com.malinovski.helpdesk.dto.TicketDto;
import com.malinovski.helpdesk.model.History;
import com.malinovski.helpdesk.util.convertor.HistoryDtoConverter;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

@Component
public class HistoryDtoConverterImpl implements HistoryDtoConverter {

    @Override
    public HistoryDto convertHistoryToDto(History history) {
        HistoryDto historyDto = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM dd, yyyy HH:mm:ss");

        try {
            String stringDate = dateFormat.format(history.getDate());
            String ownerName = history.getUser().getFirstName() + " " + history.getUser().getLastName();
            String description = history.getDiscription();
            historyDto = new HistoryDto(stringDate, ownerName, history.getAction().toString(), description);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return historyDto;
    }

    @Override
    public List convertHistoryToDtoList(List<History> historyList) {

        List<HistoryDto> historyDtoList = new LinkedList<>();
        for (History history : historyList) {
            historyDtoList.add(convertHistoryToDto(history));
        }
        return historyDtoList;
    }

    @Override
    public History getHistoryFromTicket(TicketDto ticketDto) {
        return History.newBuilder()
                .setDate(ticketDto.getCreatedOn())
                .build();
    }
}
