package com.parkinglot.service;

import com.parkinglot.enums.VehicleType;
import com.parkinglot.interfaces.PricingStrategy;
import com.parkinglot.model.Ticket;

public class BillingService {
    private PricingStrategy pricingStrategy;

    public BillingService(PricingStrategy pricingStrategy){
        this.pricingStrategy = pricingStrategy;
    }

    public double calculateBill(Ticket ticket){
        long exitTime = System.currentTimeMillis();

        return pricingStrategy.calculateFee(ticket.getEntryTime(), exitTime, VehicleType.CAR);
    }
}
