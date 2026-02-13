package com.parkinglot.strategy;

import com.parkinglot.interfaces.PricingStrategy;
import com.parkinglot.enums.VehicleType;

public class VehicleBasedPricingStrategy implements PricingStrategy {


    public double calculateFee(long entryTime, long exitTime, VehicleType vehicleType){
        long durationMillis = exitTime - entryTime;
        long hours = (long)Math.ceil(durationMillis/(1000.0*60*60));
        double rate;

        switch(vehicleType){
            case BIKE:
                rate = 1;
                break;
            case CAR:
                rate = 2;
                break;
            case TRUCK:
                rate = 4;
                break;
            default:
                rate = 0;
        }
        return hours*rate;
    }
}