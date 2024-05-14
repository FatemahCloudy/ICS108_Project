package com.eventbookingsystem;

/**
 * Ticket record class to represent a ticket for an event.
 * Each ticket is associated with an event and have details about the date and time along with the quantity
 */

import java.time.LocalDate;
import java.time.LocalTime;

public record Ticket(Event event, int numTickets, LocalDate startDate,
                     LocalTime startTime, LocalDate endDate, LocalTime endTime) {

    /**
     * Method to represent the ticket as a string, detailing the title of the event
     * and the number of tickets.
     */

    @Override
    public String toString() {
        return "Ticket: " +
                "event: '" + event.getTitle() + '\'' +
                ", number of tickets: " + numTickets;
    }
}
