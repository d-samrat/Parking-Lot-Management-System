package com.parkinglot.repository;

import com.parkinglot.model.ParkingLot;
import com.parkinglot.model.Ticket;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;

import java.util.List;

@Repository
public class TicketRepository {

    private final SessionFactory sessionFactory;

    @PersistenceContext
    private EntityManager entityManager;

    public TicketRepository(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    public void save(Ticket ticket){
        sessionFactory.getCurrentSession().persist(ticket);
    }

    public Ticket findById(long id){
        return sessionFactory
                .getCurrentSession()
                .find(Ticket.class,id);
    }

    public Double getTotalRevenue() {
        String hql = "SELECT SUM(t.billAmount) FROM Ticket t";

        return sessionFactory
                .getCurrentSession()
                .createQuery(hql, Double.class)
                .uniqueResult();
    }

    public List<Ticket> findAll() {
        return entityManager
                .createQuery("from Ticket", Ticket.class)
                .getResultList();
    }

}
