package com.malinovski.helpdesk.util.helper;

import com.malinovski.helpdesk.dto.TicketDto;

import javax.servlet.http.HttpServletRequest;

public interface RequestHelper {

  TicketDto getTicketDtoFromRequest(HttpServletRequest request);
}
