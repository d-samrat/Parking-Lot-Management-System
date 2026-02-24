package com.parkinglot.service;

import com.parkinglot.enums.SpotType;
import com.parkinglot.enums.VehicleType;
import com.parkinglot.model.*;
import com.parkinglot.repository.ParkingSpotRepository;
import com.parkinglot.repository.TicketRepository;
import com.parkinglot.strategy.VehicleBasedPricingStrategy;
import com.parkinglot.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ParkingLotService {
    private ParkingLot parkingLot;
    private TicketService ticketService;
    private BillingService billingService;
//    private ParkingStrategy parkingStrategy;

    private ParkingSpotRepository spotRepo = new ParkingSpotRepository();
    private TicketRepository ticketRepo = new TicketRepository();

    public ParkingLotService(ParkingLot parkingLot, TicketService ticketService, BillingService billingService){
        this.parkingLot = parkingLot;
        this.ticketService = ticketService;
        this.billingService = billingService;
//        this.parkingStrategy = parkingStrategy;
    }

    public ParkingLotService() {
    }

    public Ticket parkVehicle(Vehicle vehicle, SpotType spotType) throws ClassNotFoundException {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = session.beginTransaction();

        try{
            session.persist(vehicle);

            ParkingSpot spot = spotRepo.findAvailableSpotByType(session,spotType);

            if(spot==null){
                throw new RuntimeException("No available spot");
            }

            spot.setOccupied(true);

            Ticket ticket = new Ticket(spot,vehicle);

            session.persist(spot);

            ticketRepo.save(session, ticket);
            tx.commit();
            return ticket;
        }
        catch(Exception e){
            tx.rollback();
            throw e;
        }
        finally {
            session.close();
        }

    }

    public double unparkVehicle(Long ticketId){

        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = session.beginTransaction();

        try{
            Ticket ticket = ticketRepo.findById(session,ticketId);

            if(ticket==null){
                throw new RuntimeException("Invalid Ticket");
            }

            ticket.setExitTime(System.currentTimeMillis());

            double bill = new VehicleBasedPricingStrategy().calculateFee(ticket.getEntryTime(),ticket.getExitTime(), VehicleType.CAR);

            ticket.setBillAmount(bill);

            ticket.getParkingSpot().setOccupied(false);

            tx.commit();
            return bill;
        }
        catch (Exception e){
            tx.rollback();
            throw e;
        }
        finally {
            session.close();
        }
    }

}
