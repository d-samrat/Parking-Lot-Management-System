package com.parkinglot.model;

import com.parkinglot.enums.SpotType;
import com.parkinglot.enums.VehicleType;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="vehicle_type")
@Table(name="vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    protected String regNumber;

    @Enumerated(EnumType.STRING)
    protected VehicleType vehicleType;

//    protected ParkingSpot spot;
    @Transient
    private String spotId;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<Ticket> tickets = new ArrayList<>();

    public Vehicle(){}

    public Vehicle(String regNumber){
        this.regNumber = regNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
