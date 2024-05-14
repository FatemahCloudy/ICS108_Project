package com.eventbookingsystem;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * UserScene class provides the user interface in the booking system,
 * enabling users to view upcoming events, and book tickets.
 *
 * Features:
 * - Display upcoming events.
 * - Book tickets for events.
 */
public class UserScene {

    private EventHandler eventHandler; // Event data management handler
    private ListView<Event> eventListView; // This displays upcoming events
    private ListView<String> bookedTicketsListView; // This displays booked tickets
    private ComboBox<Integer> ticketNumberComboBox; // To select the number of tickets to book
    private Button bookingButton; // Button to book tickets
    private VBox layout; // The container of the UI components
    private VBox upcomingEvents, bookedTickets; // Containers for events and tickets
    private HBox bookingTools; // contains the comboBox and the booking button
    private ArrayList<Ticket> tickets; // List of tickets

    // Constructor
    public UserScene(EventHandler eventDataModel) {
        // Initializes the UserScene with a given EventDataModel
        this.eventHandler = eventDataModel;
        // Sets up the UI components
        eventListView = new ListView<>();
        bookedTicketsListView = new ListView<>();
        ticketNumberComboBox = new ComboBox<>();
        bookingButton = new Button("Book Tickets");
        // Initialize the main layout
        layout = new VBox(10);
        this.tickets = new ArrayList<>();
        // Call setupUI in the constructor
        setup();
    }

    // Methods

    private void setup() {
        // Refresh lists to load initial data
        refreshEventListView();
        updateBookedTicketsListView();

        // The user can book a maximum of 10 tickets each time
        for (int i = 1; i <= 10; i++)
            ticketNumberComboBox.getItems().add(i);

        // The default value is one ticket
        ticketNumberComboBox.setValue(1);

        // Action handlers for book ticket Button
        bookingButton.setOnAction(e -> bookTickets());

        // Create sections for upcoming events and booked tickets
        bookingTools = new HBox(10, new Label("Select number of tickets:"), ticketNumberComboBox, bookingButton);
        bookingTools.setAlignment(Pos.CENTER);

        upcomingEvents = new VBox(10, new Label("Upcoming Events:"), eventListView, bookingTools);
        bookedTickets = new VBox(10, new Label("Booked Tickets:"), bookedTicketsListView);

        // Add sections to the main layout
        layout.getChildren().addAll(upcomingEvents, bookedTickets); // Add both sections to the main layout
        layout.setAlignment(Pos.CENTER);
    }

    public VBox getScene() {return layout;}

    public void refreshEventListView() {
        // Clear the existing items
        eventListView.getItems().clear();
        for (Event event : eventHandler.getEvents()) {
            if (!event.getStartDate().isBefore(LocalDate.now())) {
                // Add the event if it's not in the past
                eventListView.getItems().add(event);
            }
        }
    }

    private void updateBookedTicketsListView() {
        bookedTicketsListView.getItems().clear();
        for (Ticket ticket : tickets) {
            bookedTicketsListView.getItems().add(ticket.toString());
        }
    }

    private void bookTickets() {
        Event selectedEvent = eventListView.getSelectionModel().getSelectedItem();
        if (selectedEvent == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "No event selected.");
            alert.showAndWait();
            return; // To stop the method
        }

        Integer numberOfTickets = ticketNumberComboBox.getValue();
        if (numberOfTickets == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select the number of tickets.");
            alert.showAndWait();
            return; // To stop the method
        }

        if (selectedEvent.bookTickets(numberOfTickets)) {
            LocalDate startDate = selectedEvent.getStartDate();
            LocalTime startTime = selectedEvent.getStartTime();
            LocalDate endDate = selectedEvent.getEndDate();
            LocalTime endTime = selectedEvent.getEndTime();

            // Call bookTickets function to book ticket
            bookTickets(selectedEvent, numberOfTickets, startDate, startTime, endDate, endTime);
            // Refresh the tickets view
            updateBookedTicketsListView();
            // Refresh the event list
            refreshEventListView();
        } else {
            Alert error = new Alert(Alert.AlertType.ERROR, "No enough tickets available!");
            error.showAndWait();
        }
    }

    public void bookTickets(Event event, int numTickets, LocalDate startDate,
                            LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        // Create a new Ticket
        Ticket newTicket = new Ticket(event, numTickets, startDate, startTime, endDate, endTime);
        // Add the ticket to the list
        tickets.add(newTicket);
        // Show a message to indicate successful booking and display remaining tickets
        Alert info = new Alert(Alert.AlertType.INFORMATION,
                "Tickets booked successfully! Remaining tickets: " + event.getTicketsRemaining());
        // Display the message
        info.showAndWait();
    }
}

