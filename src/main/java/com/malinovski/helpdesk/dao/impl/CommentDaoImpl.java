package com.malinovski.helpdesk.dao.impl;

import com.malinovski.helpdesk.dao.CommentDao;
import com.malinovski.helpdesk.model.Comment;
import com.malinovski.helpdesk.model.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CommentDaoImpl implements CommentDao {

    private SessionFactory sessionFactory;

    public CommentDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void createComment(Comment comment) {

        Session session = sessionFactory.getCurrentSession();
        session.persist(comment);
    }

    @Override
    @Transactional
    public List getAllComments() {

        List commnets = null;
        Session session = sessionFactory.getCurrentSession();
        commnets = session.createCriteria(Comment.class).addOrder(Order.desc("date")).list();
        return commnets;

    }

    @Override
    @Transactional
    public List getCommentsByTicketId(Ticket ticket) {

        List commnets = null;
        Session session = sessionFactory.getCurrentSession();
        commnets = session.createCriteria(Comment.class)
                .add(Restrictions.eq("ticket", ticket))
                .addOrder(Order.desc("date"))
                .list();
        return commnets;


    }
}
