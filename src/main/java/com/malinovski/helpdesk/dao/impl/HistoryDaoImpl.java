package com.malinovski.helpdesk.dao.impl;

import com.malinovski.helpdesk.dao.HistoryDao;
import com.malinovski.helpdesk.model.History;
import com.malinovski.helpdesk.model.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class HistoryDaoImpl implements HistoryDao {

    private SessionFactory sessionFactory;

    public HistoryDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void createHistory(History history) {

        Session session = sessionFactory.getCurrentSession();
        session.persist(history);
    }

    @Override
    public List getAllHistory() {

        List history = null;
        Session session = sessionFactory.getCurrentSession();
        history = session.createCriteria(History.class).addOrder(Order.asc("date")).list();
        return history;
    }

    @Override
    public List getHistoryById(Ticket ticket) {


        List history = null;
        Session session = sessionFactory.getCurrentSession();
        history = session.createCriteria(History.class)
                .add(Restrictions.eq("ticket", ticket))
                .addOrder(Order.desc("date"))
                .list();

        return history;

    }

}
