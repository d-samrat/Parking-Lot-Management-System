package com.parkinglot.model;
import java.util.ArrayList;
import java.util.List;


public class ParkingLot {
    private String name;
    private List<ParkingFloor> floors;

    public ParkingLot(String name){
        this.name = name;
        this.floors = new ArrayList<>();
    }

    public void addFloor(ParkingFloor floor){
        floors.add(floor);
    }

    public ParkingSpot findSpotForVehicle(Vehicle vehicle){
        for(ParkingFloor floor: floors){
            ParkingSpot spot = floor.getAvailableSpot(vehicle);
            if(spot!=null){
                return spot;
            }
        }
        return null;
    }

    public List<ParkingFloor> getFloors(){
        return floors;
    }
}
