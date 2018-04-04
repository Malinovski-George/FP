package com.malinovski.helpdesk.controller;

import com.malinovski.helpdesk.dto.HistoryDto;
import com.malinovski.helpdesk.service.HistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HistoryController {

    private HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @RequestMapping(value = "/tickets/{id}/history", method = RequestMethod.GET)
    public ResponseEntity<List<HistoryDto>> getAllHistory(@PathVariable("id") int id) {
        List historyDtoList = historyService.getAllHistory(id);
        return new ResponseEntity<List<HistoryDto>>(historyDtoList, HttpStatus.OK);
    }
}
