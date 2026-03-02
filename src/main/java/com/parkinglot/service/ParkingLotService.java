package com.parkinglot.service;

import com.parkinglot.enums.SpotType;
import com.parkinglot.enums.VehicleType;
import com.parkinglot.model.*;
import com.parkinglot.repository.ParkingSpotRepository;
import com.parkinglot.repository.TicketRepository;
import com.parkinglot.repository.VehicleRepository;
import com.parkinglot.strategy.VehicleBasedPricingStrategy;
import com.parkinglot.util.HibernateUtil;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class ParkingLotService {
    private ParkingLot parkingLot;
    private TicketService ticketService;
    private BillingService billingService;
//    private ParkingStrategy parkingStrategy;

    private final ParkingSpotRepository spotRepo;
    private final TicketRepository ticketRepo;
    private final VehicleRepository vehicleRepo;

    public ParkingLotService(ParkingSpotRepository spotRepository, TicketRepository ticketRepository, VehicleRepository vehicleRepo) throws Exception {
        this.spotRepo = spotRepository;
        this.ticketRepo = ticketRepository;
        this.vehicleRepo = vehicleRepo;
    }


//    public ParkingLotService() {
//    }
    @Transactional
    public Ticket parkVehicle(Vehicle vehicle){

        vehicleRepo.save(vehicle);

        ParkingSpot spot = spotRepo.findAvailableSpot();

            if(spot==null){
                throw new RuntimeException("No available spot");
            }

            spot.setOccupied(true);

            Ticket ticket = new Ticket();
            ticket.setTicketCode("TICKET_"+System.currentTimeMillis());
            ticket.setEntryTime(System.currentTimeMillis());
            ticket.setVehicle(vehicle);
            ticket.setParkingSpot(spot);



            ticketRepo.save(ticket);
            return ticket;
        }

    @Transactional
    public double unparkVehicle(Long ticketId){

        Ticket ticket = ticketRepo.findById(ticketId);

        ticket.setExitTime(System.currentTimeMillis());

        double bill = new VehicleBasedPricingStrategy().calculateFee(ticket.getEntryTime(),ticket.getExitTime(), VehicleType.CAR);

            ticket.setBillAmount(bill);

            ticket.getParkingSpot().setOccupied(false);

            return bill;
    }

}
