package com.parkinglot.interfaces;

import com.parkinglot.enums.VehicleType;
import com.parkinglot.model.Vehicle;

public interface PricingStrategy {
    double calculateFee(long entryTime, long exitTime, VehicleType vehicleType);
}
