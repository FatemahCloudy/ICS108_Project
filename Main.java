package com.example.ics108_project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.EventHandler;

public class Main extends Application {

    Stage primaryStage;
    AdminScene adminScene;
    UserScene userScene;
    Button switchToUserButton, switchToAdminButton;
    HBox buttonLayout;
    VBox layout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        // Initialize both scenes
        adminScene = new AdminScene(primaryStage);
        userScene = new UserScene(primaryStage);

        // Set initial scene (user scene)
        primaryStage.setScene(userScene.getScene());
        primaryStage.setTitle("Event Booking");

        // Create a button to switch to the admin scene
        switchToAdminButton = new Button("Switch to Admin");
        switchToAdminButton.setOnAction(new SwitchHandler());

        // Create a button to switch to the user scene
        switchToUserButton = new Button("Switch to User");
        switchToUserButton.setOnAction(new SwitchHandler());

        // Create a layout for the buttons
        buttonLayout = new HBox(10);
        buttonLayout.getChildren().addAll(switchToAdminButton, switchToUserButton);
        buttonLayout.setAlignment(Pos.CENTER);

        // Create a layout for the main content
        layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(buttonLayout);

        // Create the main scene and set the layout as the root
        Scene mainScene = new Scene(layout, 400, 300);
        primaryStage.setScene(mainScene);

        // Show the primary stage
        primaryStage.show();
    }

    public static void main(String[] args) {launch(args);}

    class SwitchHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == switchToUserButton) {
                primaryStage.setScene(userScene.getScene());
                primaryStage.setTitle("Event Booking");
            } else if (event.getSource() == switchToAdminButton) {
                primaryStage.setScene(adminScene.getScene());
                primaryStage.setTitle("Event Management");
            }

        }
    }
}
