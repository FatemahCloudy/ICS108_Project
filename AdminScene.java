public class AdminScene {
    // ... [rest of your variables]

    public AdminScene(Stage stage) {
        this.stage = stage;
        events = FXCollections.observableArrayList();
        initialize();
    }

    private void initialize() {
        // ... [rest of your initialization code]

        // Create a layout for the admin scene
        VBox layout = new VBox(10);
        layout.getChildren().addAll(formLayout, buttonLayout, eventListView, statusLabel);

        // Create the scene but don't set it to the stage here
        adminScene = new Scene(layout, 600, 400);
    }

    // ... [rest of your methods]

    public Scene getAdminScene() {
        return adminScene;
    }
}
