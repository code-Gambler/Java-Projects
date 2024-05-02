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
import model.Room;
import model.Database;

/**
 * Controller class for the AvailableRooms.fxml.
 */
public class AvailableRoomsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text numOfRooms;
    
    @FXML
    private TableView<Room> roomTable;

    /**
     * Initializes the controller.
     */
    @FXML
    void initialize() {
        assert numOfRooms != null : "fx:id=\"numOfRooms\" was not injected: check your FXML file 'availableRooms.fxml'.";
        
        TableColumn<Room, Integer> roomNumberCol = new TableColumn<>("Room Number");
        roomNumberCol.setCellValueFactory(new PropertyValueFactory<>("roomID"));

        TableColumn<Room, String> roomTypeCol = new TableColumn<>("Room Type");
        roomTypeCol.setCellValueFactory(new PropertyValueFactory<>("roomType"));

        TableColumn<Room, Double> roomPriceCol = new TableColumn<>("Room Price");
        roomPriceCol.setCellValueFactory(new PropertyValueFactory<>("rate"));

        roomTable.getColumns().addAll(roomNumberCol, roomTypeCol, roomPriceCol);
        
        Database db = new Database();
        
        List<Room> rooms = db.fetchRooms();
        int availableRoomsCount = db.countAvailableRooms();

        roomTable.getItems().addAll(rooms);
        numOfRooms.setText(String.valueOf(availableRoomsCount));
    }

}
