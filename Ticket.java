package com.example.ics108_project;
// This is a record class, just to store the information of each ticket the user book

public record Ticket(Event event, String purchaserName, int seatNumber) {

    @Override
    public String toString() {
        return "Ticket{" +
                "eventTitle: '" + event.getTitle() + '\'' +
                ", purchaserName: '" + purchaserName + '\'' +
                ", seatNumber: " + seatNumber +
                '}';
    }
}
