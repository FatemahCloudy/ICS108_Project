import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminScene {
    private List<Event> events;
    private Scanner scanner;

    public AdminScene() {
        events = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            System.out.println("Admin Scene");
            System.out.println("1. Add Event");
            System.out.println("2. Edit Event");
            System.out.println("3. Delete Event");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    addEvent();
                    break;
                case 2:
                    editEvent();
                    break;
                case 3:
                    deleteEvent();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addEvent() {
        System.out.println("Add Event");
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.print("Enter date: ");
        String date = scanner.nextLine();
        System.out.print("Enter time: ");
        String time = scanner.nextLine();
        System.out.print("Enter location: ");
        String location = scanner.nextLine();
        System.out.print("Enter capacity: ");
        int capacity = scanner.nextInt();

        Event event = new Event(title, category, description, date, time, location, capacity);
        events.add(event);
        System.out.println("Event added successfully!");
    }

    private void editEvent() {
        System.out.println("Edit Event");
        System.out.print("Enter the index of the event to edit: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        if (index >= 0 && index < events.size()) {
            Event event = events.get(index);
            System.out.print("Enter new title (current: " + event.getTitle() + "): ");
            String title = scanner.nextLine();
            System.out.print("Enter new category (current: " + event.getCategory() + "): ");
            String category = scanner.nextLine();
            System.out.print("Enter new description (current: " + event.getDescription() + "): ");
            String description = scanner.nextLine();
            System.out.print("Enter new date (current: " + event.getDate() + "): ");
            String date = scanner.nextLine();
            System.out.print("Enter new time (current: " + event.getTime() + "): ");
            String time = scanner.nextLine();
            System.out.print("Enter new location (current: " + event.getLocation() + "): ");
            String location = scanner.nextLine();
            System.out.print("Enter new capacity (current: " + event.getCapacity() + "): ");
            int capacity = scanner.nextInt();

            event.setTitle(title);
            event.setCategory(category);
            event.setDescription(description);
            event.setDate(date);
            event.setTime(time);
            event.setLocation(location);
            event.setCapacity(capacity);

            System.out.println("Event updated successfully!");
        } else {
            System.out.println("Invalid event index.");
        }
    }

    private void deleteEvent() {
        System.out.println("Delete Event");
        System.out.print("Enter the index of the event to delete: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        if (index >= 0 && index < events.size()) {
            events.remove(index);
            System.out.println("Event deleted successfully!");
        } else {
            System.out.println("Invalid event index.");
        }
    }
}
