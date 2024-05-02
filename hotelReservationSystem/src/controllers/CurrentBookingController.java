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

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import model.Reservation;
import model.Database;

/**
 * Controller class for the CurrentBooking.fxml.
 */
public class CurrentBookingController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text numOfBookings;
    
    @FXML
    private TableView<Reservation> bookingsTable;

    /**
     * Initializes the controller.
     */
    @FXML
    void initialize() {
        assert numOfBookings != null : "fx:id=\"numOfRooms\" was not injected: check your FXML file 'availableRooms.fxml'.";
        
        TableColumn<Reservation, Integer> idColumn = new TableColumn<>("Booking #");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("bookID"));

        TableColumn<Reservation, String> customerCol = new TableColumn<>("Customer Email");
        customerCol.setCellValueFactory(new PropertyValueFactory<>("guestEmail"));

        TableColumn<Reservation, String> roomTypeCol = new TableColumn<>("Room Type");
        roomTypeCol.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        
        TableColumn<Reservation, Integer> numOfRoomsCol = new TableColumn<>("No of Rooms");
        numOfRoomsCol.setCellValueFactory(new PropertyValueFactory<>("numOfRoomsBooked"));
        
        TableColumn<Reservation, Integer> numOfDaysCol = new TableColumn<>("No of Days");
        numOfDaysCol.setCellValueFactory(new PropertyValueFactory<>("numOfDaysBooked"));

        bookingsTable.getColumns().addAll(idColumn,customerCol, roomTypeCol, numOfRoomsCol,numOfDaysCol);
        
        Database db = new Database();
        
        List<Reservation> bookings = db.fetchBookings();
        int currentBookingsCount = db.countBookings();

        bookingsTable.getItems().addAll(bookings);
        numOfBookings.setText(String.valueOf(currentBookingsCount));
    }

}
