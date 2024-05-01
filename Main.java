package com.example.ics108_project;

import javafx.application.Application;
import javafx.stage.Stage;



public class Main extends Application {

    private Stage primaryStage;
    private AdminScene adminScene;
    private UserScene userScene;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        // Initialize both scenes
        adminScene = new AdminScene(primaryStage);
        userScene = new UserScene(primaryStage);

        // Set initial scene (user scene)
        primaryStage.setScene(userScene.getUserScene());
        primaryStage.setTitle("Event Booking");

        // Show the primary stage
        primaryStage.show();
    }

    // Method to switch to AdminScene
    public void switchToAdminScene() {
        primaryStage.setScene(adminScene.getAdminScene());
        primaryStage.setTitle("Event Management");
    }

    // Method to switch to UserScene
    public void switchToUserScene() {
        primaryStage.setScene(userScene.getUserScene());
        primaryStage.setTitle("Event Booking");
    }

    public static void main(String[] args) {launch(args);}
}
