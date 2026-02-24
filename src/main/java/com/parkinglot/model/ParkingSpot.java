package com.parkinglot.model;

import com.parkinglot.enums.SpotType;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "spot_type")
@Table(name="parking_spot")
public abstract class ParkingSpot{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    protected String spotId;
    protected boolean isOccupied;
    @Transient
    protected Vehicle parkedVehicle;

    @Enumerated(EnumType.STRING)
    protected SpotType spotType;

    @ManyToOne
    @JoinColumn(name = "floor_id")
    protected ParkingFloor parkingFloor;

    @OneToMany(mappedBy = "parkingSpot")
    private List<Ticket> tickets = new ArrayList<>();

    public ParkingSpot(String spotId, SpotType spotType, ParkingFloor parkingFloor){
        this.spotId = spotId;
        this.spotType = spotType;
        this.parkingFloor = parkingFloor;
        this.isOccupied = false;
    }

    public ParkingSpot() {
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

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public ParkingFloor getParkingFloor(){
        return parkingFloor;
    }

}
