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

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
import java.util.Optional;
import java.util.ResourceBundle;

import static model.Inventory.getPartList;
import static model.Inventory.getProductList;

/** Controller for the main screen of the application. */
public class HomeController implements Initializable {

    // Parts Table
    @FXML 
    private TableView<Part> PartTable;
    @FXML 
    private TableView<Product> ProductTable;
    @FXML 
    private TableColumn<Part, Integer> PartID, PartInvLevel;
    @FXML 
    private TableColumn<Product, Integer> ProductID, ProductInvLevel;
    @FXML 
    private TableColumn<Part, String> PartName;
    @FXML 
    private TableColumn<Product, String> ProductName;
    @FXML 
    private TableColumn<Part, Double> PartPrice;
    @FXML 
    private TableColumn<Product, Double> ProductPrice;
    @FXML 
    private TextField PartSearch, ProductSearch;
    private static Part selectedPart;
    public static Product selectedProduct;
    private static int selectedPartIndex;
    public static int selectedProductIndex;

    public static Part getSelectedPart() {
        return selectedPart;
    }

    public static int getSelectedPartIndex() {
        return selectedPartIndex;
    }

    public static Product getSelectedProduct() {
        return selectedProduct;
    }

    public static int getSelectedProductIndex() { 
    	return selectedProductIndex; 
    }

    @FXML
    public void handleAddPartButton(ActionEvent event) throws IOException {
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/AddPartView.fxml")));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void handleModifyPartButton(ActionEvent event) throws IOException {
        selectedPart = PartTable.getSelectionModel().getSelectedItem();
        selectedPartIndex = getPartList().indexOf(selectedPart);
        try {
            if (selectedPart != null) {
                Parent parent = FXMLLoader.load(getClass().getResource("/view/ModifyPartView.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } else {
            	Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a part to modify.");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleDeletePartButton(ActionEvent event) throws IOException {
        if (PartTable.getSelectionModel().isEmpty()){
        	Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a part to delete.");
            alert.showAndWait();
            return;
        }
        if (confirmDialog("Delete Part", "Are you sure you want to delete this part?")){
            int selectedPart = PartTable.getSelectionModel().getSelectedIndex();
            PartTable.getItems().remove(selectedPart);
        }
    }

    public void handlePartSearch(ActionEvent event) throws IOException {
        ObservableList<Part> partSearchText = Inventory.findPartByName(PartSearch.getText());
        if(partSearchText.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Part not found");
            alert.setHeaderText(PartSearch.getText() + " was not found.");
            alert.showAndWait();
        } else {
            PartTable.setItems(partSearchText);
        }
    }

    public void handleAddProductButton(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/AddProductView.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void handleModifyProductButton(ActionEvent event) throws IOException {
        selectedProduct = ProductTable.getSelectionModel().getSelectedItem();
        selectedProductIndex = getProductList().indexOf(selectedProduct);
        if (selectedProduct == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("No Product Selected");
            alert.setHeaderText("Select a product to modify.");
            alert.showAndWait();
        } else {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/ModifyProductView.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

    public void handleDeleteProductButton(ActionEvent event) throws IOException {
        Product selectedProduct = ProductTable.getSelectionModel().getSelectedItem();
        if (selectedProduct == null){
        	Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a product to delete.");
            alert.showAndWait();
        } else if (!selectedProduct.getProductParts().isEmpty()) {
        	Alert alert = new Alert(Alert.AlertType.ERROR, "All associated parts must be removed before product can be deleted.");
            alert.showAndWait();
        } else {
            if (confirmDialog("Delete Product", "Are you sure you want to delete this product?")) {
                int selectedPart = ProductTable.getSelectionModel().getSelectedIndex();
                ProductTable.getItems().remove(selectedPart);
            }
        }
    }

    public void handleProductSearch(ActionEvent event) throws IOException {
        ObservableList<Product> productSearchText = Inventory.findProductByName(ProductSearch.getText());
        if(productSearchText.isEmpty()) {
        	Alert alert = new Alert(Alert.AlertType.ERROR, ProductSearch.getText() + " was not found.");
            alert.showAndWait();
        } else {
            ProductTable.setItems(productSearchText);
        }
    }

    public void handleExitButton(ActionEvent event) throws IOException {
        confirmDialog("Close Program", "Are you sure you want to exit?");
        {
            System.exit(0);
        }
    }

    static boolean confirmDialog(String title, String content){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText("Confirm");
        alert.setContentText(content);
        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PartTable.setItems(getPartList());
        ProductTable.setItems(getProductList());

        PartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartInvLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        ProductID.setCellValueFactory(new PropertyValueFactory<>("id"));
        ProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ProductInvLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        ProductPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}
