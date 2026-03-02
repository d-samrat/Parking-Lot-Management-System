package com.parkinglot.repository;

import com.parkinglot.model.ParkingLot;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParkingLotRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(ParkingLot lot) {
        entityManager.persist(lot);
    }

    public ParkingLot findById(Long id) {
        return entityManager.find(ParkingLot.class, id);
    }

    public List<ParkingLot> findAll() {
        return entityManager
                .createQuery("from ParkingLot", ParkingLot.class)
                .getResultList();
    }
}