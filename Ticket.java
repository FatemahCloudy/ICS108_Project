package com.example.ics108_project;

import java.time.LocalDateTime;

public record Ticket(Event event, String purchaserName, int numTickets, LocalDateTime bookingTime) {

    // Constructor for Ticket, automatically provided by the record.
    // The bookingTime field has been added to track when the ticket was booked.

    public Ticket(Event event, String purchaserName, int numTickets) {
        this(event, purchaserName, numTickets, LocalDateTime.now());
        // Calls the canonical constructor to set the booking time to the current time.
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "event: '" + event.getTitle() + '\'' +
                ", purchaser: '" + purchaserName + '\'' +
                ", number of tickets: " + numTickets +
                ", booking time: " + bookingTime +
                '}';
    }

    // The getters for event, purchaserName, and numTickets are automatically generated.
    // However, if you need to explicitly define or override them, you can do so.
    // For example:

    public Event getEvent() {
        return event;
    }

    public String getPurchaserName() {
        return purchaserName;
    }

    public int getNumTickets() {
        return numTickets;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }
}
