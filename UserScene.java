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

// ... other imports ...

public class UserScene {
    private Stage stage;
    private Scene userScene; // The Scene for this view
    // ... other fields ...

    // Constructor remains the same
    public UserScene(Stage stage) {
        this.stage = stage;
        events = FXCollections.observableArrayList();
        initialize();
    }

    // Initialize the Scene without showing it
    private void initialize() {
        // ... field initializations and layout setup ...

        // Instead of showing the stage, simply create the Scene and assign it
        userScene = new Scene(layout, 400, 300);
    }

    // ... other methods including setEvents, displayEventDetails, bookTickets ...

    // Method to get the Scene object for this UserScene
    public Scene getUserScene() {
        return userScene;
    }

    // ... rest of the class code ...
}
