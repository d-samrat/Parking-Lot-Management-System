package com.parkinglot.controller;

import com.parkinglot.dto.ParkRequest;
import com.parkinglot.enums.VehicleType;
import com.parkinglot.model.Ticket;
import com.parkinglot.model.Vehicle;
import com.parkinglot.model.vehicles.Bike;
import com.parkinglot.model.vehicles.Car;
import com.parkinglot.model.vehicles.Truck;
import com.parkinglot.service.ParkingLotService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ParkingController {

    private final ParkingLotService parkingLotService;

    public ParkingController(ParkingLotService parkingLotService){
        this.parkingLotService = parkingLotService;
    }

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/park")
    public String showParkForm(Model model){
        model.addAttribute("parkRequest", new ParkRequest());
        model.addAttribute("vehicleTypes", VehicleType.values());
        return "park";
    }

    @PostMapping("/park")
    public String parkVehicle(@ModelAttribute ParkRequest request, Model model){
        Vehicle vehicle;

        switch(request.getVehicleType()){
            case CAR-> vehicle = new Car();
            case BIKE-> vehicle = new Bike();
            case TRUCK-> vehicle = new Truck();
            default-> throw new RuntimeException("Invalid vehicle type");
        }

        vehicle.setRegNumber(request.getRegNumber());
        vehicle.setVehicleType(request.getVehicleType());

        Ticket ticket = parkingLotService.parkVehicle(vehicle);

        model.addAttribute("ticket",ticket);

        return "ticket";
    }

    @GetMapping("/unpark")
    public String showUnparkForm(){
        return "unpark";
    }

    @PostMapping("/unpark")
    public String unparkVehicle(@RequestParam long ticketId, Model model){
        double bill = parkingLotService.unparkVehicle(ticketId);

        model.addAttribute("bill",bill);
        return "bill";
    }
}