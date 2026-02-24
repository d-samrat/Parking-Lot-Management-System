package com.parkinglot.service;

import com.parkinglot.model.ParkingSpot;
import com.parkinglot.model.Ticket;
import com.parkinglot.model.Vehicle;

import java.util.HashMap;
import java.util.Map;

public class TicketService {
    private Map<String, Ticket> activeTickets = new HashMap<>();


    public Ticket createTicket(Vehicle vehicle, ParkingSpot spot){
        String ticketId = "TICKET_"+System.currentTimeMillis();
        Ticket ticket = new Ticket(spot,vehicle);
        activeTickets.put(ticketId, ticket);
        return ticket;
    }

    public Ticket getTicket(String ticketId){
        return activeTickets.get(ticketId);
    }

    public void closeTicket(String ticketId){
        activeTickets.remove(ticketId);
    }
}

