/**********************************************
Project
Course:APD545 - Winter 2024
Last Name: Pillay
First Name:Steven David
ID:162218218
Section:ZAA
This assignment represents my own work in accordance with Seneca Academic Policy.
Signature: Steven David Pillay
Date:14-04-2024
**********************************************/
package controllers;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Window;
import model.Guest;
import model.Reservation;
import model.Database;


/**
 * Controller class for handling controls for GuestInfo.fxml.
 */
public class GuestInfoController {
	
    @FXML
    private TextField addressBox, emailBox, firstNameBox, lastNameBox, phoneBox, titleBox;
    
    @FXML
    private Text bookingIdDisplay;
    
    private int numberOfDays;
    private double priceOfRoom;
    private int numberOfRooms;
    private String typeOfRoom;
    private int bookingID;
    

    /**
     * Sets the booking data received from the previous screen.
     * @param numOfDays The number of days for the booking.
     * @param id The booking ID.
     * @param price The price of the room.
     * @param roomSuggested The number of rooms suggested.
     * @param roomType The type of room.
     */    
    public void setBookingData(String numOfDays, int id, String price, String roomSuggested, String roomType) {
        this.numberOfDays = Integer.parseInt(numOfDays);
        this.bookingID = id;
        this.priceOfRoom = Double.parseDouble(price);
        this.numberOfRooms = Integer.parseInt(roomSuggested);
        this.typeOfRoom = roomType;
        bookingIdDisplay.setText(String.valueOf(bookingID));
    }

    /**
     * Handles the confirmation of booking when the user clicks the confirm button.
     * @param event The action event triggered by the confirm button click.
     */
    @FXML
    void handleBookButton(ActionEvent event) {
    	
        Window owner = emailBox.getScene().getWindow();
        String title = titleBox.getText();
        String firstName = firstNameBox.getText();
        String lastName = lastNameBox.getText();
        String address = addressBox.getText();
        String phone = phoneBox.getText(); 
        
        String email = emailBox.getText();
        if (!isValid(email)) {
        	showAlert(AlertType.ERROR, owner, "Input Error", "Please enter a valid email address.");
        	return;
        }
        

        Guest guest = new Guest(title, firstName, lastName, address, phone, email);
        Database db = new Database();
        
        boolean roomsAvailable = db.checkRoomsAvailability(typeOfRoom, numberOfRooms);
        if (roomsAvailable) {
            
        	boolean guestInserted = db.insertGuest(guest);
            if (guestInserted) {
                
            	Reservation booking = new Reservation(bookingID, email, numberOfRooms, numberOfDays, priceOfRoom, typeOfRoom);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedDate = dateFormat.format(Calendar.getInstance().getTime());
                booking.setBookDate(formattedDate);
                boolean bookingCreated = db.insertBooking(booking);
                if (bookingCreated) {
                    
                	boolean roomsDeleted = db.deleteRooms(typeOfRoom, numberOfRooms);
                    if (roomsDeleted) {
                        
                    	showAlert(Alert.AlertType.CONFIRMATION, owner, "Booking Created", "Thank You");
                        owner.hide();
                    } else {
                        showAlert(Alert.AlertType.ERROR, owner, "Rooms Deletion Faliure", "Failed to delete rooms.");
                    }
                } else {
                    showAlert(Alert.AlertType.ERROR, owner, "Booking Creation Failure", "Failed to create booking.");
                }
            }else {
            	showAlert(Alert.AlertType.ERROR, owner, "Guest INsertion Failure", "Failed to insert Guest.");
            }
        } else {
            showAlert(Alert.AlertType.WARNING, owner, "No Rooms Available", "Rooms of type " + typeOfRoom + " are not available, Please Try Booking Another Type.");
        }
    }
    
    /**
     * Displays an alert with the specified type, title, and message.
     * @param alertType The type of alert.
     * @param window The window owner of the alert.
     * @param title The title of the alert.
     * @param message The message to be displayed in the alert.
     */
    private void showAlert(Alert.AlertType alertType, Window window, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(window);
        alert.showAndWait();
	}
    

    private boolean isValid(String email) {
    String validEmailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
    return email.matches(validEmailRegex);
    }
    
    
}
