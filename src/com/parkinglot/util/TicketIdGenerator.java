package com.parkinglot.util;

public class TicketIdGenerator {
    public static String generateTicketId(){
        return "TICKET_"+System.currentTimeMillis();
    }
}

