package com.parkinglot.interfaces;

import com.parkinglot.model.ParkingSpot;
import com.parkinglot.model.ParkingLot;
import com.parkinglot.model.Vehicle;

public interface ParkingStrategy {
    ParkingSpot findSpot(ParkingLot parkingLot, Vehicle vehicle);
}
