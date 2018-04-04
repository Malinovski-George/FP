package com.malinovski.helpdesk.service.impl;

import com.malinovski.helpdesk.dao.AttachmentDao;
import com.malinovski.helpdesk.dao.HistoryDao;
import com.malinovski.helpdesk.dao.TicketDao;
import com.malinovski.helpdesk.dto.AttachmentDto;
import com.malinovski.helpdesk.model.*;
import com.malinovski.helpdesk.service.AttachmentService;
import com.malinovski.helpdesk.util.convertor.AttachmentConvertor;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Component
public class AttachmentServiceImpl implements AttachmentService {

    private TicketDao ticketDao;
    private AttachmentDao attachmentDao;
    private AttachmentConvertor attachmentConvertor;
    private HistoryDao historyDao;

    public AttachmentServiceImpl(TicketDao ticketDao, AttachmentDao attachmentDao, AttachmentConvertor attachmentConvertor, HistoryDao historyDao) {
        this.ticketDao = ticketDao;
        this.attachmentDao = attachmentDao;
        this.attachmentConvertor = attachmentConvertor;
        this.historyDao = historyDao;
    }

    @Override
    public void deleteAttachment(int fileId, Principal principal) {
        Attachment attachment = attachmentDao.getAttachment(fileId);
        Ticket ticket = attachment.getTicket();
        User user = ticket.getOwner();
        String discription = String.format("File is removed: [%s]", attachment.getFileName());
        History history = History.newBuilder()
                .setTicket(ticket)
                .setAction(HistoryEvents.FILE_REMOVE)
                .setDiscription(discription)
                .setDate(new Timestamp(new Date().getTime()))
                .setUser(user)
                .build();

        historyDao.createHistory(history);
        attachmentDao.deleteAttachment(attachment);
    }

    @Override
    public List<AttachmentDto> getFileList(int ticketId) {
        return attachmentConvertor.convertAttachmentToDtoList(ticketDao.getTicketById(ticketId).getTicketAttachments());
    }

    @Override
    public Attachment downloadFile(int fileId) {
        return attachmentDao.getAttachment(fileId);
    }
}
