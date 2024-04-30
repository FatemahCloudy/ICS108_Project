package com.example.ics108_project;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage primaryStage;
    private AdminScene adminScene; // Assuming you have a similar setup for AdminScene
    private UserScene userScene;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        
        // Initialize both scenes
        adminScene = new AdminScene(primaryStage);
        userScene = new UserScene(primaryStage);

        // Set initial scene, for example, the UserScene
        primaryStage.setScene(userScene.getUserScene());
        primaryStage.setTitle("Event Booking - User");

        // Show the primary stage
        primaryStage.show();
    }

    // Method to switch to AdminScene
    public void switchToAdminScene() {
        primaryStage.setScene(adminScene.getAdminScene());
        primaryStage.setTitle("Event Management - Admin");
    }

    // Method to switch to UserScene
    public void switchToUserScene() {
        primaryStage.setScene(userScene.getUserScene());
        primaryStage.setTitle("Event Booking - User");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
