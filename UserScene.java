public class UserScene {
    // ... [rest of your variables]

    public UserScene(Stage stage) {
        this.stage = stage;
        events = FXCollections.observableArrayList();
        initialize();
    }

    private void initialize() {
        // ... [rest of your initialization code]

        // Create a layout for the user scene
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(eventListView, formLayout);

        // Create the scene but don't set it to the stage here
        userScene = new Scene(layout, 400, 300);
    }

    // ... [rest of your methods]

    public Scene getUserScene() {
        return userScene;
    }
}
