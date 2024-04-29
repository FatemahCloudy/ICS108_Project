package com.example.ics108_project;

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
