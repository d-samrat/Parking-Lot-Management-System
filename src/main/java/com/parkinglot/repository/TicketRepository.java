package com.parkinglot.repository;

import com.parkinglot.model.Ticket;
import org.hibernate.Session;

public class TicketRepository {
    public void save(Session session, Ticket ticket){
        session.persist(ticket);
    }

    public Ticket findById(Session session, long id){
        return session.find(Ticket.class,id);
    }

    public Double getTotalRevenue(Session session) {
        String hql = "SELECT SUM(t.billAmount) FROM Ticket t";

        return session.createQuery(hql, Double.class).uniqueResult();
    }

}
