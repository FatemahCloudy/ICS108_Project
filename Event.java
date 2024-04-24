import java.time.LocalDateTime;

public class Event {
    private String title;
    private String category;
    private String description;
    private LocalDateTime dateTime;
    private String location;
    private int capacity;
    private int bookedTickets;

    public Event(String title, String category, String description, LocalDateTime dateTime, String location, int capacity) {
        this.title = title;
        this.category = category;
        this.description = description;
        this.dateTime = dateTime;
        this.location = location;
        this.capacity = capacity;
        this.bookedTickets = 0;
    }

public Ticket bookTicket(String purchaserName) {
    if (availableTickets() > 0) {
        int seatNumber = capacity - availableTickets() + 1;
        bookTickets(1); // Book 1 ticket
        return new Ticket(this, purchaserName, seatNumber);
    } else {
        return null; // No tickets available
    }
}

    public int availableTickets() {
        return capacity - bookedTickets;
    }

    @Override
    public String toString() {
        return "Event{" +
                "title:'" + title + '\'' +
                ", category:'" + category + '\'' +
                ", description: '" + description + '\'' +
                ", date: " + dateTime.toLocalDate() +
                ", time: " + dateTime.toLocalTime() +
                ", location: '" + location + '\'' +
                ", capacity: " + capacity +
                ", availableTickets: " + availableTickets() +
                '}';
    }

    // Admin methods to edit the event details
    public void editTitle(String title) {
        this.title = title;
    }

    public void editCategory(String category) {
        this.category = category;
    }

    public void editDescription(String description) {
        this.description = description;
    }

    public void editDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void editLocation(String location) {
        this.location = location;
    }

    public void editCapacity(int capacity) {
        this.capacity = capacity;
    }
}
