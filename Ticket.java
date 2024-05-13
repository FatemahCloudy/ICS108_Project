package com.eventbookingsystem;

/**
 * Ticket class to represent a ticket for an event within the event booking system.
 * Each ticket is associated with a specific event and has quantity
 */

// This is a record class, just to store the information of each ticket the user book

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record Ticket(Event event, int numTickets, LocalDate startDate,
                     LocalTime startTime, LocalDate endDate, LocalTime endTime) {

    /**
     * Method to Provides a string representation of the ticket, detailing the number of tickets,
     * the title of the event, and other relevant event details.
     */

    @Override
    public String toString() {
        return "Ticket{" +
                "event: '" + event.getTitle() + '\'' +
                ", number of tickets: " + numTickets +
                '}';
    }
    public Event getEvent() {return event;}
    public int getNumTickets() {return numTickets;}
}
