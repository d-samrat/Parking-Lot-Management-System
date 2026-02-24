package com.parkinglot.model.spots;

import com.parkinglot.enums.SpotType;
import com.parkinglot.enums.VehicleType;
import com.parkinglot.model.ParkingFloor;
import com.parkinglot.model.ParkingSpot;
import com.parkinglot.model.Vehicle;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


@Entity
@DiscriminatorValue("MEDIUM")
public class MediumSpot extends ParkingSpot {

    public MediumSpot() {

    }
    public MediumSpot(String spotId, ParkingFloor parkingFloor){
        super(spotId, SpotType.MEDIUM, parkingFloor);
    }

    public boolean canParkVehicle(Vehicle vehicle){
        boolean check = vehicle.getVehicleType()==VehicleType.CAR||vehicle.getVehicleType()==VehicleType.BIKE;
//        if(check)vehicle.setSpotDetails(spotId);
        return check;
    }

}
