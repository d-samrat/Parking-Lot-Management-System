package com.parkinglot.model.vehicles;

import com.parkinglot.enums.VehicleType;
import com.parkinglot.model.Vehicle;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


@Entity
@DiscriminatorValue("BIKE")
public class Bike extends Vehicle {

    public Bike(){}
    public Bike(String regNumber){
        super(regNumber);
        vehicleType = VehicleType.BIKE;
    }
}
