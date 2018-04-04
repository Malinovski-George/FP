package com.malinovski.helpdesk.dao.impl;

import com.malinovski.helpdesk.dao.TicketDao;
import com.malinovski.helpdesk.model.State;
import com.malinovski.helpdesk.model.Ticket;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TicketDaoImpl implements TicketDao {

    private SessionFactory sessionFactory;

    public TicketDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void createTicket(Ticket ticket) {
        int id = 0;
        Session session = sessionFactory.getCurrentSession();
        id = (Integer) session.save(ticket);
    }

    @Override
    @Transactional
    public List<Ticket> getEmployeeTickets(String employeeId) {
        Session session = null;
        List<Ticket> tickets = null;
        session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Ticket where OWNER=:ticketOwner and (STATE=:draft or STATE=:declined)order by URGENCY, DESIRED_RESOLUTION_DATE")
                .setString("ticketOwner", employeeId)
                .setString("draft", String.valueOf(State.DRAFT.ordinal()))
                .setString("declined", String.valueOf(State.DECLIENED.ordinal()));
        tickets = (ArrayList<Ticket>) query.list();
        return tickets;
    }

    @Override
    @Transactional
    public List<Ticket> getEmployeeAllTickets(String employeeId) {
        Session session = null;
        List<Ticket> tickets = null;
        session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Ticket where OWNER=:ticketOwner  order by URGENCY, DESIRED_RESOLUTION_DATE").setString("ticketOwner", employeeId);
        tickets = (ArrayList<Ticket>) query.list();
        return tickets;
    }

    @Override
    @Transactional
    public List<Ticket> getManagerTickets(String managerId) {
        Session session = null;
        List<Ticket> tickets = null;
        session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Ticket where OWNER=:ticketOwnerApprover or (APPROVER=:ticketOwnerApprover and STATE=:approved) order by URGENCY, DESIRED_RESOLUTION_DATE")
                .setString("ticketOwnerApprover", managerId)
                .setString("approved", String.valueOf(State.APPROVED.ordinal()));
        tickets = (ArrayList<Ticket>) query.list();
        return tickets;
    }

    @Override
    @Transactional
    public List<Ticket> getManagerAllTickets(String managerId) {
        Session session = null;
        List<Ticket> tickets = null;
        session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Ticket where OWNER=:ticketOwnerApprover or APPROVER=:ticketOwnerApprover or (STATE=:new and APPROVER is null) order by URGENCY, DESIRED_RESOLUTION_DATE")
                .setString("ticketOwnerApprover", managerId)
                .setString("new", String.valueOf(State.NEW.ordinal()));
        tickets = (ArrayList<Ticket>) query.list();
        return tickets;
    }

    @Override
    @Transactional
    public List<Ticket> getEngineerTickets(String engineerId) {
        Session session = null;
        List<Ticket> tickets;
        session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Ticket where assignee=:ticketAssignee order by URGENCY, DESIRED_RESOLUTION_DATE")
                .setString("ticketAssignee", engineerId);
        tickets = (ArrayList<Ticket>) query.list();
        return tickets;
    }

    @Override
    @Transactional
    public List<Ticket> getEngineerAllTickets(String engineerId) {
        Session session = null;
        List<Ticket> tickets = null;
        session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Ticket where (assignee=:ticketAssignee and (state=:progress or state=:done)) or state=:approved order by URGENCY, DESIRED_RESOLUTION_DATE")
                .setString("ticketAssignee", engineerId)
                .setString("progress", String.valueOf(State.IN_PROGRESS.ordinal()))
                .setString("done", String.valueOf(State.DONE.ordinal()))
                .setString("approved", String.valueOf(State.APPROVED.ordinal()));
        tickets = (ArrayList<Ticket>) query.list();
        return tickets;
    }

    @Override
    @Transactional
    public Ticket getTicketById(int id) {

        Ticket ticket = null;
        Session session = sessionFactory.getCurrentSession();
        ticket = (Ticket) session.get(Ticket.class, id);
        return ticket;
    }

    @Override
    @Transactional
    public void updateTicket(Ticket ticket) {

        Session session = sessionFactory.getCurrentSession();
        session.update(ticket);

    }
}



