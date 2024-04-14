/**********************************************
Workshop 4&5
Course:APD545 - Winter 2024
Last Name: Pillay
First Name:Steven David
ID:162218218
Section:ZAA
This assignment represents my own work in accordance with Seneca Academic Policy.
Signature
Date:30-03-2024
**********************************************/
package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AddPartController implements Initializable {

    @FXML
    private RadioButton InHouseRadioButton, OutsourcedRadioButton;
    @FXML
    private TextField IDTextField, NameTextField, InvTextField, PriceTextField, MaxTextField, MinTextField, MachineIDTextField;
    @FXML
    private Label MachineCompanyLabel;

    private static int newID = Inventory.newPartID();
    private final Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    @FXML
    public void handleInHouseRadioButton() {
        MachineCompanyLabel.setText("Machine ID");
    }
    
    public void handleOutsourcedRadioButton() {
        MachineCompanyLabel.setText("Company Name");
    }

    public boolean isNumeric(String numString) {
        if (numString == null) return true;
        return !pattern.matcher(numString).matches();
    }

    public void handleSaveButton(ActionEvent event) throws IOException {
        String partName = NameTextField.getText();
        String partInv = InvTextField.getText();
        String partPrice = PriceTextField.getText();
        String partMax = MaxTextField.getText();
        String partMin = MinTextField.getText();
        String machineIDText = MachineIDTextField.getText();

        try {
            if (isNumeric(partInv)) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Inv must be a number.");
                alert.showAndWait();
            } else if (isNumeric(partPrice)) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Price must be a Number");
                alert.showAndWait();
            } else if (Integer.parseInt(partMin) > Integer.parseInt(partMax)) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "MInimum value can't be greater than Maximum value.");
                alert.showAndWait();
            } else if (Integer.parseInt(partInv) > Integer.parseInt(partMax) || Integer.parseInt(partInv) < Integer.parseInt(partMin)) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory amount must be between minimum and maximum values.");
                alert.showAndWait();
            } else {
                int id = newID;
                int inventory = Integer.parseInt(partInv);
                double cost = Double.parseDouble(partPrice);
                int max = Integer.parseInt(partMax);
                int min = Integer.parseInt(partMin);

                if (InHouseRadioButton.isSelected()) {
                    int machineID = Integer.parseInt(machineIDText);
                    InHouse addInHousePart = new InHouse(id, partName, cost, inventory, min, max, machineID);
                    addInHousePart.setId(Inventory.newPartID());
                    Inventory.addPart(addInHousePart);
                } else if (OutsourcedRadioButton.isSelected()) {
                    Outsourced addOutsourcedPart = new Outsourced(id, partName, cost, inventory,
                            min, max, machineIDText);
                    addOutsourcedPart.setId(Inventory.newPartID());
                    Inventory.addPart(addOutsourcedPart);
                }
                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Object scene = FXMLLoader.load(getClass().getResource("/view/MainView.fxml"));
                stage.setScene(new Scene((Parent) scene));
                stage.show();
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setContentText("Error adding part. Please check inputs for errors.");
            alert.showAndWait();
        }
    }

    public void handleCancelButton(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel?(Part Will be Discarded)");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Object scene = FXMLLoader.load(getClass().getResource("/view/MainView.fxml"));
            stage.setScene(new Scene((Parent) scene));
            stage.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    IDTextField.setText(String.valueOf("Auto-generated"));
    }
}