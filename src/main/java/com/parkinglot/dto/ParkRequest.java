package com.parkinglot.dto;

import com.parkinglot.enums.VehicleType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParkRequest {
    private String regNumber;
    private VehicleType vehicleType;
}
