package com.malinovski.helpdesk.dao.impl;

import com.malinovski.helpdesk.dao.AttachmentDao;
import com.malinovski.helpdesk.model.Attachment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
@Transactional
public class AttachmentDaoImpl implements AttachmentDao {


    private SessionFactory sessionFactory;

    public AttachmentDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Attachment attachment) {
        sessionFactory.getCurrentSession().save(attachment);
    }

    @Override
    public Attachment getAttachment(int attachmentId) {
        Session session = sessionFactory.getCurrentSession();
        return (Attachment) session.get(Attachment.class, attachmentId);
    }

    @Override
    public void deleteAttachment(Attachment attachment) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(attachment);
    }
}
