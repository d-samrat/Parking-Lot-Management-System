package com.parkinglot.model;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name="parking_floor")
public class ParkingFloor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int floorNumber;

    @ManyToOne
    @JoinColumn(name="parking_lot_id")
    private ParkingLot parkingLot;

    @OneToMany(mappedBy = "parkingFloor", cascade = CascadeType.ALL)
    private List<ParkingSpot> spotMap;

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    @Transient
    private PriorityQueue<ParkingSpot> availableSpots;

    public ParkingFloor(int floorNumber){
        this.floorNumber = floorNumber;
        this.spotMap = new ArrayList<>();
//        this.availableSpots = new PriorityQueue<>(Comparator.comparing(s->s.spotId));
    }

    public void addSpot(ParkingSpot spot){
        spotMap.add(spot);
//        availableSpots.add(spot);
    }

    public ParkingSpot getAvailableSpot(Vehicle vehicle){
        for(ParkingSpot spot: availableSpots){
            if(!spot.isOccupied()&&spot.canParkVehicle(vehicle)){
                return spot;
            }
        }
        return null;
    }

    public ParkingFloor() {
    }

    public void markSpotOccupied(ParkingSpot spot, Vehicle vehicle){
        spot.parkVehicle(vehicle);
//        availableSpots.remove(spot);
    }

    public void freeSpot(ParkingSpot spot){
        spot.removeVehicle();
//        availableSpots.add(spot);
    }

    public int getFloorNumber(){
        return floorNumber;
    }


}
