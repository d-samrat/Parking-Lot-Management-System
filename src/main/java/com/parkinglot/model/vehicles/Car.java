package com.parkinglot.model.vehicles;

import com.parkinglot.enums.VehicleType;
import com.parkinglot.model.Vehicle;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CAR")
public class Car extends Vehicle {

    public Car() {
        super();
    }

    public Car(String regNumber){
        super(regNumber);
        vehicleType = VehicleType.CAR;
    }
}
