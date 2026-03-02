package com.parkinglot.repository;

import com.parkinglot.enums.SpotType;
import com.parkinglot.model.ParkingSpot;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParkingSpotRepository {

    private final SessionFactory sessionFactory;

    public ParkingSpotRepository(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    public ParkingSpot findAvailableSpot(){
        Session session = sessionFactory.getCurrentSession();

        String hql = "FROM ParkingSpot WHERE isOccupied = false";


//        List<ParkingSpot> spots = session
//                .createQuery(hql, ParkingSpot.class)
//                .setParameter(1,spotType)
//                .setMaxResults(1)
//                .list();
//
//        return spots.isEmpty() ? null : spots.get(0);
        return session.createQuery(hql, ParkingSpot.class)
                .setMaxResults(1)
                .uniqueResult();
    }

    @Transactional
    public void save(ParkingSpot spot){
        sessionFactory.getCurrentSession().persist(spot);
    }

}

