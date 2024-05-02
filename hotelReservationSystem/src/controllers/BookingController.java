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

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * Controller class for handling control for file Booking.fxml.
 */
public class BookingController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField numOfDaysField, numOfGuestsField;

    @FXML
    private Text priceDisplay, roomSuggested;

    @FXML
    private ChoiceBox<String> roomTypeSelector;
    
    private Random random = new Random();

    /**
     * Generates a random booking ID.
     * @return The generated booking ID.
     */
    private int generateBookingId() {
        return 1000 + random.nextInt(90000);
    }


    /**
     * Handles the action when the user proceeds to enter guest information.
     * @param event The action event triggered by the proceed button click.
     */
    @FXML
    void handleContinueButton(ActionEvent event) {
        try {
        	Window owner = numOfDaysField.getScene().getWindow();
            FXMLLoader guestInfoWindow = new FXMLLoader(getClass().getResource("/view/GuestInfo.fxml"));
            Parent guestInfoRoot = guestInfoWindow.load();
            GuestInfoController guestInfoController = guestInfoWindow.getController();
            
            int bookingID = generateBookingId();
            guestInfoController.setBookingData(numOfDaysField.getText(), bookingID,
            priceDisplay.getText(), roomSuggested.getText(), roomTypeSelector.getValue());
            Stage guestInfoStage = new Stage();
            guestInfoStage.setTitle("Guest Information");
            guestInfoStage.setScene(new Scene(guestInfoRoot));
            guestInfoStage.show();
            owner.hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the room suggestion and price based on the selected room type and number of guests.
     */
    private void updateRoomSuggestionAndPrice() {
    	String numOfGuestsText = numOfGuestsField.getText();
        int numOfGuests = Integer.parseInt(numOfGuestsField.getText());
        
        if (numOfGuestsText.isEmpty()) {
            return;
        }

        String selectedRoomType = roomTypeSelector.getValue();
        int numOfRooms = 0;
        double rateOfRoom = 0;

        if ("Single Room".equals(selectedRoomType)) {
            numOfRooms = (numOfGuests + 1) / 2;
            rateOfRoom = 2000;
        } else if ("Double Room".equals(selectedRoomType)) {
            numOfRooms = (numOfGuests + 3) / 4;
            rateOfRoom = 4000;
        } else if ("Deluxe Room".equals(selectedRoomType)) {
            numOfRooms = (numOfGuests + 5) / 6;
            rateOfRoom = 6000;
        } else if ("Penthouse".equals(selectedRoomType)) {
            numOfRooms = (numOfGuests + 7) / 8;
            rateOfRoom = 8000;
        }

        roomSuggested.setText(String.valueOf(numOfRooms));

        double totalPrice = numOfRooms * rateOfRoom;
        priceDisplay.setText(String.valueOf(totalPrice));
    }
    
    /**
     * Initializes the controller.
     */
    @FXML
    void initialize() {
    	assert numOfDaysField != null : "fx:id=\"numOfDaysField\" was not injected: check your FXML file 'bookRoom.fxml'.";
    	assert numOfGuestsField != null : "fx:id=\"numOfGuestsField\" was not injected: check your FXML file 'bookRoom.fxml'.";
    	assert priceDisplay != null : "fx:id=\"priceDisplay\" was not injected: check your FXML file 'bookRoom.fxml'.";
    	assert roomSuggested != null : "fx:id=\"roomSuggested\" was not injected: check your FXML file 'bookRoom.fxml'.";
    	assert roomTypeSelector != null : "fx:id=\"roomTypeSelector\" was not injected: check your FXML file 'bookRoom.fxml'.";
    	
    	
    	ObservableList<String> roomTypes = FXCollections.observableArrayList(
    			"Single Room", "Double Room", "Deluxe Room", "Penthouse"
    			);
    	roomTypeSelector.setItems(roomTypes);
    	roomTypeSelector.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
    		updateRoomSuggestionAndPrice();
    	});
    }
    
    


}
