package com.parkinglot;

import com.parkinglot.enums.SpotType;
import com.parkinglot.model.*;
import com.parkinglot.model.spots.LargeSpot;
import com.parkinglot.model.spots.MediumSpot;
import com.parkinglot.model.spots.SmallSpot;
import com.parkinglot.model.vehicles.Bike;
import com.parkinglot.model.vehicles.Car;
import com.parkinglot.repository.TicketRepository;
import com.parkinglot.service.ParkingLotService;
import com.parkinglot.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
            initializeParkingLotData();

            ParkingLotService service = new ParkingLotService();
            TicketRepository ticketRepo = new TicketRepository();
            Vehicle car = new Car("TS07FX1503");

            Ticket ticket = service.parkVehicle(car, SpotType.MEDIUM);
            System.out.println("Vehicle1 is parked. Ticket ID: " + ticket.getId());

            Vehicle bike = new Bike("AP28DM1403");

            Ticket ticket1 = service.parkVehicle(bike, SpotType.LARGE);
            System.out.println("Vehicle2 is Parked. Ticket ID: " + ticket1.getId());

            try{
                Thread.sleep(5000);
            }
            catch (Exception e){
                e.printStackTrace();
            }

            double bill = service.unparkVehicle(ticket.getId());
            System.out.println("Vehicle is unparked. Bill amount: $"+bill);

            double bill1 = service.unparkVehicle(ticket1.getId());
            System.out.println("Vehicle is unparked. Bill amount: $"+bill1);

            Session session = HibernateUtil.getSessionFactory().openSession();

            Double revenue = ticketRepo.getTotalRevenue(session);

            System.out.println("Total revenue: $"+revenue);
            session.close();
    }

    private static void initializeParkingLotData(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try{
            ParkingLot lot = new ParkingLot("Inorbit mall");
            ParkingFloor floor1 = new ParkingFloor(1);
            floor1.setParkingLot(lot);

            floor1.addSpot(new SmallSpot("S1",floor1));
            floor1.addSpot(new MediumSpot("M1",floor1));
            floor1.addSpot(new LargeSpot("L1",floor1));

            lot.addFloor(floor1);
            session.persist(lot);
            transaction.commit();
            System.out.println("Parking Lot Successfully initialized");
        }

        catch (Exception e){
            transaction.rollback();
            throw e;
        }
        finally {
            session.close();
        }

    }
}

