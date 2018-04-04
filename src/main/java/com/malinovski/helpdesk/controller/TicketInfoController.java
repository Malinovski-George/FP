package com.malinovski.helpdesk.controller;

import com.malinovski.helpdesk.dto.AttachmentDto;
import com.malinovski.helpdesk.dto.FeedbackDto;
import com.malinovski.helpdesk.model.Attachment;
import com.malinovski.helpdesk.service.AttachmentService;
import com.malinovski.helpdesk.service.FeedbackService;
import com.malinovski.helpdesk.service.TicketService;
import com.malinovski.helpdesk.util.FormExtender;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
public class TicketInfoController {

    private TicketService ticketService;
    private FormExtender formExtender;
    private FeedbackService feedbackService;
    private AttachmentService attachmentService;

    public TicketInfoController(TicketService ticketService, FormExtender formExtender, FeedbackService feedbackService, AttachmentService attachmentService) {
        this.ticketService = ticketService;
        this.formExtender = formExtender;
        this.feedbackService = feedbackService;
        this.attachmentService = attachmentService;
    }

    @RequestMapping(value = "/tickets/{id}/overview", method = RequestMethod.GET)
    public ModelAndView getTicketOverview(@PathVariable("id") int id, Principal principal) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("ticketDto", ticketService.getTicketDto(id));

        List<AttachmentDto> list = attachmentService.getFileList(id);

        for (AttachmentDto attachmentDto : list){

            System.out.println(attachmentDto.getFileName());
        }

        modelAndView.addObject("attachmentDto", list);
        modelAndView.addObject("isEditable", ticketService.isEditable(id, principal));
        modelAndView.setViewName("ticket-overview");
        return modelAndView;
    }

    @RequestMapping(value = {"/tickets/{id}/init-form-data", "/init-form-data"}, method = RequestMethod.GET)
    public ResponseEntity fillForm() {
        return new ResponseEntity(formExtender.getFormFieldDto(), HttpStatus.OK);
    }

    @RequestMapping(value = "/tickets/{id}/feedback", method = RequestMethod.GET)
    public ModelAndView feedback(@PathVariable("id") int id, Principal principal) {

        ModelAndView modelAndView = new ModelAndView();
        if (feedbackService.isReadyToFeedback(id, principal)) {
            modelAndView.setViewName("feedback");
            return modelAndView;
        } else {
            modelAndView.setViewName("tickets");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/tickets/{id}/feedback", method = RequestMethod.POST)
    public void createFeedback(@PathVariable("id") int id,
                               @RequestBody FeedbackDto feedbackDto,
                               Principal principal) {

        feedbackDto.setTicketId(id);
        feedbackService.createFeedback(feedbackDto, principal);
    }

    @RequestMapping(value = "/tickets/{id}/files", method = RequestMethod.GET)
    public ResponseEntity getListFiles(@PathVariable("id") int ticketId) {
        return new ResponseEntity(attachmentService.getFileList(ticketId), HttpStatus.OK);
    }

    @RequestMapping(value = "/tickets/{id}/files/{fileId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteFile(Principal principal,
                                     @PathVariable("fileId") int fileId) {

        attachmentService.deleteAttachment(fileId, principal);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/tickets/{id}/files/{fileId}", method = RequestMethod.GET)
    public void downloadFile(@PathVariable("id") int ticketId,
                             @PathVariable("fileId") int fileId,
                             HttpServletResponse response) throws IOException {

        Attachment attachment = attachmentService.downloadFile(fileId);
        response.setContentType(attachment.getType());
        response.setContentLength(attachment.getContent().length);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.getFileName() + "\"");
        FileCopyUtils.copy(attachment.getContent(), response.getOutputStream());
    }
}