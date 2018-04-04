package com.malinovski.helpdesk.dao;

import com.malinovski.helpdesk.model.History;
import com.malinovski.helpdesk.model.Ticket;

import java.util.List;

public interface HistoryDao {

    void createHistory(History history);

    List getAllHistory();

    List getHistoryById(Ticket ticket);
}
