package com.parkinglot.service;

import com.parkinglot.enums.SpotType;
import com.parkinglot.model.ParkingFloor;
import com.parkinglot.model.ParkingLot;
import com.parkinglot.model.ParkingSpot;
import com.parkinglot.model.Ticket;
import com.parkinglot.repository.ParkingLotRepository;
import com.parkinglot.repository.ParkingSpotRepository;
import com.parkinglot.repository.ParkingFloorRepository;
import com.parkinglot.repository.TicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AdminService {
    private final ParkingLotRepository lotRepo;
    private final ParkingFloorRepository floorRepo;
    private final ParkingSpotRepository spotRepo;
    private final TicketRepository ticketRepo;

    public AdminService(ParkingLotRepository lotRepo, ParkingFloorRepository floorRepo, ParkingSpotRepository spotRepo, TicketRepository ticketRepo){
        this.lotRepo = lotRepo;
        this.floorRepo = floorRepo;
        this.spotRepo = spotRepo;
        this.ticketRepo = ticketRepo;
    }

    public void createParkingLot(String name){
        ParkingLot lot = new ParkingLot();
        lot.setName(name);
        lotRepo.save(lot);
    }

    public void addFloor(long lotId, int floorNumber){
        ParkingLot lot = lotRepo.findById(lotId);
        ParkingFloor floor = new ParkingFloor();
        floor.setFloorNumber(floorNumber);
        floor.setParkingLot(lot);

        floorRepo.save(floor);
    }

    public void addSpot(long floorId, String spotNumber, SpotType type){
        ParkingFloor floor = floorRepo.findById(floorId);

        ParkingSpot spot = new ParkingSpot();
        spot.setSpotNumber(spotNumber);
        spot.setOccupied(false);
        spot.setParkingFloor(floor);

        spotRepo.save(spot);
    }

    public List<Ticket> getAllTickets(){
        return ticketRepo.findAll();
    }
}
