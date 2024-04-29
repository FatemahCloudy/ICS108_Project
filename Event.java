package com.example.ics108_project;
//ToDo: show a message for the user if he tried to book tickets more than available
public class Event {
    private String title;
    private String category;
    private String description;
    private String date;
    private String time;
    private String location;
    private int capacity;
    private int bookedTickets;

    public Event(String title, String category, String description, String date, String time, String location, int capacity) {
        this.title = title;
        this.category = category;
        this.description = description;
        this.date = date;
        this.time = time;
        this.location = location;
        this.capacity = capacity;
        this.bookedTickets = 0;
    }

public void bookTickets(int numberOfTickets) {bookedTickets+= numberOfTickets;}

public Ticket book(String purchaserName) { // for the user
    if (availableTickets() > 1) {
        int seatNumber = capacity - availableTickets() + 1;
        bookTickets(1);
        return new Ticket(this, purchaserName, seatNumber);

    } else {return null;} // No tickets available
}

    public int availableTickets() {
        return capacity - bookedTickets;
    }

    @Override
    public String toString() {
        return " title:'" + title + '\'' +
                ", category:'" + category + '\'' +
                ", description: '" + description + '\'' +
                ", date: " + date +
                ", time: " + time +
                ", location: '" + location + '\'' +
                ", capacity: " + capacity +
                ", availableTickets: " + availableTickets();
    }

    // Admin methods to edit the event details
    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {this.date = date;}

    public void setTime(String time) {this.time = time;}

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    // getters
    public String getTitle() {return title;}
    public String getDate() {return date;}
    public String getTime() {return time;}
    public int getCapacity() {return capacity;}
    public String getCategory() {return category;}
    public String getDescription() {return description;}
    public String getLocation() {return location;}
    public int getBookedTickets() {return bookedTickets;}
    public int getAvailableTickets() {return capacity - bookedTickets;}
}
