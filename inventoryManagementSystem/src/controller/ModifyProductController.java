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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import static controller.HomeController.getSelectedProductIndex;

public class ModifyProductController implements Initializable {
    private Stage stage;
    private Object scene;
    @FXML 
    private TableView<Part> PartTable;
    @FXML 
    private TableView<Part> AssociatedPartTable;
    @FXML 
    private TableColumn<Part, Integer> PartID, PartInv;
    @FXML 
    private TableColumn<Product, Integer> AssociatedPartID, AssociatedPartInv;
    @FXML 
    private TableColumn<Part, String> PartName;
    @FXML 
    private TableColumn<Product, String> AssociatedPartName;
    @FXML 
    private TableColumn<Part, Double> PartPrice;
    @FXML 
    private TableColumn<Product, Double> AssociatedPartPrice;
    @FXML 
    private TextField NameTextField, InvTextField, PriceTextField, MaxTextField, MinTextField, IDTextField, SearchField;
    private ObservableList<Part> associatedPart = FXCollections.observableArrayList();
    private final int productIndex = getSelectedProductIndex();
    private final Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return true;
        }
        return !pattern.matcher(strNum).matches();
    }

    @FXML 
    public void handleSearchButton(ActionEvent event) {
        ObservableList<Part> foundPart = Inventory.findPartByName(SearchField.getText());
        if(foundPart.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Part not found");
            alert.setHeaderText(SearchField.getText() + " was not found.");
            alert.showAndWait();
        } else {
            PartTable.setItems(foundPart);
        }
    }

    @FXML 
    public void handleCancelButton(ActionEvent event) throws IOException {
        if (HomeController.confirmDialog("Cancel", "Cancel modifying this product?")) {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainView.fxml"));
            stage.setScene(new Scene((Parent) scene));
            stage.show();
        }
    }

    @FXML 
    void handleSaveButton(ActionEvent event) throws IOException {
        String productName = NameTextField.getText();
        String productInv = InvTextField.getText();
        String productPrice = PriceTextField.getText();
        String productMin = MinTextField.getText();
        String productMax = MaxTextField.getText();
        try {
            if (isNumeric(productInv) || isNumeric(productPrice) || isNumeric(productMin) || isNumeric(productMax)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Invalid entry");
                alert.setHeaderText("Check all values");
                alert.setContentText("Inv, Price, Min and Max must be numbers.");
                alert.showAndWait();
            } else {
                if (productName.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Enter a part name");
                    alert.setHeaderText("Enter a part name");
                    alert.setContentText("Part name cannot be empty.");
                    alert.showAndWait();
                } else if (Integer.parseInt(productMin) > Integer.parseInt(productMax)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Invalid Min/Max Value");
                    alert.setHeaderText("Invalid Min/Max Value");
                    alert.setContentText("Min value must be less than Max value.");
                    alert.showAndWait();
                } else if (Integer.parseInt(productInv) < Integer.parseInt(productMin) || Integer.parseInt(productInv) > Integer.parseInt(productMax)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Invalid Inv Value");
                    alert.setHeaderText("Invalid Inv Value");
                    alert.setContentText("Inv must be between Min and Max values.");
                    alert.showAndWait();
                } else {
                    Product newProduct = new Product();
                    newProduct.setId(productIndex + 1);
                    newProduct.setName(productName);
                    newProduct.setPrice(Double.parseDouble(productPrice));
                    newProduct.setStock(Integer.parseInt(productInv));
                    newProduct.setMin(Integer.parseInt(productMin));
                    newProduct.setMax(Integer.parseInt(productMax));
                    newProduct.addProductPart(associatedPart);
                    Inventory.modifyProduct(productIndex, newProduct);
                    stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("/view/MainView.fxml"));
                    stage.setScene(new Scene((Parent) scene));
                    stage.show();
                }
            }
            } catch(NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("Please check all fields.");
                alert.showAndWait();
            }

    }

    @FXML 
    void handleAddButton(ActionEvent event) {
        Part selectedPart = PartTable.getSelectionModel().getSelectedItem();
        if(selectedPart != null) {
            associatedPart.add(selectedPart);
            updateAssociatedPartTable();
        }
        else {
        	Alert alert = new Alert(Alert.AlertType.ERROR, "Select a part to add to the Product");
            alert.showAndWait();
        }
    }

    @FXML
    void handleRemoveButton(ActionEvent event) {
        Part selectedPart = AssociatedPartTable.getSelectionModel().getSelectedItem();

        if(selectedPart != null) {
            HomeController.confirmDialog("Deleting Part","Are you sure you want to delete " + selectedPart.getName() + " from the Product?");
            associatedPart.remove(selectedPart);
            updateAssociatedPartTable();
        }
        else {
        	Alert alert = new Alert(Alert.AlertType.ERROR, "Please choose something to remove");
            alert.showAndWait();
        }
    }

    private void updateAssociatedPartTable() {
        AssociatedPartTable.setItems(associatedPart);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Product selectedProduct = HomeController.getSelectedProduct();
        associatedPart = selectedProduct.getProductParts();

        PartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        PartTable.setItems(Inventory.getPartList());

        AssociatedPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        AssociatedPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        AssociatedPartInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        AssociatedPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        AssociatedPartTable.setItems(associatedPart);

        IDTextField.setText(String.valueOf(selectedProduct.getId()));
        NameTextField.setText(selectedProduct.getName());
        PriceTextField.setText(String.valueOf(selectedProduct.getPrice()));
        InvTextField.setText(String.valueOf(selectedProduct.getStock()));
        MinTextField.setText(String.valueOf(selectedProduct.getMin()));
        MaxTextField.setText(String.valueOf(selectedProduct.getMax()));
    }
}