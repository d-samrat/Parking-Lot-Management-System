package com.parkinglot.model.spots;

import com.parkinglot.enums.SpotType;
import com.parkinglot.enums.VehicleType;
import com.parkinglot.model.ParkingFloor;
import com.parkinglot.model.ParkingSpot;
import com.parkinglot.model.Vehicle;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("SMALL")
public class SmallSpot extends ParkingSpot {

    public SmallSpot(String spotId, ParkingFloor parkingFloor){
        super(spotId, SpotType.SMALL, parkingFloor);
    }

    public SmallSpot() {

    }

    public boolean canParkVehicle(Vehicle vehicle){
        boolean check = vehicle.getVehicleType()== VehicleType.BIKE;
//        if(check)vehicle.setSpotDetails(spotId);
        return check;
    }
}
