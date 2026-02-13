package com.parkinglot.model;

import com.parkinglot.enums.SpotType;

public abstract class ParkingSpot{
    protected String spotId;
    protected boolean isOccupied;
    protected Vehicle parkedVehicle;
    protected SpotType spotType;
    protected ParkingFloor parkingFloor;

    public ParkingSpot(String spotId, SpotType spotType, ParkingFloor parkingFloor){
        this.spotId = spotId;
        this.spotType = spotType;
        this.parkingFloor = parkingFloor;
        this.isOccupied = false;
    }

    public boolean isOccupied(){
        return isOccupied;
    }

    public abstract boolean canParkVehicle(Vehicle vehicle);

    public void parkVehicle(Vehicle vehicle){
        this.parkedVehicle = vehicle;
        this.isOccupied = true;
    }

    public void removeVehicle(Vehicle vehicle){
        this.parkedVehicle = null;
        this.isOccupied = false;
    }

    public void removeVehicle(){
        this.parkedVehicle = null;
        this.isOccupied = false;
    }

    public ParkingFloor getParkingFloor(){
        return parkingFloor;
    }

}
