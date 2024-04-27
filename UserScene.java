import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserScene {
    private Stage stage;
    private ListView<Event> eventListView;
    private TextField ticketQuantityField;
    private Button bookButton;
    private Label statusLabel;

    private ObservableList<Event> events;

    public UserScene(Stage stage, ObservableList<Event> events) {
        this.stage = stage;
        this.events = events;
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
                    setText(item.getTitle() + " - " + item.getDate());
                }
            }
        });

        ticketQuantityField = new TextField();
        ticketQuantityField.setPromptText("Enter quantity");

        bookButton = new Button("Book Tickets");
        bookButton.setOnAction(event -> bookTickets());

        statusLabel = new Label();

        GridPane formLayout = new GridPane();
        formLayout.setHgap(10);
        formLayout.setVgap(10);
        formLayout.addRow(0, new Label("Ticket Quantity:"), ticketQuantityField);

        HBox buttonLayout = new HBox(10);
        buttonLayout.getChildren().add(bookButton);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(eventListView, formLayout, buttonLayout, statusLabel);

        Scene scene = new Scene(layout, 600, 400);
        stage.setScene(scene);
    }

    private void bookTickets() {
        Event selectedEvent = eventListView.getSelectionModel().getSelectedItem();
        if (selectedEvent != null) {
            int quantity = Integer.parseInt(ticketQuantityField.getText());
            // Logic to book the tickets for the selected event
            // Update the event's capacity
            // Show booking status in the statusLabel
        }
    }
}
