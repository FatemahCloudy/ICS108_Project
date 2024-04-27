import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class UserScene {
    private Stage stage;
    private ListView<Event> eventListView;
    private Label titleLabel;
    private Label locationLabel;
    private Label dateLabel;
    private Label timeLabel;
    private Label availableTicketsLabel;
    private Spinner<Integer> ticketsSpinner;
    private Button bookButton;

    private ObservableList<Event> events;

    public UserScene(Stage stage) {
        this.stage = stage;
        events = FXCollections.observableArrayList();
        initialize();
    }

    private void initialize() {
        eventListView = new ListView<>();
        eventListView.setItems(events);
        eventListView.setCellFactory(param -> new ListCell<Event>() {
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
        eventListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
                displayEventDetails(newValue));

        titleLabel = new Label();
        locationLabel = new Label();
        dateLabel = new Label();
        timeLabel = new Label();
        availableTicketsLabel = new Label();

        Label ticketsLabel = new Label("Number of Tickets:");
        ticketsSpinner = new Spinner<>(1, 10, 1);

        bookButton = new Button("Book Tickets");
        bookButton.setOnAction(event -> bookTickets());

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
        
        List<Event> upcomingEvents = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();

        for (Event event : events) {
            if (event.getDate().isAfter(currentDate)) {
                upcomingEvents.add(event);
            }
        }

        this.events = FXCollections.observableArrayList(upcomingEvents);
        eventListView.setItems(this.events);
    }

    private void displayEventDetails(Event event) {
        if (event != null) {
            titleLabel.setText(event.getTitle());
            locationLabel.setText(event.getLocation());
            dateLabel.setText(event.getDate().toString());
            timeLabel.setText(event.getTime().toString());
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

    private void bookTickets() {
        Event selectedEvent = eventListView.getSelectionModel().getSelectedItem();
        if (selectedEvent != null) {
            int numTickets = ticketsSpinner.getValue();
            if (selectedEvent.getDate().isAfter(LocalDate.now()) && selectedEvent.getAvailableTickets() >= numTickets) {
              
                selectedEvent.bookTickets(numTickets);
                availableTicketsLabel.setText("Available Tickets: " + selectedEvent.getAvailableTickets());

                System.out.println("Tickets booked for event: " + selectedEvent.getTitle() + " (" + numTickets + " tickets)");
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Invalid Booking");
                alert.setHeaderText(null);

                if (selectedEvent.getDate().isBefore(LocalDate.now())) {
                    alert.setContentText("You cannot book tickets for past events.");
                } else {
                    alert.setContentText("Not enough available tickets for booking.");
                               alert.showAndWait();
            }
        }
    }
}
