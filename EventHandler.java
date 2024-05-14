package com.eventbookingsystem;

import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * The methods for maintaining an events list in an event booking system are handled by this class.
 * It offers ways for the administrator to add, modify, and remove events.
 */

public class EventHandler {
    private List<Event> events;

    // Methods
    public void addEvent(Event event) {
        events.add(event);
    }

    public void removeEvent(Event event) {
        events.remove(event);
    }

    public void updateEventList(ListView<String> listView) {
        listView.getItems().clear();
        for (Event event : events)
            listView.getItems().add(event.toString());
    }

    public List<Event> getEvents() {
        return events;
    }

    // Constructor
    public EventHandler() {
        this.events = new ArrayList<>();
    }

}
