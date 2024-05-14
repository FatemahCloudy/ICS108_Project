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
 - **Variables**: `TabPane tabPane` `Tab adminTab, userTab` `Scene scene` `EventHandler eventHandler` `UserScene userScene` `AdminScene adminScene` 
 - **Methods**: `start(Stage primaryStage)` `main(String[] args)` 
 - 
## Usage 

### Running the Application 
To run the application, you will need to have Java and JavaFX set up on your machine. Once installed, you can compile and run the application from your IDE. 

### Using the Application
 1. **Switching**: Click on the user/admin tab.
 2. **Viewing Events**: The available events appear in the top section.
 3. **Booking a Ticket**: Click on the event you wish to attend, choose the number of tickets, and press 'Book'.
 4. **Managment**: The admin can add, edit, or delete events by entering the requiered information then press the button.
