package com.malinovski.helpdesk.util.convertor.impl;

import com.malinovski.helpdesk.dto.AttachmentDto;
import com.malinovski.helpdesk.model.Attachment;
import com.malinovski.helpdesk.util.convertor.AttachmentConvertor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Component
public class AttachmentConvertorImpl implements AttachmentConvertor {
    @Override
    public Attachment convertMultipartFileToAttachment(MultipartFile mFile) {

        Attachment attachment = new Attachment();

        attachment.setFileName(mFile.getOriginalFilename());
        try {
            attachment.setContent(mFile.getBytes());
            attachment.setType(mFile.getContentType());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return attachment;
    }

    @Override
    public List<AttachmentDto> convertAttachmentToDtoList(List<Attachment> attachmentList) {

        List<AttachmentDto> attachmentDtoList = new LinkedList<>();
        AttachmentDto attachmentDto;

        for (Attachment attachment : attachmentList) {
            attachmentDto = new AttachmentDto();


            attachmentDto.setFileName(attachment.getFileName());
            attachmentDto.setId(attachment.getId());
            attachmentDtoList.add(attachmentDto);
        }
        return attachmentDtoList;
    }
}
