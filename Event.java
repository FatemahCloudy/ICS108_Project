package com.eventbookingsystem;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *  Event class represents an event in the event booking system. Each event has details such as title,
 *  category, description, date, time, location, and capacity, along with the remaining
 *  tickets available for booking.
 */
public class Event {

    // Data members of Event class
    private String title; // The title of the event
    private String category; // The category of the event (e.g., Concert, Seminar)
    private String description; // A detailed description of the event
    private LocalDate date; // The date on which the event is scheduled
    private LocalTime time; // The time at which the event starts
    private String location; // The location where the event will take place
    private int capacity; // The total number of seats available for the event
    private int ticketsRemaining; // The number of tickets currently available for booking

    // Constructor
    public Event(String title, String category, String description, LocalDate date, LocalTime time, String location, int capacity) {
        this.title = title;
        this.category = category;
        this.description = description;
        this.date = date;
        this.time = time;
        this.location = location;
        this.capacity = capacity;
        this.ticketsRemaining = capacity; // Initially, all tickets are available.
    }

    // Getters and setters for accessing and updating event properties
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
        // Reset available tickets when capacity changes
        this.ticketsRemaining = capacity;
    }

    public int getTicketsRemaining() {
        return ticketsRemaining;
    }

    /**
     * Method to check if there are tickets available
     * return true if the booking is successful (enough tickets are available), false otherwise
     */
    public boolean bookTickets(int numberOfTickets) {
        if (numberOfTickets <= ticketsRemaining) {
            ticketsRemaining -= numberOfTickets;
            return true;
        }
        return false;
    }

    // Method to update event details
    public void updateEvent(String title, String category, String description, LocalDate date, LocalTime time, String location, int capacity) {
        this.title = title;
        this.category = category;
        this.description = description;
        this.date = date;
        this.time = time;
        this.location = location;
        this.capacity = capacity;
        // Resetting tickets remaining to new capacity
        this.ticketsRemaining = capacity;
    }

    // Provides a string representation of the event, including all its details
    @Override
    public String toString() {
        return "Event: " +
                "title = '" + title + '\'' +
                ", category = '" + category + '\'' +
                ", description = '" + description + '\'' +
                ", date = " + date +
                ", time = " + time +
                ", location = '" + location + '\'' +
                ", capacity = " + capacity +
                ", ticketsRemaining = " + ticketsRemaining;
    }
}
