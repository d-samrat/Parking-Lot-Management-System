package com.parkinglot.model;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="parking_lot")
public class ParkingLot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    public ParkingLot() {
    }

    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.ALL)
    private List<ParkingFloor> floors = new ArrayList<>();

    public ParkingLot(String name){
        this.name = name;
    }

    public void addFloor(ParkingFloor floor){
        floors.add(floor);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFloors(List<ParkingFloor> floors) {
        this.floors = floors;
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
