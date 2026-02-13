package com.parkinglot.service;

import com.parkinglot.exception.SpotNotAvailableException;
import com.parkinglot.interfaces.ParkingStrategy;
import com.parkinglot.model.*;
import com.parkinglot.service.BillingService;

public class ParkingLotService {
    private ParkingLot parkingLot;
    private TicketService ticketService;
    private BillingService billingService;
    private ParkingStrategy parkingStrategy;

    public ParkingLotService(ParkingLot parkingLot, TicketService ticketService, BillingService billingService, ParkingStrategy parkingStrategy){
        this.parkingLot = parkingLot;
        this.ticketService = ticketService;
        this.billingService = billingService;
        this.parkingStrategy = parkingStrategy;
    }

    public Ticket parkVehicle(Vehicle vehicle){
        ParkingSpot spot = parkingStrategy.findSpot(parkingLot, vehicle);

        if(spot==null){
            throw new SpotNotAvailableException("No spot available for vehicle");
        }

        spot.getParkingFloor().markSpotOccupied(spot, vehicle);

        return ticketService.createTicket(vehicle,spot);
    }

    public double unparkVehicle(String ticketId){
        Ticket ticket = ticketService.getTicket(ticketId);

        if(ticket==null){
            throw new RuntimeException("Invalid Ticket");
        }

        double billAmount = billingService.calculateBill(ticket);

        ParkingSpot spot = ticket.getParkingSpot();

        spot.getParkingFloor().freeSpot(spot);

        ticketService.closeTicket(ticketId);

        return billAmount;
    }

}
