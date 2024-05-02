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
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

import static model.Inventory.getPartList;
import static model.Inventory.getProductList;
import static data.FileHandler.writePart;
import static data.FileHandler.writeProduct;
import static data.FileHandler.loadPart;
import static data.FileHandler.loadProduct;

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
    
    public void handleSaveButton(ActionEvent event) throws IOException {
    	writePart(getPartList());
    	writeProduct(getProductList());
    }
    
    public void handleLoadButton(ActionEvent event) throws IOException, ClassNotFoundException {
    	for(Part part: loadPart()) {
    		Inventory.addPart(part);
    	}
    	for(Product product: loadProduct()) {
    		Inventory.addProduct(product);
    	}
    	System.out.println("All loaded successfully");
    }

    static boolean confirmDialog(String title, String content){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText("Confirm");
        alert.setContentText(content);
        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }
    
    @FXML
    void saveToDB(ActionEvent event) {
        String connectionURL = "jdbc:mysql://127.0.0.1:3306/sys?user=root&password=APD545ZAA";
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(connectionURL);
            connection.setAutoCommit(false);
            
            // Delete Statement
            //////////////////////////////////////////////////////////////////////////////////////////////////////
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate("DELETE FROM product_parts");
                stmt.executeUpdate("DELETE FROM parts");
                stmt.executeUpdate("DELETE FROM products");
            }
            
            // Insert Part Statement
            //////////////////////////////////////////////////////////////////////////////////////////////////////
            for (Part currentInhousePart : Inventory.getPartList()) {
                String insertStatement = "";
                if (currentInhousePart instanceof InHouse) {
                    InHouse inHousePart = (InHouse) currentInhousePart;
                    insertStatement = "INSERT INTO parts (id, name, price, stock, min, max, partType, machineID) VALUES (?, ?, ?, ?, ?, ?, 'InHouse', ?)";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(insertStatement)) {
                        preparedStatement.setInt(1, inHousePart.getId()); // Set ID
                        preparedStatement.setString(2, inHousePart.getName());
                        preparedStatement.setDouble(3, inHousePart.getPrice());
                        preparedStatement.setInt(4, inHousePart.getStock());
                        preparedStatement.setInt(5, inHousePart.getMin());
                        preparedStatement.setInt(6, inHousePart.getMax());
                        preparedStatement.setInt(7, inHousePart.getMachineID()); // Adjusted parameter index
                        preparedStatement.executeUpdate();
                    }
                } else if (currentInhousePart instanceof Outsourced) {
                    Outsourced outsourcedPart = (Outsourced) currentInhousePart;
                    insertStatement = "INSERT INTO parts (id, name, price, stock, min, max, partType, companyName) VALUES (?, ?, ?, ?, ?, ?, 'Outsourced', ?)";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(insertStatement)) {
                        preparedStatement.setInt(1, outsourcedPart.getId()); // Set ID
                        preparedStatement.setString(2, outsourcedPart.getName());
                        preparedStatement.setDouble(3, outsourcedPart.getPrice());
                        preparedStatement.setInt(4, outsourcedPart.getStock());
                        preparedStatement.setInt(5, outsourcedPart.getMin());
                        preparedStatement.setInt(6, outsourcedPart.getMax());
                        preparedStatement.setString(7, outsourcedPart.getCompanyName()); // Adjusted parameter index
                        preparedStatement.executeUpdate();
                    }
                }
            }

            // Insert Product Statement
            //////////////////////////////////////////////////////////////////////////////////////////////////////
            String insertProductStatement = "INSERT INTO products (id, name, price, stock, min, max) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertProductStatement)) {
                for (Product product : Inventory.getProductList()) {
                    preparedStatement.setInt(1, product.getId());
                    preparedStatement.setString(2, product.getName());
                    preparedStatement.setDouble(3, product.getPrice());
                    preparedStatement.setInt(4, product.getStock());
                    preparedStatement.setInt(5, product.getMin());
                    preparedStatement.setInt(6, product.getMax());
                    preparedStatement.executeUpdate();
                }
            }
            
            // Insert Product Part Statement
            //////////////////////////////////////////////////////////////////////////////////////////////////////
            String insertProductPartStatement = "INSERT INTO product_parts (product_id, part_id) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertProductPartStatement)) {
                for (Product product : Inventory.getProductList()) {
                    for (Part part : product.getProductParts()) {
                        preparedStatement.setInt(1, product.getId());
                        preparedStatement.setInt(2, part.getId());
                        preparedStatement.executeUpdate();
                    }
                }
            }

            // End Transaction
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
            	// Restore Previous Saved Version
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    @FXML
    void loadFromDB(ActionEvent event) {
        String connectionURL = "jdbc:mysql://127.0.0.1:3306/sys?user=root&password=APD545ZAA";
        try (Connection connection = DriverManager.getConnection(connectionURL)) {
        	// Clear table
            Inventory.clear();
            
            // Query-Part
            /////////////////////////////////////////////////////////////////////////////////////////////
            String queryPartStatement = "SELECT * FROM parts";
            try (PreparedStatement preparedStatement = connection.prepareStatement(queryPartStatement);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Part part = null;
                    String partType = resultSet.getString("partType");
                    if ("InHouse".equals(partType)) {
                        part = new InHouse(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getDouble("price"),
                            resultSet.getInt("stock"),
                            resultSet.getInt("min"),
                            resultSet.getInt("max"),
                            resultSet.getInt("machineID")
                        );
                    } else if ("Outsourced".equals(partType)) {
                        part = new Outsourced(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getDouble("price"),
                            resultSet.getInt("stock"),
                            resultSet.getInt("min"),
                            resultSet.getInt("max"),
                            resultSet.getString("companyName")
                        );
                    }
                    if (part != null) {
                        Inventory.addPart(part);
                    }
                }
            }

            // Query-Product
            /////////////////////////////////////////////////////////////////////////////////////////////
            String queryProductStatement = "SELECT * FROM products";
            try (PreparedStatement preparedStatement = connection.prepareStatement(queryProductStatement);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Product product = new Product(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getDouble("price"),
                            resultSet.getInt("stock"),
                            resultSet.getInt("min"),
                            resultSet.getInt("max")
                    );
                    Inventory.addProduct(product);
                }
            }

            // Query-Product-Part
            /////////////////////////////////////////////////////////////////////////////////////////////
            String queryProductPartStatement = "SELECT * FROM product_parts";
            try (PreparedStatement preparedStatement = connection.prepareStatement(queryProductPartStatement);
                 ResultSet resultStatement = preparedStatement.executeQuery()) {
                while (resultStatement.next()) {
                    int productId = resultStatement.getInt("product_id");
                    int partId = resultStatement.getInt("part_id");
                    
                    Product product = Inventory.findProductByID(productId);
                    Part part = Inventory.findPartByID(partId);
                    
                    if (product != null && part != null) {
                        product.addPart(part);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
