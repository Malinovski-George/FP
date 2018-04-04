package com.malinovski.helpdesk.util.convertor;

import com.malinovski.helpdesk.dto.AttachmentDto;
import com.malinovski.helpdesk.model.Attachment;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AttachmentConvertor {

    Attachment convertMultipartFileToAttachment(MultipartFile mFile);

    List<AttachmentDto> convertAttachmentToDtoList(List<Attachment> commentList);
}
