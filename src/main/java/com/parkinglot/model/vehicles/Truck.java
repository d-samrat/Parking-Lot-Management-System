package com.parkinglot.model.vehicles;

import com.parkinglot.enums.VehicleType;
import com.parkinglot.model.Vehicle;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


@Entity
@DiscriminatorValue("TRUCK")
public class Truck extends Vehicle {
    public Truck(String regNumber){
        super(regNumber);
        vehicleType = VehicleType.BIKE;
    }

    public Truck(){}
}
