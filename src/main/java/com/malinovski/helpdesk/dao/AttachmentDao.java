package com.malinovski.helpdesk.dao;

import com.malinovski.helpdesk.model.Attachment;

public interface AttachmentDao {

    void save(Attachment attachment);

    Attachment getAttachment(int attachmentId);

    void deleteAttachment(Attachment fileId);
}
