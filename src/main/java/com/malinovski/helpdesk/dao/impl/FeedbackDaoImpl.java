package com.malinovski.helpdesk.dao.impl;

import com.malinovski.helpdesk.dao.FeedbackDao;
import com.malinovski.helpdesk.model.Feedback;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class FeedbackDaoImpl implements FeedbackDao {
    private SessionFactory sessionFactory;

    public FeedbackDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void createFeedback(Feedback feedback) {
        Session session = sessionFactory.getCurrentSession();
        session.save(feedback);

    }
}
