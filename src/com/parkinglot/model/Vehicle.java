package com.parkinglot.model;

import com.parkinglot.enums.SpotType;
import com.parkinglot.enums.VehicleType;

public class Vehicle {
    protected String regNumber;
    protected VehicleType vehicleType;
    protected String spotId;

    public Vehicle(String regNumber, VehicleType vehicleType){
        this.regNumber = regNumber;
        this.vehicleType = vehicleType;
    }

    public String getRegNumber(){
        return regNumber;
    }

    public VehicleType getVehicleType(){
        return vehicleType;
    }

    public void setSpotDetails(String spotId){
        this.spotId = spotId;
    }

    public String getSpotDetails(){
        return spotId;
    }
}
