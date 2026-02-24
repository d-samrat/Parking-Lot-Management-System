package com.parkinglot.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double billAmount;

    private String ticketId;

    @ManyToOne
    @JoinColumn(name="vehicle_id")
    private Vehicle vehicle;

    private long entryTime;
    private long exitTime;

    @ManyToOne
    @JoinColumn(name="spot_id")
    private ParkingSpot parkingSpot;

    public Ticket() {
    }

    public Ticket(ParkingSpot parkingSpot, Vehicle vehicle){
        this.ticketId = "TICKET_" + System.currentTimeMillis();
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
        this.entryTime = System.currentTimeMillis();
    }

    public String getTicketId(){
        return ticketId;
    }

    public Vehicle getVehicle(){
        return vehicle;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public long getExitTime() {
        return exitTime;
    }

    public void setExitTime(long exitTime) {
        this.exitTime = exitTime;
    }

    public double getBillAmount() {
        return billAmount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setBillAmount(double billAmount) {
        this.billAmount = billAmount;
    }

    public long getEntryTime(){
        return  entryTime;
    }
}
