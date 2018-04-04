package com.malinovski.helpdesk.service.impl;

import com.malinovski.helpdesk.dao.HistoryDao;
import com.malinovski.helpdesk.dao.TicketDao;
import com.malinovski.helpdesk.model.History;
import com.malinovski.helpdesk.service.HistoryService;
import com.malinovski.helpdesk.util.convertor.HistoryDtoConverter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    private TicketDao ticketDao;
    private HistoryDao historyDao;
    private HistoryDtoConverter historyDtoConverter;

    public HistoryServiceImpl(TicketDao ticketDao, HistoryDao historyDao, HistoryDtoConverter historyDtoConverter) {
        this.ticketDao = ticketDao;
        this.historyDao = historyDao;
        this.historyDtoConverter = historyDtoConverter;
    }

    @Override
    public void createHistory(History history) {
        historyDao.createHistory(history);
    }

    @Override
    public List<History> getAllHistory(int ticketId) {
        List historyList = historyDao.getHistoryById(ticketDao.getTicketById(ticketId));
        return historyDtoConverter.convertHistoryToDtoList(historyList);
    }
}
