package com.eventbookingsystem;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 Title, category, description, start date, start time,
 finish date, end time, location, capacity, and number of
 available tickets are all represented by the event class in the system.
 */
public class Event {
    private String title; // The title for the event
    private String category; // (e.g., Concert, Seminar)
    private String description; // Additional information about the event
    private LocalDate startDate, endDate; // The date on which the event starts/ends
    private LocalTime startTime, endTime; // The time at which the event starts/ends
    private String location; // Where the event will take place
    private int capacity; // The total number of seats
    private int ticketsRemaining; // The number of tickets available for booking

    // Constructor
    public Event(String title, String category, String description, LocalDate startDate,
                 LocalTime startTime, LocalDate endDate, LocalTime endTime, String location, int capacity) {
        this.title = title;
        this.category = category;
        this.description = description;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.location = location;
        this.capacity = capacity;
        this.ticketsRemaining = capacity; // Initially, no ticket is booked.

    }

    /** The getters which we used */

    public String getTitle() {
        return title;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public int getTicketsRemaining() {
        return ticketsRemaining;
    }


    // Methods

    public boolean bookTickets(int numberOfTickets) {
        if (numberOfTickets <= ticketsRemaining) {
            ticketsRemaining -= numberOfTickets;
            return true;
        }
        return false;
    }

    public void updateEvent(String title, String category, String description,
                            LocalDate startDate, LocalTime startTime, LocalDate endDate,
                            LocalTime endTime, String location, int capacity) {
        this.title = title;
        this.category = category;
        this.description = description;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.location = location;
        this.capacity = capacity;
        this.ticketsRemaining = capacity;
    }

    @Override
        public String toString() {
            return title + " (" + category + ") Description: " + description
                    + "\n Start: " + startDate +" "+ startTime + ", End: " + endDate +" "+ endTime
                    + "\n Location: " + location + ", Remaining tickets: " + ticketsRemaining;
    }
}
