package com.eventbookingsystem;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

 /**
 This class sets up the JavaFX application, and initializes the user interface.
 */

public class Main extends Application {
    // Tabs and tabPane to hold them
    TabPane tabPane;
    Tab adminTab, userTab;
    Scene scene;
    // Holds the event data shared across different scenes
    private EventHandler eventHandler;
    // Scene for user interactions
    private UserScene userScene;
    // Scene for admin interactions
    private AdminScene adminScene;

    // Method to start the application and sets up the primary stage including all tabs and scenes
    @Override
    public void start(Stage primaryStage) {
        // Initialize the shared data model
        eventHandler = new EventHandler();
        // Administrative scene for managing events
        adminScene = new AdminScene(eventHandler);
        // Store reference to userScene
        userScene = new UserScene(eventHandler);

        // Create a TabPane to hold tabs for different sections of the application
        tabPane = new TabPane();
        // Tab for admin functions
        adminTab = new Tab("Admin", adminScene.createAdminScene());
        // Tab for user functions
        userTab = new Tab("User", userScene.getScene());

        // Add both tabs to the tab pane
        tabPane.getTabs().addAll(adminTab, userTab);
        // Add a listener to refresh the event list view whenever the user tab is selected
        tabPane.getSelectionModel().selectedItemProperty().addListener((obs, oldTab, newTab) -> {
            if (newTab == userTab) {
                // Refresh the list view when the user tab is selected
                userScene.refreshEventListView();
            }
        });

        // Create a scene with the tab pane
        scene = new Scene(tabPane, 800, 600);
        // Set the scene on the primary stage
        primaryStage.setScene(scene);
        // Set the title of the window
        primaryStage.setTitle("Event Booking System");
        // Display the primary stage
        primaryStage.show();
    }

    // main method to launch the application
    public static void main(String[] args) {
        launch(args);
    }
}
