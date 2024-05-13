package com.eventbookingsystem;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * AdminScene class represents the admin interface for managing events in an event booking system.
 * It provides a graphical user interface (GUI) for adding, editing, and deleting events.
 *
 * The AdminScene class creates and manages a GridPane layout containing several input fields and buttons that allow
 * administrators to manage events effectively. It includes text fields for entering event details such as title,
 * category, description, location, time, and capacity. A date picker is used for selecting the event date.
 *
 * Features:
 * - A ComboBox for selecting the event category, with predefined options such as Conference, Concert, Seminar, and Workshop.
 * - TextField inputs for event title, time, location, and capacity, with a TextArea for the event description.
 * - DatePicker to select the date of the event.
 * - Buttons to add a new event, edit an existing event, and delete an event.
 * - A ListView to display the list of current events, which updates dynamically as events are added, edited, or removed.
 *
 * Functionality:
 * - Add Event: Adds a new event to the system based on the data entered in the input fields. It performs validation
 *   to ensure that all fields are filled out correctly before adding the event.
 * - Edit Event: Updates an existing event with new data entered in the input fields. It also performs validation and
 *   allows editing only if an event is selected from the ListView.
 * - Delete Event: Removes the selected event from the system.
 * - The ListView displays the events and updates in real-time as changes are made.
 */

public class AdminScene {
    // Fields of AdminScene
    TextField titleField;
    ComboBox<String> categoryComboBox;
    TextArea descriptionArea;
    DatePicker dateField;
    TextField timeField;
    TextField locationField;
    TextField capacityField;
    // Model to manage event data
    private EventDataModel eventDataModel;

    // Constructor
    public AdminScene(EventDataModel eventDataModel) {
        // Initialize the data model
        this.eventDataModel = eventDataModel;
    }

    // method to setup admin scene (GUI)
    public GridPane createAdminScene() {
        // Set up the layout grid for the admin scene
        GridPane layout = new GridPane();
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setPadding(new Insets(10, 10, 10, 10));

        // Initialize form fields for event data input
        titleField = new TextField();
        categoryComboBox = new ComboBox<>();
        categoryComboBox.getItems().addAll("Conference", "Concert", "Seminar", "Workshop");
        // Set default category
        categoryComboBox.setValue("Conference");

        descriptionArea = new TextArea();
        // Default current date
        dateField = new DatePicker(LocalDate.now());
        timeField = new TextField();
        // Placeholder text to indicate the time format
        timeField.setPromptText("HH:mm");

        locationField = new TextField();
        capacityField = new TextField();

        // Buttons for adding, editing, and deleting events
        Button addButton = new Button("Add Event");
        Button editButton = new Button("Edit Event");
        Button deleteButton = new Button("Delete Event");

        HBox buttonGroup = new HBox(10);
        buttonGroup.getChildren().addAll(addButton, editButton, deleteButton);

        // List view to display events
        ListView<String> eventListView = new ListView<>();
        eventDataModel.updateEventList(eventListView);

        // Action handlers for add event Button
        addButton.setOnAction(e -> {
            addEvent(titleField, categoryComboBox, descriptionArea, dateField, timeField, locationField, capacityField, eventListView);
        });

        // Action handlers for delete event Button
        deleteButton.setOnAction(e -> {
            deleteEvent(eventListView.getSelectionModel().getSelectedIndex(), eventListView);
        });

        // Action handlers for edit event Button
        editButton.setOnAction(e -> {
            editEvent(eventListView.getSelectionModel().getSelectedIndex(), titleField, categoryComboBox, descriptionArea, dateField, timeField, locationField, capacityField, eventListView);
        });

        // Arrange all UI components in the grid layout
        layout.addRow(0, new Label("Title:"), titleField);
        layout.addRow(1, new Label("Category:"), categoryComboBox);
        layout.addRow(2, new Label("Description:"), descriptionArea);
        layout.addRow(3, new Label("Date:"), dateField);
        layout.addRow(4, new Label("Time:"), timeField);
        layout.addRow(5, new Label("Location:"), locationField);
        layout.addRow(6, new Label("Capacity:"), capacityField);
        layout.addRow(7, new Label(""), buttonGroup);
        layout.addRow(8, new Label("Events List:"), eventListView);

        return layout;
    }

