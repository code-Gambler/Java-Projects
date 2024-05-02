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

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Window;
import model.Reservation;
import model.Database;

/**
 * Controller class for handling all controls for the file: Billing.fxml
 */
public class BillingController {

    @FXML
    private TextField bookingIDField, discountField;

    @FXML
    private Text finalAmount, guestName, numOfRoomsBooked, ratePerNight, typeOfRooms, numOfDaysBooked;

    /**
     * Calculates the final amount after applying discount.
     * @param event The action event triggered by the calculate button click.
     */
    @FXML
    void handleCalculateButton(ActionEvent event) {
        String discountPercent = discountField.getText();
        
        if (discountPercent.isEmpty()) {
            showAlert(AlertType.ERROR, discountField.getScene().getWindow(), "Missing Values", "Please a Value for Discount.");
            return;
        }
        
        double discountNum = Double.parseDouble(discountPercent);
        if (discountNum < 0 || discountNum > 25) {
            showAlert(AlertType.ERROR, discountField.getScene().getWindow(), "Invalid Discount", "Discount Should not be More than 25 percent");
            return;
        }
        
        double ratePerNightNum = Double.parseDouble(ratePerNight.getText());
        int numOfDaysNum = Integer.parseInt(numOfDaysBooked.getText());
        double totalAmountNum = ratePerNightNum * numOfDaysNum;
        double discountAmountNum = (discountNum / 100) * totalAmountNum;
        double finalAmountValueNum = totalAmountNum - discountAmountNum;
        
        finalAmount.setText("Final Amount : C$" + String.valueOf(finalAmountValueNum));
    }


    /**
     * Fetches bill details for the provided booking ID.
     * @param event The action event triggered by the fetch bill button click.
     */
    @FXML
    void handlePullDataButton(ActionEvent event) {
    	Window owner = bookingIDField.getScene().getWindow();
    	
    	int bookingId = Integer.parseInt(bookingIDField.getText());
    	
    	Database db = new Database();
        Reservation fetchedBooking = db.fetchBookingDetails(bookingId);

        if (fetchedBooking != null) {
            guestName.setText(fetchedBooking.getGuestEmail());
            numOfRoomsBooked.setText(String.valueOf(fetchedBooking.getNumOfRoomsBooked()));
            ratePerNight.setText(String.valueOf(fetchedBooking.getRatePerNight()));
            typeOfRooms.setText(fetchedBooking.getRoomType());
            numOfDaysBooked.setText(String.valueOf(fetchedBooking.getNumOfDaysBooked()));
        } else {
        	 showAlert(AlertType.ERROR, owner, "Booking Not Found", "Please enter a valid booking ID.");
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
    

}

