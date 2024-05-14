# Event Booking System

## Table of Contents
 - [Description](#description)
 - [Technologies Used](#technologies-used)
 - [Design Overview](#design-overview)
 - [Usage](#usage) 


## Description 
The Event Booking System is a simple JavaFX application designed to manag registration for various events that requires booking tickets. It allows users to book tickets, view available events, and view booked tickets. The system also provides administrative features for adding, updating, or deleting course events. 

## Technologies Used 
 - **Java**: Core programming language used for building the application logic. 
 - **JavaFX**: Graphical User Interface (GUI) toolkit for Java used to create the desktop application interface. 
 - 
## Design Overview
The application is structured into several classes, each responsible for specific aspects of the application: 

 - **Main**: The entry point of the application which launches the JavaFX application.
 - **Variables**:
`
addButton
adminScene
adminTab
bookedTickets
bookedTicketsListView
bookingButton
bookingTools
buttonGroup
capacity
capacityField
category
categoryComboBox
deleteButton
description
descriptionArea
editButton
endDate
endDatePicker
endTime
endTimeField
event
eventHandler
eventListView
events
layout
location
locationField
numTickets
scene
startDate
startDatePicker
startTime
startTimeField
tabPane
ticketsRemaining
title
titleField
userScene
userTab EventHandler eventHandler
TabPane tabPane
Tab adminTab
Tab userTab
Scene scene
UserScene userScene
AdminScene adminScene
LocalDate startDate
LocalDate endDate
TextField startTimeField
TextField endTimeField
EventHandler eventHandler
ListView<Event> eventListView
ListView<String> bookedTicketsListView
ComboBox<Integer> ticketNumberComboBox
Button bookingButton
VBox layout
LocalDate endDate
LocalTime startTime
LocalTime endTime
`
 - **Methods**:
`
addEvent()
bookTickets()
bookTickets(Event event, int numTickets, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime)
clearFields()
createAdminScene()
deleteEvent(int index)
editEvent(int index)
getEndDate()
getEndTime()
getEvents()
getScene()
getStartDate()
getStartTime()
getTitle()
getTicketsRemaining()
main(String[] args)
refreshEventListView()
removeEvent()
setup()
start(Stage primaryStage)
toString()
updateBookedTicketsListView()
updateEvent()
updateEventList()
UserScene()
`
 - 
## Usage 

### Running the Application 
To run the application, you will need to have Java and JavaFX set up on your machine. Once installed, you can compile and run the application from your IDE. 

### Using the Application
 1. **Switching**: Click on the user/admin tab.
 2. **Viewing Events**: The available events appear in the top section.
 3. **Booking a Ticket**: Click on the event you wish to attend, choose the number of tickets, and press 'Book'.
 4. **Managment**: The admin can add, edit, or delete events by entering the requiered information then press the button.
