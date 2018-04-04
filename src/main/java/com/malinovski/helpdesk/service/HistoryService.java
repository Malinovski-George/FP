package com.malinovski.helpdesk.service;

import com.malinovski.helpdesk.model.History;

import java.util.List;

public interface HistoryService {

    void createHistory(History history);

    List<History> getAllHistory(int id);
}
