package com.malinovski.helpdesk.controller;

import com.malinovski.helpdesk.dto.TicketDto;
import com.malinovski.helpdesk.model.User;
import com.malinovski.helpdesk.service.TicketService;
import com.malinovski.helpdesk.service.UserService;
import com.malinovski.helpdesk.util.helper.RequestHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@RestController
public class TicketController {

    private UserService userService;
    private TicketService ticketService;
    private RequestHelper requestHelper;

    public TicketController(UserService userService, TicketService ticketService, RequestHelper requestHelper) {
        this.userService = userService;
        this.ticketService = ticketService;
        this.requestHelper = requestHelper;
    }

    @RequestMapping(value = "/tickets/list", method = RequestMethod.GET)
    public ResponseEntity<List<TicketDto>> getAllTickets(Principal principal) {
        List<TicketDto> ticketDtoList = ticketService.getAllTickets(principal);
        return new ResponseEntity<>(ticketDtoList, HttpStatus.OK);
    }

    @RequestMapping(value = "/user-tickets", method = RequestMethod.GET)
    public ResponseEntity<List<TicketDto>> getMyTickets(Principal principal) {
        List<TicketDto> ticketDtoList = ticketService.getMyTickets(principal);
        return new ResponseEntity<>(ticketDtoList, HttpStatus.OK);
    }

    @RequestMapping(value = {"/tickets"}, method = RequestMethod.GET)
    public ModelAndView createTicket(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ticket-create");
        return modelAndView;
    }


    @RequestMapping(value = "/tickets", method = RequestMethod.POST)
    public void createTicket(HttpServletRequest request, Principal principal) {
        TicketDto ticketDto = requestHelper.getTicketDtoFromRequest(request);
        ticketDto.setOwner(userService.getUserByEmail(principal.getName()).getId());
        ticketService.createTicket(ticketDto);
    }

    @RequestMapping(value = "/tickets/{id}/dto", method = RequestMethod.GET)
    public ResponseEntity<TicketDto> getTicket(@PathVariable("id") int id) {
        TicketDto ticketDto = ticketService.getTicketDto(id);
        return new ResponseEntity(ticketDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/tickets/{id}/edit", method = RequestMethod.GET)
    public ModelAndView editTicket(@PathVariable("id") int id, Principal principal) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ticket-edit");
        return modelAndView;
    }

    @RequestMapping(value = "/tickets/{id}/edit", method = RequestMethod.POST)
    public void updateTicket(@PathVariable("id") int id,
                             HttpServletRequest request,
                             @RequestParam(value = "state", required = false) String newState,
                             Principal principal) {

        User user = userService.getUserByEmail(principal.getName());
        if (newState == null) {
            TicketDto ticketDto = requestHelper.getTicketDtoFromRequest(request);
            ticketDto.setOwner(userService.getUserByEmail(principal.getName()).getId());
            ticketDto.setId(id);
            ticketService.updateTicket(ticketDto);
        } else {
            ticketService.updateTicketState(id, newState, user);
        }
    }
}
