package com.parkinglot.model;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class ParkingFloor {
    private int floorNumber;
    private Map<String, ParkingSpot> spotMap;
    private PriorityQueue<ParkingSpot> availableSpots;

    public ParkingFloor(int floorNumber){
        this.floorNumber = floorNumber;
        this.spotMap = new HashMap<>();
        this.availableSpots = new PriorityQueue<>(Comparator.comparing(s->s.spotId));
    }

    public void addSpot(ParkingSpot spot){
        spotMap.put(spot.spotId, spot);
        availableSpots.add(spot);
    }

    public ParkingSpot getAvailableSpot(Vehicle vehicle){
        for(ParkingSpot spot: availableSpots){
            if(!spot.isOccupied()&&spot.canParkVehicle(vehicle)){
                return spot;
            }
        }
        return null;
    }

    public void markSpotOccupied(ParkingSpot spot, Vehicle vehicle){
        spot.parkVehicle(vehicle);
        availableSpots.remove(spot);
    }

    public void freeSpot(ParkingSpot spot){
        spot.removeVehicle();
        availableSpots.add(spot);
    }

    public int getFloorNumber(){
        return floorNumber;
    }


}
