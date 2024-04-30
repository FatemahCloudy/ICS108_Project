package com.example.ics108_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// ToDo: let the user view the tickets after being booked successfully
public class UserScene {
    private Stage stage;
    private ListView<Event> eventListView;
    private Label titleLabel;
    private Label locationLabel;
    private Label dateLabel;
    private Label timeLabel;
    private Label availableTicketsLabel;
    private Spinner<Integer> ticketsSpinner;
    private TextField nameField;
    private String purchaserName;

    private ObservableList<Event> events;

    public UserScene(Stage stage) {
        this.stage = stage;
        events = FXCollections.observableArrayList();
        initialize();
    }

    private void initialize() {
        // Show the events for the user to choose and book tickets
        eventListView = new ListView<>();
        eventListView.setItems(events);
        eventListView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Event item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getTitle());
                }
            }
        });
        eventListView.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) ->
                displayEventDetails(newValue));

        titleLabel = new Label();
        locationLabel = new Label();
        dateLabel = new Label();
        timeLabel = new Label();
        availableTicketsLabel = new Label();

        // let the user choose how many tickets they want to book
        Label ticketsLabel = new Label("Number of Tickets:");
        ticketsSpinner = new Spinner<>(1, 10, 1);
        nameField = new TextField();
        purchaserName = nameField.getText();

        // the user press this button to book, then, they get a message (Success or failed)
        Button bookButton = new Button("Book Tickets");
        bookButton.setOnAction(event -> {
            Ticket bookedTicket = bookTickets();
            if (bookedTicket != null) {
                // Display success alert
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Ticket Booking");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Ticket booked successfully!");
                successAlert.showAndWait();
            } else {
                // Display error alert
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Ticket Booking");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("No available tickets.");
                errorAlert.showAndWait();
            }
        });

        // organize everything in the layout
        GridPane formLayout = new GridPane();
        formLayout.setHgap(10);
        formLayout.setVgap(10);
        formLayout.setAlignment(Pos.CENTER);
        formLayout.addRow(0, new Label("Title:"), titleLabel);
        formLayout.addRow(1, new Label("Location:"), locationLabel);
        formLayout.addRow(2, new Label("Date:"), dateLabel);
        formLayout.addRow(3, new Label("Time:"), timeLabel);
        formLayout.addRow(4, availableTicketsLabel);
        formLayout.addRow(5, ticketsLabel, ticketsSpinner);
        formLayout.addRow(6, bookButton);

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(eventListView, formLayout);

        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
    }

    public void setEvents(List<Event> events) {
        // upcoming events that user can book
        List<Event> upcomingEvents = new ArrayList<>();
        for (Event event : events) {
            if (event.getDateTime().toLocalDate().isAfter(LocalDate.now())) {
                upcomingEvents.add(event);
            }
        }

        this.events = FXCollections.observableArrayList(upcomingEvents);
        eventListView.setItems(this.events);
    }
    // display event details
    private void displayEventDetails(Event event) {
        if (event != null) {
            titleLabel.setText(event.getTitle());
            locationLabel.setText(event.getLocation());
            dateLabel.setText(event.getDateTime().toLocalDate().toString());
            timeLabel.setText(event.getDateTime().toLocalTime().toString());
            availableTicketsLabel.setText("Available Tickets: " + event.getAvailableTickets());
            ticketsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, event.getAvailableTickets(), 1));
        } else {
            titleLabel.setText("");
            locationLabel.setText("");
            dateLabel.setText("");
            timeLabel.setText("");
            availableTicketsLabel.setText("");
            ticketsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1, 1));
        }
    }
    private void viewBookedTickets(Ticket bookedTicket) {
        // Create a new dialog window to display the ticket details
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Booked Tickets");

        // Create labels to display the ticket details
        Label titleLabel = new Label("Event: " + bookedTicket.getEvent().getTitle());
        Label locationLabel = new Label("Location: " + bookedTicket.getEvent().getLocation());
        Label dateLabel = new Label("Date: " + bookedTicket.getEvent().getDateTime().toLocalDate());
        Label timeLabel = new Label("Time: " + bookedTicket.getEvent().getDateTime().toLocalTime());
        Label ticketsLabel = new Label("Number of Tickets: " + bookedTicket.getNumTickets());

        // Create a layout to hold the labels
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(titleLabel, locationLabel, dateLabel, timeLabel, ticketsLabel);

        // Create a scene with the layout and set it to the dialog stage
        Scene scene = new Scene(layout);
        dialogStage.setScene(scene);

        // Display the dialog stage
        dialogStage.show();
    }
    // the user can only book upcoming events, past events cannot be booked
// the user can only book upcoming events, past events cannot be booked
    private Ticket bookTickets() {
        Event selectedEvent = eventListView.getSelectionModel().getSelectedItem();
        if (selectedEvent != null) {
            int numTickets = ticketsSpinner.getValue();

            if (selectedEvent.getDateTime().toLocalDate().isAfter(LocalDate.now())
                    && selectedEvent.getAvailableTickets() >= numTickets) {
                selectedEvent.bookTickets(numTickets);
                availableTicketsLabel.setText("Available Tickets: " + selectedEvent.getAvailableTickets());
                System.out.println("Tickets booked for event: " + selectedEvent.getTitle() +
                        " (" + numTickets + " tickets)");

                // Create a new Ticket object for the booked tickets
                Ticket bookedTicket = new Ticket(selectedEvent, purchaserName, numTickets);

                // Display success alert
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Ticket Booking");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Ticket booked successfully!");
                successAlert.showAndWait();

                // View the booked tickets
                viewBookedTickets(bookedTicket);

                // Return the booked ticket
                return bookedTicket;
            } else { // the user cannot book some events (past events or no available tickets)
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Invalid Booking");
                alert.setHeaderText(null);

                if (selectedEvent.getDateTime().toLocalDate().isBefore(LocalDate.now())) {
                    alert.setContentText("You cannot book tickets for past events.");
                } else {
                    alert.setContentText("Not enough available tickets for booking.");
                    alert.showAndWait();
                }
            }
        }
        return null;
    }
        public Scene getScene() {return stage.getScene();}
}

