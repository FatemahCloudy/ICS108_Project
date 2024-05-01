package com.example.ics108_project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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

        // Create a button to switch to the admin scene
        Button switchToAdminButton = new Button("Switch to Admin");
        switchToAdminButton.setOnAction(event -> switchToAdminScene());

        // Create a button to switch to the user scene
        Button switchToUserButton = new Button("Switch to User");
        switchToUserButton.setOnAction(event -> switchToUserScene());

        // Create a layout for the buttons
        HBox buttonLayout = new HBox(10);
        buttonLayout.getChildren().addAll(switchToAdminButton, switchToUserButton);
        buttonLayout.setAlignment(Pos.CENTER);

        // Create a layout for the main content
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(buttonLayout);

        // Create the main scene
        Scene mainScene = new Scene(layout, 400, 300);
        primaryStage.setScene(mainScene);

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

    public static void main(String[] args) {
        launch(args);
    }
}
