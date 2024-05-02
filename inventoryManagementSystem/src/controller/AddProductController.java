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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddProductController implements Initializable {
    private final ObservableList<Part> productParts = FXCollections.observableArrayList();
    public TextField SearchTextField, ProdIDTextField, ProdNameTextField, ProdInvTextField, ProdMaxTextField, ProdMinTextField, ProdPriceTextField;
    public TableView<Part> PartTable, AssociatedPart;
    public TableColumn<Part, Integer> PartID, PartInv, AssociatedPartID, AssociatedPartInv;
    public TableColumn<Part, Double> PartPrice, AssociatedPartPrice;
    public TableColumn<Part, String> AssociatedPartName, PartName;
    int productId = Inventory.newProdID();

    public void handleSaveButton(ActionEvent actionEvent) throws IOException {
        try {
            String productName = ProdNameTextField.getText();
            double productPrice = Double.parseDouble(ProdPriceTextField.getText());
            int inv = Integer.parseInt(ProdInvTextField.getText()), min = Integer.parseInt(ProdMinTextField.getText()), max = Integer.parseInt(ProdMaxTextField.getText());

            if (productName.isEmpty()) {
            	Alert alert = new Alert(Alert.AlertType.ERROR, "Part name cannot be empty.");
                alert.showAndWait();
            } else {
                if (min > max) {
                	Alert alert = new Alert(Alert.AlertType.ERROR, "Minimum value must be less than Maximum value.");
                    alert.showAndWait();
                } else if ((inv < min) || (inv > max)) {
                	Alert alert = new Alert(Alert.AlertType.ERROR, "Inv value must be inbetween Min and Max.");
                    alert.showAndWait();
                }
                else {
                    Product newProduct = new Product(productId, productName, productPrice, inv, min, max);
                    for (Part part : productParts) {
                        newProduct.addPart(part);
                    }
                    newProduct.setId(productId);
                    Inventory.addProduct(newProduct);
                    Parent parent = FXMLLoader.load(getClass().getResource("/view/MainView.fxml"));
                    Scene scene = new Scene(parent);
                    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                }
            }
        } catch (Exception e) {
        	
        	Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill out all fields.");
            alert.showAndWait();
        }

    }

    public void handleCancelButton(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setContentText("Would you like to exit without saving?(Product will Be Discarded)");
        Optional<ButtonType> answer = alert.showAndWait();

        if (answer.isPresent() && answer.get() == ButtonType.OK) {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/MainView.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

    public void handleAddPartButton(ActionEvent actionEvent) {
        Part thisPart = PartTable.getSelectionModel().getSelectedItem();

        if (thisPart == null) {
        	Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a part.");
            alert.showAndWait();
        } else {
            productParts.add(thisPart);
            AssociatedPart.setItems(productParts);
        }
    }

    public void handleRemovePartButton(ActionEvent actionEvent) {
        Part selectedPart = AssociatedPart.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {
        	Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a part.");
            alert.showAndWait();
        } else {
            Alert confirmRemoval = new Alert(Alert.AlertType.CONFIRMATION);
            confirmRemoval.setTitle("Part Removal Confirmation");
            confirmRemoval.setContentText("Remove Selected Part?");
            Optional<ButtonType> answer = confirmRemoval.showAndWait();

            if (answer.isPresent() && answer.get() == ButtonType.OK) {
                productParts.remove(selectedPart);
                AssociatedPart.setItems(productParts);
            }

        }
    }

    public void handleSearchButton(ActionEvent actionEvent) {
        ObservableList<Part> foundPart = Inventory.findPartByName(SearchTextField.getText());
        if(foundPart.isEmpty()) {
        	Alert alert = new Alert(Alert.AlertType.ERROR, SearchTextField.getText() + " was not found.");
            alert.showAndWait();
        } else {
            PartTable.setItems(foundPart);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ProdIDTextField.setText(String.valueOf(productId));
        PartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        PartTable.setItems(Inventory.getPartList());

        AssociatedPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        AssociatedPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        AssociatedPartInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        AssociatedPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

}
