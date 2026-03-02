package com.parkinglot.repository;

import com.parkinglot.model.ParkingFloor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParkingFloorRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(ParkingFloor floor) {
        entityManager.persist(floor);
    }

    public ParkingFloor findById(Long id) {
        return entityManager.find(ParkingFloor.class, id);
    }

    public List<ParkingFloor> findAll() {
        return entityManager
                .createQuery("from ParkingFloor", ParkingFloor.class)
                .getResultList();
    }

    public List<ParkingFloor> findByLotId(Long lotId) {
        return entityManager
                .createQuery("from ParkingFloor f where f.parkingLot.id = :lotId", ParkingFloor.class)
                .setParameter("lotId", lotId)
                .getResultList();
    }
}