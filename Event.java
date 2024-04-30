public class Event {
    // ... existing fields and methods ...

    public Ticket book(String purchaserName, int numTickets) {
        if (availableTickets() >= numTickets) {
            bookTickets(numTickets); // This method would update the number of bookedTickets.
            return new Ticket(this, purchaserName, numTickets);
        } else {
            // Handle the scenario where there are not enough tickets to book.
            System.out.println("Not enough tickets available to book.");
            return null;
        }
    }
    
    // ... rest of the Event class ...
}
