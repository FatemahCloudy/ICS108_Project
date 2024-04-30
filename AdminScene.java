package com.example.ics108_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static javafx.application.Application.launch;

public class AdminScene {
    private Stage stage;
    private Scene adminScene;
    private TextField titleField;
    private TextField categoryField;
    private TextArea descriptionArea;
    private DatePicker datePicker;
    private TextField timeField;
    private TextField locationField;
    private TextField capacityField;
    private ListView<Event> eventListView;
    private Label statusLabel;

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

        Button addButton = new Button("Add Event");
        addButton.setOnAction(event -> {
            try {
                addEvent();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });

        Button editButton = new Button("Edit Event");
        editButton.setOnAction(event -> editEvent());

        Button deleteButton = new Button("Delete Event");
        deleteButton.setOnAction(event -> deleteEvent());

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

        GridPane formLayout = new GridPane();
        formLayout.setHgap(10);
        formLayout.setVgap(10);
        formLayout.addRow(0, new Label("Title:"), titleField);
        formLayout.addRow(1, new Label("Category:"), categoryField);
        formLayout.addRow(2, new Label("Description:"), descriptionArea);
        formLayout.addRow(3, new Label("Date:"), datePicker);
        formLayout.addRow(4, new Label("Time:"), timeField);
        formLayout.addRow(5, new Label("Location:"), locationField);
        formLayout.addRow(6, new Label("Capacity:"), capacityField);

        HBox buttonLayout = new HBox(10);
        buttonLayout.getChildren().addAll(addButton, editButton, deleteButton);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(formLayout, buttonLayout, eventListView, statusLabel);

        Scene scene = new Scene(layout, 600, 400);
        stage.setScene(scene);
        adminScene = new Scene(layout, 600, 400);
    }

    private void addEvent() throws ParseException {
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

    private void editEvent() {
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

    private void deleteEvent() {
        Event selectedEvent = eventListView.getSelectionModel().getSelectedItem();
        if (selectedEvent != null) {
            events.remove(selectedEvent);
            clearFields();
            statusLabel.setText("Event deleted: " + selectedEvent.getTitle());
        }
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

    public Scene getAdminScene() {return adminScene;}
}
