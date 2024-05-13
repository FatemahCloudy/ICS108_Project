package com.eventbookingsystem;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AdminScene {
    // Fields of AdminScene
    TextField titleField;
    ComboBox<String> categoryComboBox;
    TextArea descriptionArea;
    DatePicker startDatePicker, endDatePicker;
    TextField startTimeField, endTimeField;
    TextField locationField;
    TextField capacityField;
    // Model to manage event data
    private EventDataModel eventDataModel;

    // Constructor
    public AdminScene(EventDataModel eventDataModel) {
        this.eventDataModel = eventDataModel;
    }

    // Method to setup admin scene (GUI)
    public GridPane createAdminScene() {
        GridPane layout = new GridPane();
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setPadding(new Insets(10, 10, 10, 10));

        titleField = new TextField();
        categoryComboBox = new ComboBox<>();
        categoryComboBox.getItems().addAll("Conference", "Concert", "Seminar", "Workshop");
        categoryComboBox.setValue("Conference");
        descriptionArea = new TextArea();

        startDatePicker = new DatePicker(LocalDate.now());
        endDatePicker = new DatePicker(LocalDate.now());
        startTimeField = new TextField();
        endTimeField = new TextField();
        startTimeField.setPromptText("HH:mm");
        endTimeField.setPromptText("HH:mm");

        locationField = new TextField();
        capacityField = new TextField();
        Button addButton = new Button("Add Event");
        Button editButton = new Button("Edit Event");
        Button deleteButton = new Button("Delete Event");
        HBox buttonGroup = new HBox(10);
        buttonGroup.getChildren().addAll(addButton, editButton, deleteButton);

        ListView<String> eventListView = new ListView<>();
        eventDataModel.updateEventList(eventListView);

        addButton.setOnAction(e -> addEvent());
        deleteButton.setOnAction(e -> deleteEvent(eventListView.getSelectionModel().getSelectedIndex(), eventListView));
        editButton.setOnAction(e -> editEvent(eventListView.getSelectionModel().getSelectedIndex()));

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
    // Validate inputs before adding the event
    if (titleField.getText().isEmpty() || descriptionArea.getText().isEmpty() || locationField.getText().isEmpty() ||
            capacityField.getText().isEmpty() || !capacityField.getText().matches("\\d+") ||
            startTimeField.getText().isEmpty() || endTimeField.getText().isEmpty()) {
        Alert error = new Alert(Alert.AlertType.ERROR, "Please ensure all fields are filled and capacity is a valid number.");
        error.showAndWait();
        return;
    }

    LocalDate startDate = startDatePicker.getValue();
    LocalDate endDate = endDatePicker.getValue();
    if (startDate == null || endDate == null || startDate.isAfter(endDate)) {
        Alert error = new Alert(Alert.AlertType.ERROR, "Please select valid start and end dates.");
        error.showAndWait();
        return;
    }

    LocalTime startTime = null;
    LocalTime endTime = null;
    try {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        startTime = LocalTime.parse(startTimeField.getText(), timeFormatter);
        endTime = LocalTime.parse(endTimeField.getText(), timeFormatter);
        if (endTime.isBefore(startTime)) {
            throw new IllegalArgumentException("End time cannot be before start time.");
        }
    } catch (Exception e) {
        Alert error = new Alert(Alert.AlertType.ERROR, "Invalid time format. Please enter time in HH:mm format.");
        error.showAndWait();
        return;
    }

    Event event = new Event(titleField.getText(), categoryComboBox.getValue(), descriptionArea.getText(),
                            startDate, startTime, endDate, endTime, locationField.getText(),
                            Integer.parseInt(capacityField.getText()));
    eventDataModel.addEvent(event);
    eventDataModel.updateEventList(eventListView);
    clearFields();
}private void editEvent(int index) {
    if (index == -1) {
        Alert error = new Alert(Alert.AlertType.ERROR, "No event selected for editing.");
        error.showAndWait();
        return;
    }

    Event event = eventDataModel.getEvents().get(index);
    if (titleField.getText().isEmpty() || descriptionArea.getText().isEmpty() || locationField.getText().isEmpty() ||
            capacityField.getText().isEmpty() || !capacityField.getText().matches("\\d+") ||
            startTimeField.getText().isEmpty() || endTimeField.getText().isEmpty()) {
        Alert error = new Alert(Alert.AlertType.ERROR, "Invalid event data. Please ensure all fields are filled and capacity is a valid number.");
        error.showAndWait();
        return;
    }

    LocalDate startDate = startDatePicker.getValue();
    LocalDate endDate = endDatePicker.getValue();
    if (startDate == null || endDate == null || startDate.isAfter(endDate)) {
        Alert error = new Alert(Alert.AlertType.ERROR, "Please select valid start and end dates.");
        error.showAndWait();
        return;
    }

    LocalTime startTime = null;
    LocalTime endTime = null;
    try {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        startTime = LocalTime.parse(startTimeField.getText(), timeFormatter);
        endTime = LocalTime.parse(endTimeField.getText(), timeFormatter);
        if (endTime.isBefore(startTime)) {
            throw new IllegalArgumentException("End time cannot be before start time.");
        }
    } catch (Exception e) {
        Alert error = new Alert(Alert.AlertType.ERROR, "Invalid time format. Please enter time in HH:mm format.");
        error.showAndWait();
        return;
    }

    event.updateEvent(titleField.getText(), categoryComboBox.getValue(), descriptionArea.getText(),
                      startDate, startTime, endDate, endTime, locationField.getText(),
                      Integer.parseInt(capacityField.getText()));
    eventDataModel.updateEventList(eventListView);
    clearFields();
}private void clearFields() {
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
