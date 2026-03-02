package com.parkinglot.repository;

import com.parkinglot.model.ParkingSpot;
import com.parkinglot.model.Vehicle;
import jakarta.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class VehicleRepository {

    private final SessionFactory sessionFactory;

    public VehicleRepository(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void save(Vehicle vehicle){
        sessionFactory.getCurrentSession().persist(vehicle);
    }
}
