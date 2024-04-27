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

public class UserScene {
    private Stage stage;
    private ListView<Event> eventListView;
    private Label selectedEventLabel;
    private Label availableSlotsLabel;
    private Spinner<Integer> slotsSpinner;
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

        selectedEventLabel = new Label("Selected Event: None");
        availableSlotsLabel = new Label("Available Slots: 0");

        Label slotsLabel = new Label("Number of Slots:");
        slotsSpinner = new Spinner<>(1, 10, 1);

        bookButton = new Button("Book Tickets");
        bookButton.setOnAction(event -> bookTickets());

        GridPane formLayout = new GridPane();
        formLayout.setHgap(10);
        formLayout.setVgap(10);
        formLayout.setAlignment(Pos.CENTER);
        formLayout.addRow(0, selectedEventLabel);
        formLayout.addRow(1, availableSlotsLabel);
        formLayout.addRow(2, slotsLabel, slotsSpinner);
        formLayout.addRow(3, bookButton);

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(eventListView, formLayout);

        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
    }

    public void setEvents(ObservableList<Event> events) {
        this.events = events;
        eventListView.setItems(events);
    }

    private void displayEventDetails(Event event) {
        if (event != null) {
            selectedEventLabel.setText("Selected Event: " + event.getTitle());
            availableSlotsLabel.setText("Available Slots: " + event.getAvailableSlots());
            slotsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, event.getAvailableSlots(), 1));
        } else {
            selectedEventLabel.setText("Selected Event: None");
            availableSlotsLabel.setText("Available Slots: 0");
            slotsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1, 1));
        }
    }

    private void bookTickets() {
        Event selectedEvent = eventListView.getSelectionModel().getSelectedItem();
        if (selectedEvent != null) {
            int numSlots = slotsSpinner.getValue();
            if (selectedEvent.getAvailableSlots() >= numSlots) {
                
                selectedEvent.bookTickets(numSlots);
                availableSlotsLabel.setText("Available Slots: " + selectedEvent.getAvailableSlots());

                System.out.println("Tickets booked for event: " + selectedEvent.getTitle() + " (" + numSlots + " slots)");
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Not Enough Slots");
                alert.setHeaderText(null);
                alert.setContentText("Not enough available slots for booking.");
                alert.showAndWait();
            }
        }
    }
}
