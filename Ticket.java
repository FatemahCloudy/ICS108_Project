package com.example.ics108_project;

// This is a record class, just to store the information of each ticket the user book

import java.time.LocalDateTime;

public record Ticket(Event event, String purchaserName, int numTickets, LocalDateTime dateTime) {

    public Ticket(Event event, String purchaserName, int numTickets) {
        this(event, purchaserName, numTickets, LocalDateTime.now()); //set the booking time to the current time
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "event: '" + event.getTitle() + '\'' +
                ", purchaser: '" + purchaserName + '\'' +
                ", number of tickets: " + numTickets +
                '}';
    }
    public Event getEvent() {return event;}
    public int getNumTickets() {return numTickets;}
}
