package com.example.ics108_project;

import java.time.LocalDateTime;

public class Event {
    private String title;
    private String category;
    private String description;
    LocalDateTime dateTime;
    private String location;
    private int capacity;
    private int bookedTickets;
    //ToDo: edit the date type
    public Event(String title, String category, String description, LocalDateTime dateTime, String location, int capacity) {
        this.title = title;
        this.category = category;
        this.description = description;
        this.dateTime = dateTime;
        this.location = location;
        this.capacity = capacity;
        this.bookedTickets = 0;
    }
    // Did the user just booked some tickets? Increment the number of booked tickets
    public void bookTickets(int numberOfTickets) {bookedTickets+= numberOfTickets;}

    // A useful method to see if there is enough tickets to book or no
    public int availableTickets() {return capacity - bookedTickets;}

    // ToDo: This method was never used, it is the one that generate tickets
    public Ticket book(String purchaserName) {
        if (availableTickets() > 1) {
            int seatNumber = capacity - availableTickets() + 1;
            bookTickets(1);
            return new Ticket(this, purchaserName, seatNumber);

        } else {return null;} // No tickets available
    }

    @Override
    public String toString() {
        return " title:'" + title + '\'' +
                ", category:'" + category + '\'' +
                ", description: '" + description + '\'' +
                ", date: " + dateTime.toLocalDate() +
                ", time: " + dateTime.toLocalTime() +
                ", location: '" + location + '\'' +
                ", capacity: " + capacity +
                ", availableTickets: " + availableTickets();
    }

    // Admin methods to edit the event details
    public void setTitle(String title) {this.title = title;}

    public void setCategory(String category) {this.category = category;}

    public void setDescription(String description) {this.description = description;}

    public void setDateTime(LocalDateTime dateTime) {this.dateTime = dateTime;}

    public void setLocation(String location) {this.location = location;}

    public void setCapacity(int capacity) {this.capacity = capacity;}

    // getters for everything. To be used across classes
    public String getTitle() {return title;}

    public LocalDateTime getDateTime() {return dateTime;}

    public int getCapacity() {return capacity;}

    public String getCategory() {return category;}

    public String getDescription() {return description;}

    public String getLocation() {return location;}

    public int getBookedTickets() {return bookedTickets;}

    public int getAvailableTickets() {return capacity - bookedTickets;}
}
