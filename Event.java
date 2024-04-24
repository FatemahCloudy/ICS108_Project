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

public Ticket book(String purchaserName) {
    if (availableTickets() > NumberOfTickets) {
        int seatNumber = capacity - availableTickets() + 1;
        bookTickets(1); // To book only one eachTime (We shall find a way to book multi)
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
    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
