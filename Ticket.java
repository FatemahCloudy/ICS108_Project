public class Ticket {
    private Event event;
    private String purchaserName;
    private int seatNumber;

    public Ticket(Event event, String purchaserName, int seatNumber) {
        this.event = event;
        this.purchaserName = purchaserName;
        this.seatNumber = seatNumber;
    }

    public Event getEvent() {
        return event;
    }

    public String getPurchaserName() {
        return purchaserName;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "eventTitle: '" + event.getTitle() + '\'' +
                ", purchaserName: '" + purchaserName + '\'' +
                ", seatNumber: " + seatNumber +
                '}';
    }
}
