package com.example.ics108_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// ... other imports ...

public class AdminScene {
    private Stage stage;
    private Scene adminScene; // The Scene for this view
    // ... other fields ...

    // Constructor remains the same
    public AdminScene(Stage stage) {
        this.stage = stage;
        events = FXCollections.observableArrayList();
        initialize();
    }

    // Initialize the Scene without showing it
    private void initialize() {
        // ... field initializations and layout setup ...

        // Instead of showing the stage, simply create the Scene and assign it
        adminScene = new Scene(layout, 600, 400);
    }

    // ... other methods ...

    // Method to get the Scene object for this AdminScene
    public Scene getAdminScene() {
        return adminScene;
    }

    // ... rest of the class code ...
}
