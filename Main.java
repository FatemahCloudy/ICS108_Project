package com.example.ics108_project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    private AdminScene adminScene;
    private UserScene userScene;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        adminScene = new AdminScene(primaryStage);
        userScene = new UserScene(primaryStage);

        // Create a menu bar
        MenuBar menuBar = new MenuBar();

        // Create menus
        Menu adminMenu = new Menu("Admin");
        MenuItem adminSceneItem = new MenuItem("Manage Events");
        adminSceneItem.setOnAction(e -> switchToAdminScene());

        Menu userMenu = new Menu("User");
        MenuItem userSceneItem = new MenuItem("Book Events");
        userSceneItem.setOnAction(e -> switchToUserScene());

        // Add menu items to menus
        adminMenu.getItems().add(adminSceneItem);
        userMenu.getItems().add(userSceneItem);

        // Add menus to the menu bar
        menuBar.getMenus().addAll(adminMenu, userMenu);

        // Create a BorderPane as the root for the scene
        BorderPane root = new BorderPane();
        root.setTop(menuBar);

        // Set an initial scene
        root.setCenter(userScene.getUserScene().getRoot());

        // Create the main scene
        Scene scene = new Scene(root, 800, 600);

        // Set the scene to the stage and show the stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Event Booking System");
        primaryStage.show();
    }

    private void switchToAdminScene() {
        primaryStage.getScene().setRoot(adminScene.getAdminScene().getRoot());
        primaryStage.setTitle("Admin - Manage Events");
    }

    private void switchToUserScene() {
        primaryStage.getScene().setRoot(userScene.getUserScene().getRoot());
        primaryStage.setTitle("User - Book Events");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
