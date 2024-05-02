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
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Controller class for MainMenu.fxml.
 */
public class MainMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private Button exitBtn;

    /**
     * Opens the window for checking available rooms.
     * @param event The action event triggered by the button click.
     */
    @FXML
    void handleAvailableRoomsButton(ActionEvent event) {
        try {
            FXMLLoader availableRoomsLoader = new FXMLLoader(getClass().getResource("/view/AvailableRooms.fxml"));
            Parent availableRoomsRoot = availableRoomsLoader.load();

            Stage availableRoomsStage = new Stage();
            availableRoomsStage.setTitle("Available Rooms");
            availableRoomsStage.setScene(new Scene(availableRoomsRoot));
            availableRoomsStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens the window for billing service.
     * @param event The action event triggered by the button click.
     */
    @FXML
    void handleBillingButton(ActionEvent event) {
        try {
            FXMLLoader billingLoader = new FXMLLoader(getClass().getResource("/view/Billing.fxml"));
            Parent billingRoot = billingLoader.load();

            Stage billingStage = new Stage();
            billingStage.setTitle("Billing Service");
            billingStage.setScene(new Scene(billingRoot));
            billingStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens the window for booking a room.
     * @param event The action event triggered by the button click.
     */
    @FXML
    void handleBookButton(ActionEvent event) {
        try {
            FXMLLoader bookingLoader = new FXMLLoader(getClass().getResource("/view/Booking.fxml"));
            Parent bookingRoot = bookingLoader.load();

            Stage bookingStage = new Stage();
            bookingStage.setTitle("Book A Room");
            bookingStage.setScene(new Scene(bookingRoot));
            bookingStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens the window for viewing current bookings.
     * @param event The action event triggered by the button click.
     */
    @FXML
    void handleCurrentBookingButton(ActionEvent event) {
        try {
            FXMLLoader currentBookingloader = new FXMLLoader(getClass().getResource("/view/CurrentBooking.fxml"));
            Parent currentBookingRoot = currentBookingloader.load();

            Stage currentBookingStage = new Stage();
            currentBookingStage.setTitle("Available Bookings");
            currentBookingStage.setScene(new Scene(currentBookingRoot));
            currentBookingStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Exits the application.
     * @param event The action event triggered by the button click.
     */
    @FXML
    void onExit(ActionEvent event) {
    	Platform.exit();
    }

    @FXML
    void initialize() {

    }

}
