package com.parkinglot.repository;

import com.parkinglot.enums.SpotType;
import com.parkinglot.model.ParkingSpot;
import org.hibernate.Session;

import java.util.List;

public class ParkingSpotRepository {
    public ParkingSpot findAvailableSpotByType(Session session, SpotType spotType) throws ClassNotFoundException {
        String hql = "FROM ParkingSpot ps WHERE ps.isOccupied = false AND ps.spotType = ?1";


        List<ParkingSpot> spots = session
                .createQuery(hql, ParkingSpot.class)
                .setParameter(1,spotType)
                .setMaxResults(1)
                .list();

        return spots.isEmpty() ? null : spots.get(0);
    }

}

