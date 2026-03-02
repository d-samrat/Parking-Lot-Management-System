package com.parkinglot;

import com.parkinglot.model.ParkingSpot;
import com.parkinglot.model.Ticket;
import com.parkinglot.model.Vehicle;
import com.parkinglot.model.spots.SmallSpot;
import com.parkinglot.model.vehicles.Car;
import com.parkinglot.repository.ParkingSpotRepository;
import com.parkinglot.service.ParkingLotService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ParkingLotManagementV3Application {

    public static void main(String[] args) {
        SpringApplication.run(ParkingLotManagementV3Application.class, args);
    }

}