    // Method to add a new event
    private void addEvent(TextField title, ComboBox<String> category, TextArea description, DatePicker date, TextField time, TextField location, TextField capacity, ListView<String> listView) {
        // Validate inputs before adding the event
        if (title.getText().isEmpty() || description.getText().isEmpty() || time.getText().isEmpty() ||
                location.getText().isEmpty() || capacity.getText().isEmpty() || !capacity.getText().matches("\\d+")) {
            Alert error = new Alert(Alert.AlertType.ERROR, "Event not added due to invalid event data. Please ensure all fields are filled and capacity is a valid number.");
            error.showAndWait();
            return;
        }

        // Retrieve the date from DatePicker
        LocalDate eventDate = date.getValue();
        if (eventDate == null) {
            Alert error = new Alert(Alert.AlertType.ERROR, "Please select a valid date.");
            error.showAndWait();
            return;
        }

        // Parse the time using DateTimeFormatter
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime eventTime = null;
        try {
            eventTime = LocalTime.parse(time.getText(), timeFormatter);
        } catch (Exception e) {
            Alert error = new Alert(Alert.AlertType.ERROR, "Invalid time format. Please enter time in HH:mm format.");
            error.showAndWait();
            return;
        }

        // If all inputs are valid, proceed to create an object of Event
        Event event = new Event(title.getText(), category.getValue(), description.getText(), eventDate, eventTime, location.getText(), Integer.parseInt(capacity.getText()));
        // Add Event to the model
        eventDataModel.addEvent(event);
        eventDataModel.updateEventList(listView);
        clearFields();
    }

    // Method to edit an existing event. User has to elect an event from the list to edit
    private void editEvent(int index, TextField title, ComboBox<String> category, TextArea description, DatePicker date, TextField time, TextField location, TextField capacity, ListView<String> listView) {
        // If no event is selected, give error message
        if (index == -1) {
            Alert error = new Alert(Alert.AlertType.ERROR, "No event selected for editing.");
            error.showAndWait();
            return;
        }

        // Edit the event
        Event event = eventDataModel.getEvents().get(index);

        // Validate inputs first
        if (title.getText().isEmpty() || description.getText().isEmpty() || time.getText().isEmpty() ||
                location.getText().isEmpty() || capacity.getText().isEmpty() || !capacity.getText().matches("\\d+")) {
            Alert error = new Alert(Alert.AlertType.ERROR, "Invalid event data. Please ensure all fields are filled and capacity is a valid number.");
            error.showAndWait();
            return;
        }

        // Retrieve and validate the date
        LocalDate eventDate = date.getValue();
        if (eventDate == null) {
            Alert error = new Alert(Alert.AlertType.ERROR, "Please select a valid date.");
            error.showAndWait();
            return;
        }

        // Parse and validate the time
        LocalTime eventTime = null;
        try {
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            eventTime = LocalTime.parse(time.getText(), timeFormatter);
        } catch (Exception e) {
            Alert error = new Alert(Alert.AlertType.ERROR, "Invalid time format. Please enter time in HH:mm format.");
            error.showAndWait();
            return;
        }

        // update the event object
        event.updateEvent(title.getText(), category.getValue(), description.getText(), eventDate, eventTime, location.getText(), Integer.parseInt(capacity.getText()));
        // To refreshes the ListView display
        eventDataModel.updateEventList(listView);
        // Clear fields data
        clearFields();
    }

    // Method to delete an event
    private void deleteEvent(int index, ListView<String> listView) {
        if (index >= 0 && index < eventDataModel.getEvents().size()) {
            // remove the event at index
            eventDataModel.removeEvent(eventDataModel.getEvents().get(index));
            // Update the event list after removing the event
            eventDataModel.updateEventList(listView);
        }
    }


    // Helper method to clear all fields after an action is completed
    private void clearFields() {
        titleField.clear();
        categoryComboBox.setValue("Conference");
        descriptionArea.clear();
        dateField.setValue(LocalDate.now());
        timeField.clear();
        locationField.clear();
        capacityField.clear();
    }

}
