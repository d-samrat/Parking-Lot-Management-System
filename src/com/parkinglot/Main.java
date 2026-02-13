package com.parkinglot;

import com.parkinglot.interfaces.ParkingStrategy;
import com.parkinglot.interfaces.PricingStrategy;
import com.parkinglot.model.ParkingFloor;
import com.parkinglot.model.ParkingLot;
import com.parkinglot.model.Vehicle;
import com.parkinglot.model.spots.LargeSpot;
import com.parkinglot.model.spots.MediumSpot;
import com.parkinglot.model.spots.SmallSpot;
import com.parkinglot.model.vehicles.Car;
import com.parkinglot.service.BillingService;
import com.parkinglot.service.ParkingLotService;
import com.parkinglot.service.TicketService;
import com.parkinglot.model.Ticket;
import com.parkinglot.strategy.NearestSpotStrategy;
import com.parkinglot.strategy.VehicleBasedPricingStrategy;

public class Main {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot("Inorbit mall");
        ParkingFloor floor1 = new ParkingFloor(1);

        ParkingStrategy parkingStrategy = new NearestSpotStrategy();
        PricingStrategy pricingStrategy = new VehicleBasedPricingStrategy();

        BillingService billingService = new BillingService(pricingStrategy);
        TicketService ticketService = new TicketService();
        ParkingLotService parkingLotService = new ParkingLotService(parkingLot,ticketService, billingService, parkingStrategy);

        floor1.addSpot(new SmallSpot("S1",floor1));
        floor1.addSpot(new MediumSpot("M1",floor1));
        floor1.addSpot(new LargeSpot("L1",floor1));

        parkingLot.addFloor(floor1);

        Vehicle car1 = new Car("TS07FX1503");

        Ticket ticket1 = parkingLotService.parkVehicle(car1);

        System.out.println("Vehicle is parked. Ticket ID: " + ticket1.getTicketId());

        try{
            Thread.sleep(5000);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        double bill = parkingLotService.unparkVehicle(ticket1.getTicketId());
        System.out.println("Bill amount: $"+bill);

    }
}
