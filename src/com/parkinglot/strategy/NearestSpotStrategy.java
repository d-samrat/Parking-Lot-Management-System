package com.parkinglot.strategy;

import com.parkinglot.interfaces.ParkingStrategy;
import com.parkinglot.model.ParkingFloor;
import com.parkinglot.model.ParkingSpot;
import com.parkinglot.model.ParkingLot;
import com.parkinglot.model.Vehicle;

public class NearestSpotStrategy implements ParkingStrategy {
    @Override
    public ParkingSpot findSpot(ParkingLot parkingLot, Vehicle vehicle){
        for(ParkingFloor floor: parkingLot.getFloors()){
            ParkingSpot spot = floor.getAvailableSpot(vehicle);

            if(spot!=null){
                return spot;
            }
        }
        return null;
    }
}
