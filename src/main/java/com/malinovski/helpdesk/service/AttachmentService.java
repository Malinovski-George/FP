package com.malinovski.helpdesk.service;

import com.malinovski.helpdesk.dto.AttachmentDto;
import com.malinovski.helpdesk.model.Attachment;

import java.security.Principal;
import java.util.List;

public interface AttachmentService {

    void deleteAttachment(int fileId, Principal principal);

    List<AttachmentDto> getFileList(int ticketId);

    Attachment downloadFile(int fileId);

}
