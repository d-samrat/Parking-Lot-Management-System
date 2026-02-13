package com.parkinglot.model;

public class Ticket {
    private String ticketId;
    private Vehicle vehicle;
    private long entryTime;
    private ParkingSpot parkingSpot;

    public Ticket(String ticketId, Vehicle vehicle, ParkingSpot parkingSpot, long entryTime){
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.entryTime = entryTime;
        this.parkingSpot = parkingSpot;
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

    public long getEntryTime(){
        return  entryTime;
    }
}
