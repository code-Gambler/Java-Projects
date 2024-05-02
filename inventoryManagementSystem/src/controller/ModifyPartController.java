/**********************************************
Workshop 5&6
Course:APD545 - Winter 2024
Last Name: Pillay
First Name:Steven David
ID:162218218
Section:ZAA
This assignment represents my own work in accordance with Seneca Academic Policy.
Signature
Date:14-04-2024
**********************************************/
package controller;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import model.*;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static controller.HomeController.getSelectedPart;
import static controller.HomeController.getSelectedPartIndex;

public class ModifyPartController implements Initializable {

    @FXML
    private RadioButton InHouseRadioButton, OutsourcedRadioButton;
    @FXML
    private TextField PartIDTextField, PartNameTextField, PartInvTextField, PartPriceTextField, PartMaxTextField, PartMinTextField, MachineIDTextField;
    @FXML
    private Label MachineCompanyLabel;
    private Part selectedPart;
    private static int selectedPartIndex;
    private final Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return true;
        }
        return !pattern.matcher(strNum).matches();
    }

    @FXML
    public void handleInHouseRadioButton() {
        InHouseRadioButton.setSelected(true);
        OutsourcedRadioButton.setSelected(false);
        MachineCompanyLabel.setText("Machine ID");
    }

    @FXML
    public void handleOutsourcedRadioButton() {
        InHouseRadioButton.setSelected(false);
        OutsourcedRadioButton.setSelected(true);
        MachineCompanyLabel.setText("Company Name");
    }

    @FXML
    public void handleCancelButton(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Discard Changes?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Object scene = FXMLLoader.load(getClass().getResource("/view/MainView.fxml"));
            stage.setScene(new Scene((Parent) scene));
            stage.show();
        }
    }

    @FXML
    public void handleSaveButton(ActionEvent event) throws IOException {
        int partId = selectedPart.getId(), partIndex = selectedPartIndex, machineId;
        String partName = PartNameTextField.getText(), partPrice = PartPriceTextField.getText(), partInv = PartInvTextField.getText(), partMin = PartMinTextField.getText(), partMax = PartMaxTextField.getText(), companyName;
        
        if (partName.isEmpty()) {
        	Alert alert = new Alert(Alert.AlertType.ERROR, "Part name cannot be empty.");
            alert.showAndWait();
        } else if (isNumeric(partInv)) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Inv must be a number.");
            alert.showAndWait();
        } else if (isNumeric(partPrice)) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Price is invalid.");
            alert.showAndWait();
        } else if (Integer.parseInt(partMin) > Integer.parseInt(partMax)) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "MIN value can't be greater than MAX value.");
            alert.showAndWait();
        } else if (Integer.parseInt(partInv) > Integer.parseInt(partMax) || Integer.parseInt(partInv) < Integer.parseInt(partMin)) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory amount must be between minimum and maximum values.");
            alert.showAndWait();
        } else {
            if (InHouseRadioButton.isSelected()) {
                machineId = Integer.parseInt(MachineIDTextField.getText());
                InHouse modifyInHouse = new InHouse(partId, partName, Double.parseDouble(partPrice), Integer.parseInt(partInv), Integer.parseInt(partMin), Integer.parseInt(partMax), machineId);
                Inventory.updatePart(partIndex, modifyInHouse);
                Parent parent = FXMLLoader.load(getClass().getResource("/view/MainView.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } else if (OutsourcedRadioButton.isSelected()) {
                companyName = MachineIDTextField.getText();
                Outsourced newOutsourced = new Outsourced(partId, partName, Double.parseDouble(partPrice), Integer.parseInt(partInv), Integer.parseInt(partMin), Integer.parseInt(partMax), companyName);
                Inventory.updatePart(partIndex, newOutsourced);
                Parent parent = FXMLLoader.load(getClass().getResource("/view/MainView.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selectedPart = getSelectedPart();
        selectedPartIndex = getSelectedPartIndex();
        if (selectedPart instanceof InHouse) {
            InHouseRadioButton.setSelected(true);
            MachineCompanyLabel.setText("Machine ID");
            MachineIDTextField.setText(String.valueOf(((InHouse) selectedPart).getMachineID()));
        }
        if (selectedPart instanceof Outsourced) {
            OutsourcedRadioButton.setSelected(true);
            MachineCompanyLabel.setText("Company Name");
            MachineIDTextField.setText(String.valueOf(((Outsourced) selectedPart).getCompanyName()));
        }
        PartIDTextField.setText(String.valueOf(selectedPart.getId()));
        PartNameTextField.setText(String.valueOf(selectedPart.getName()));
        PartPriceTextField.setText(String.valueOf(selectedPart.getPrice()));
        PartInvTextField.setText(String.valueOf(selectedPart.getStock()));
        PartMaxTextField.setText(String.valueOf(selectedPart.getMax()));
        PartMinTextField.setText(String.valueOf(selectedPart.getMin()));
    }
}