package com.example.ics108_project;

// This is a record class, just to store the information of each ticket the user book

public record Ticket(Event event, String purchaserName, int numTickets) {

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
