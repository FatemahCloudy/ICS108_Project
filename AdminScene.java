package com.example.ics108_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

// ToDo: when a new event is declared, it need to be in a file
public class AdminScene {
    private Stage stage;
    private Scene scene;
    private TextField titleField;
    private TextField categoryField;
    private TextArea descriptionArea;
    private DatePicker datePicker;
    private TextField timeField;
    private TextField locationField;
    private TextField capacityField;
    private ListView<Event> eventListView;
    private Label statusLabel;
    VBox layout;
    HBox buttonLayout;
    GridPane formLayout;
    Button addButton, editButton, deleteButton;

    private ObservableList<Event> events;

    public AdminScene(Stage stage) {
        this.stage = stage;
        events = FXCollections.observableArrayList();
        initialize();
    }

    private void initialize() {
        titleField = new TextField();
        categoryField = new TextField();
        descriptionArea = new TextArea();
        datePicker = new DatePicker();
        timeField = new TextField();
        locationField = new TextField();
        capacityField = new TextField();

        addButton = new Button("Add Event");
        addButton.setOnAction(new AddButtonHandler());

        editButton = new Button("Edit Event");
        editButton.setOnAction(new EditButtonHandler());

        deleteButton = new Button("Delete Event");
        deleteButton.setOnAction(new DeleteButtonHandler());

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
        eventListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
                displayEventDetails(newValue));

        statusLabel = new Label();

        formLayout = new GridPane();
        formLayout.setHgap(10);
        formLayout.setVgap(10);
        formLayout.addRow(0, new Label("Title:"), titleField);
        formLayout.addRow(1, new Label("Category:"), categoryField);
        formLayout.addRow(2, new Label("Description:"), descriptionArea);
        formLayout.addRow(3, new Label("Date:"), datePicker);
        formLayout.addRow(4, new Label("Time:"), timeField);
        formLayout.addRow(5, new Label("Location:"), locationField);
        formLayout.addRow(6, new Label("Capacity:"), capacityField);

        buttonLayout = new HBox(10);
        buttonLayout.getChildren().addAll(addButton, editButton, deleteButton);

        layout = new VBox(10);
        layout.getChildren().addAll(formLayout, buttonLayout, eventListView, statusLabel);

        scene = new Scene(layout, 600, 400);
        stage.setScene(scene);
    }

    private void displayEventDetails(Event event) {
        if (event != null) {
            titleField.setText(event.getTitle());
            categoryField.setText(event.getCategory());
            descriptionArea.setText(event.getDescription());
            datePicker.setValue(event.getDateTime().toLocalDate());
            timeField.setText(event.getDateTime().toLocalTime().toString());
            locationField.setText(event.getLocation());
            capacityField.setText(String.valueOf(event.getCapacity()));
        } else {
            clearFields();
        }
    }

    private void clearFields() {
        titleField.clear();
        categoryField.clear();
        descriptionArea.clear();
        datePicker.setValue(null);
        timeField.clear();
        locationField.clear();
        capacityField.clear();
    }

    private LocalTime parseTime(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return LocalTime.parse(time, formatter);
    }

    public Scene getScene() {return scene;}

    class DeleteButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            if (e.getSource() == deleteButton) {
                Event selectedEvent = eventListView.getSelectionModel().getSelectedItem();
                if (selectedEvent != null) {
                    events.remove(selectedEvent);
                    clearFields();
                    statusLabel.setText("Event deleted: " + selectedEvent.getTitle());
                }
            }
        }
    }
    class AddButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            if (e.getSource() == addButton) {
                String title = titleField.getText();
                String category = categoryField.getText();
                String description = descriptionArea.getText();
                LocalDateTime dateTime = LocalDateTime.of(datePicker.getValue(), parseTime(timeField.getText()));
                String location = locationField.getText();
                int capacity = Integer.parseInt(capacityField.getText());

                Event event = new Event(title, category, description, dateTime, location, capacity);
                events.add(event);

                clearFields();
                statusLabel.setText("Event added: " + event.getTitle());
            }
        }
    }

    // ToDo: All the information is deleted when the admin press the button (need to be solved)
    class EditButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            Event selectedEvent = eventListView.getSelectionModel().getSelectedItem();
            if (selectedEvent != null) {
                String newTitle = titleField.getText();
                String newCategory = categoryField.getText();
                String newDescription = descriptionArea.getText();
                LocalDateTime newDateTime = LocalDateTime.of(datePicker.getValue(), parseTime(timeField.getText()));
                String newLocation = locationField.getText();
                int newCapacity = Integer.parseInt(capacityField.getText());

                selectedEvent.setTitle(newTitle);
                selectedEvent.setCategory(newCategory);
                selectedEvent.setDescription(newDescription);
                selectedEvent.setDateTime(newDateTime);
                selectedEvent.setLocation(newLocation);
                selectedEvent.setCapacity(newCapacity);

                clearFields();
                statusLabel.setText("Event edited: " + selectedEvent.getTitle());
            }
        }
    }
}
