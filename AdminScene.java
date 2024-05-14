package com.eventbookingsystem;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 The booking system's admin interface, which allows the admin to add, edit, and remove events,
 is provided by the AdminScene class.

 Features:
 - Add a brand-new occasion.
 - Modify a previous event.
 - Eliminate a previous event.

 */

public class AdminScene {
    TextField titleField;
    ComboBox<String> categoryComboBox;
    TextArea descriptionArea;
    DatePicker startDatePicker, endDatePicker;
    TextField startTimeField, endTimeField;
    TextField locationField, capacityField;
    Button addButton, editButton, deleteButton;
    HBox buttonGroup;
    ListView<String> eventListView = new ListView<>();
    EventHandler eventHandler;
    LocalDate startDate, endDate;
    LocalTime startTime, endTime;

    // Constructor
    public AdminScene(EventHandler eventDataModel) {
        this.eventHandler = eventDataModel;
    }

    // Methods

    public GridPane createAdminScene() {
        GridPane layout = new GridPane(); //Create the gridPane
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setPadding(new Insets(10, 10, 10, 10));

        // initialize fields and components to enter the event's details
        titleField = new TextField(); //title of event
        categoryComboBox = new ComboBox<>(); //category of event
        categoryComboBox.getItems().addAll("Conference", "Concert", "Seminar", "Webinar", "Workshop");
        categoryComboBox.setValue("Conference"); //default category
        descriptionArea = new TextArea(); // A description can include additional information about the event

        // the start and end date and time
        startDatePicker = new DatePicker(LocalDate.now());
        endDatePicker = new DatePicker(LocalDate.now());
        startTimeField = new TextField();
        endTimeField = new TextField();
        startTimeField.setPromptText("HH:mm"); // the time is in the format HH:mm, for example "12:30" .
        endTimeField.setPromptText("HH:mm");

        locationField = new TextField();
        capacityField = new TextField();

        //creat buttons and add them to the HBox
        addButton = new Button("Add Event");
        editButton = new Button("Edit Event");
        deleteButton = new Button("Delete Event");
        buttonGroup = new HBox(10);
        buttonGroup.getChildren().addAll(addButton, editButton, deleteButton);

        eventListView = new ListView<>();
        eventHandler.updateEventList(eventListView);

        // all buttons are here
        addButton.setOnAction(e -> addEvent());
        deleteButton.setOnAction(e -> deleteEvent(eventListView.getSelectionModel().getSelectedIndex()));
        editButton.setOnAction(e -> editEvent(eventListView.getSelectionModel().getSelectedIndex()));

        // add everything to the gridPane
        layout.addRow(0, new Label("Title:"), titleField);
        layout.addRow(1, new Label("Category:"), categoryComboBox);
        layout.addRow(2, new Label("Description:"), descriptionArea);
        layout.addRow(3, new Label("Start Date:"), startDatePicker);
        layout.addRow(4, new Label("End Date:"), endDatePicker);
        layout.addRow(5, new Label("Start Time:"), startTimeField);
        layout.addRow(6, new Label("End Time:"), endTimeField);
        layout.addRow(7, new Label("Location:"), locationField);
        layout.addRow(8, new Label("Capacity:"), capacityField);
        layout.addRow(9, new Label(""), buttonGroup);
        layout.addRow(10, new Label("Events List:"), eventListView);

        return layout;
    }

    private void addEvent() {
        // check the inputs before adding the event
        if (titleField.getText().isEmpty() || descriptionArea.getText().isEmpty() ||
                locationField.getText().isEmpty() || capacityField.getText().isEmpty() ||
                !capacityField.getText().matches("\\d+") ||
                startTimeField.getText().isEmpty() || endTimeField.getText().isEmpty()) {

            Alert error = new Alert(Alert.AlertType.ERROR,
                    "Please make sure you filled all the fields and that the capacity is a valid number.");
            error.showAndWait();
            return;
        }

        startDate = startDatePicker.getValue();
        endDate = endDatePicker.getValue();

        if (startDate == null || endDate == null || startDate.isAfter(endDate)) {
            Alert error = new Alert(Alert.AlertType.ERROR, "Please select valid start and end dates.");
            error.showAndWait();
            return;
        }

        try {
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            startTime = LocalTime.parse(startTimeField.getText(), timeFormatter);
            endTime = LocalTime.parse(endTimeField.getText(), timeFormatter);
        } catch (Exception e) {
            Alert error = new Alert(Alert.AlertType.ERROR,
                    "The time need to be in HH:mm format.");
            error.showAndWait();
            return;
        }

        Event event = new Event(titleField.getText(), categoryComboBox.getValue(), descriptionArea.getText(),
                startDate, startTime, endDate, endTime, locationField.getText(),
                Integer.parseInt(capacityField.getText()));
        eventHandler.addEvent(event);
        eventHandler.updateEventList(eventListView);
        clearFields();
    }

    private void editEvent(int index) {
        if (index == -1) {
            Alert error = new Alert(Alert.AlertType.ERROR, "Please select an event for editing.");
            error.showAndWait();
            return;
        }

        Event event = eventHandler.getEvents().get(index);
        if (titleField.getText().isEmpty() || descriptionArea.getText().isEmpty() ||
                locationField.getText().isEmpty() ||
                capacityField.getText().isEmpty() || !capacityField.getText().matches("\\d+") ||
                startTimeField.getText().isEmpty() || endTimeField.getText().isEmpty()) {
            Alert error = new Alert(Alert.AlertType.ERROR,
                    "Please make sure you filled all the fields and that the capacity is a valid number.");
            error.showAndWait();
            return;
        }

        startDate = startDatePicker.getValue();
        endDate = endDatePicker.getValue();
        if (startDate == null || endDate == null || startDate.isAfter(endDate)) {
            Alert error = new Alert(Alert.AlertType.ERROR, "Please select valid start and end dates.");
            error.showAndWait();
            return;
        }

        try {
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            String startTimeInput = startTimeField.getText();
            String endTimeInput = endTimeField.getText();

            startTime = LocalTime.parse(startTimeInput, timeFormatter);
            endTime = LocalTime.parse(endTimeInput, timeFormatter);

        } catch (DateTimeParseException e) {
            Alert error = new Alert(Alert.AlertType.ERROR,
                    "The time need to be in HH:mm format.");
            error.showAndWait();
            return;
        }

        // update the event
        event.updateEvent(titleField.getText(), categoryComboBox.getValue(), descriptionArea.getText(),
                startDate, startTime, endDate, endTime, locationField.getText(),
                Integer.parseInt(capacityField.getText()));

        // update the list
        eventHandler.updateEventList(eventListView);

        // clear fields
        clearFields();
    }
    private void deleteEvent(int index) {
        if (index == -1) {
            Alert error = new Alert(Alert.AlertType.ERROR, "Please select an event to delete it.");
            error.showAndWait();
        }
        Event event = eventHandler.getEvents().get(index);
        eventHandler.removeEvent(event);
        eventHandler.updateEventList(eventListView);
        clearFields();
    }

    private void clearFields() {
        titleField.clear();
        categoryComboBox.setValue("Conference");
        descriptionArea.clear();
        startDatePicker.setValue(LocalDate.now());
        endDatePicker.setValue(LocalDate.now());
        startTimeField.clear();
        endTimeField.clear();
        locationField.clear();
        capacityField.clear();
    }
}
