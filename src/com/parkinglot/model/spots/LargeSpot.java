package com.parkinglot.model.spots;

import com.parkinglot.enums.SpotType;
import com.parkinglot.model.ParkingFloor;
import com.parkinglot.model.ParkingSpot;
import com.parkinglot.model.Vehicle;

public class LargeSpot extends ParkingSpot {
    public LargeSpot(String spotId, ParkingFloor parkingFloor){
        super(spotId, SpotType.LARGE, parkingFloor);
    }

    public boolean canParkVehicle(Vehicle vehicle){
        boolean check = true;
//        if(check)vehicle.setSpotDetails(spotId);
        return check;
    }
}
