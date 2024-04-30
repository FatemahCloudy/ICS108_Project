package com.example.ics108_project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    private Stage stage;
    private Scene adminScene;
    private Scene userScene;

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        AdminScene adminSceneUI = new AdminScene(this.stage);
        UserScene userSceneUI = new UserScene(this.stage);

        // Assuming these methods return the Scene objects for each UI
        adminScene = adminSceneUI.getScene();
        userScene = userSceneUI.getScene();

        Button switchToUserScene = new Button("Switch to User Scene");
        switchToUserScene.setOnAction(e -> stage.setScene(userScene));

        Button switchToAdminScene = new Button("Switch to Admin Scene");
        switchToAdminScene.setOnAction(e -> stage.setScene(adminScene));

        StackPane adminLayout = new StackPane();
        adminLayout.getChildren().add(switchToUserScene);
        adminScene.setRoot(adminLayout);

        StackPane userLayout = new StackPane();
        userLayout.getChildren().add(switchToAdminScene);
        userScene.setRoot(userLayout);

        // Switch between admin and user scenes
        primaryStage.setScene(adminScene);
        primaryStage.setTitle("Event Booking System");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
