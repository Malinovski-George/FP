package com.malinovski.helpdesk.util.convertor;

import com.malinovski.helpdesk.dto.HistoryDto;
import com.malinovski.helpdesk.dto.TicketDto;
import com.malinovski.helpdesk.model.History;

import java.util.List;

public interface HistoryDtoConverter {

    HistoryDto convertHistoryToDto(History history);

    List convertHistoryToDtoList(List<History> historyList);

    History getHistoryFromTicket(TicketDto ticketDto);
}
