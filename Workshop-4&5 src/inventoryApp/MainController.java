package inventoryApp;

import javafx.application.Platform;
import javafx.collections.FXCollections;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    /**
     * Users enters a string or id number into this text input field and when they press enter the Parts table will refresh
     * displaying the set of parts containing either a matching id number or a substring of the search string entered.
     */
    public TextField partsSearchField;

    /**
     * Holds the set of all parts when there is no search query in the Parts search text field, and shows the Parts that
     * match the search query when there is one. Users can select a Part from this table to Modify or Delete. They can also
     * use the add button to add a Part to this table.
     */
    public TableView partTable;

    /**
     * If the user has a part selected in the Parts table then on hitting this Delete button they will see a confirmation
     * asking them if they really want to delete the Part. They can click "Ok" to delete the Part or "Cancel" to exit early
     * without deleting. If no part is selected the user will see a Warning that says no part was selected for deletion.
     */
    public Button deletePartBtn;

    /**
     * Users enters a string or id number into this text input field and when they press enter the Products table will refresh
     * displaying the set of Products containing either a matching id number or a substring of the search string entered.
     */
    public TextField productsSearchField;

    /**
     * Holds the set of all products when there is no search query in the Products search text field, and shows the Products that
     * match the search query when there is one. Users can select a Product from this table to Modify or Delete. They can also
     * use the add button to add a Product to this table.
     */
    public TableView productTable;

    /**
     * On press the user will see a confirmation asking if they are sure they wish to delete the selected Product in the
     * Product table. If there is no Product selected then the user will see a warning saying that there is no Product selected.
     */
    public Button deleteProductBtn;

    /**
     * Holds the set of all Products and all Parts
     */
    private static Inventory inventory = new Inventory();

    /**
     * Initializes the Parts table with 3 parts
     * @throws IOException
     */
    public void initPartTable() throws IOException {
        System.out.println("initPartTable()");

        this.partTable.setEditable(true);

        TableColumn partIDCol = new TableColumn("Part ID");
        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn partNameCol = new TableColumn("Part Name");
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn inventoryLevelCol = new TableColumn("Inventory Level");
        inventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));

        TableColumn priceCostPerUnitCol = new TableColumn("Price/Cost per Unit");
        priceCostPerUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        partTable.getColumns().addAll(partIDCol, partNameCol, inventoryLevelCol, priceCostPerUnitCol);

        partTable.setItems(inventory.getAllParts());
    }

    /**
     * Initializes the Product table with 3 Products
     * @throws IOException
     */
    public void initProductTable() throws IOException {
        System.out.println("initProductTable()");
        this.productTable.setEditable(true);

        TableColumn productIDCol = new TableColumn("Part ID");
        productIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn productNameCol = new TableColumn("Part Name");
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn inventoryLevelCol = new TableColumn("Inventory Level");
        inventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));

        TableColumn priceCostPerUnitCol = new TableColumn("Price/Cost per Unit");
        priceCostPerUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        productTable.getColumns().addAll(productIDCol, productNameCol, inventoryLevelCol, priceCostPerUnitCol);

        productTable.setItems(inventory.getAllProducts());
    }

    /**
     * Sets listeners to filter all the text fields input and sets the product being modified as well as populates
     * all the tables
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            initPartTable();
        } catch(Exception e) {
            System.out.println("Error initializing parts table: " + e.toString());
        }
        try {
            initProductTable();
        } catch(Exception e) {
            System.out.println("Error initializing products table");
        }
        System.out.println("Initialized");
    }

    /**
     * Leaves the MainForm and the program goes to the AddPartForm after user clicks the Add button under the Parts table.
     * @param actionEvent
     * @throws IOException
     */
    public void toAddPartForm(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AddPartForm.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Add Part");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Leaves the MainForm and the program goes to the AddProductForm after user clicks the Add button under the Products table.
     * @param actionEvent
     * @throws IOException
     */
    public void toAddProductForm(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AddProductForm.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Add Part");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Leaves the MainForm and the program goes to the ModifyPartForm after user clicks the Add button under the Parts table.
     * @param actionEvent
     * @throws IOException
     */
    public void toModifyPartForm(ActionEvent actionEvent) throws IOException {
        Part part = (Part)partTable.getSelectionModel().getSelectedItem();

        if(part == null) {
            System.out.println("Error: no part selected");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No part selected");
            alert.setHeaderText("No part selected");
            alert.setContentText("No part selected. Please select a part to modify.");
            alert.showAndWait();
        } else {
            ModifyPartController.setPart(part);
            Parent root = FXMLLoader.load(getClass().getResource("ModifyPartForm.fxml"));
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 600, 400);
            stage.setTitle("Modify Part");
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * Leaves the MainForm and the program goes to the ModifyProductForm after user clicks the Add button under the Products table.
     * @param actionEvent
     * @throws IOException
     */
    public void toModifyProductForm(ActionEvent actionEvent) throws IOException {
        Product product = (Product)productTable.getSelectionModel().getSelectedItem();

        if(product == null) {
            System.out.println("Error: no product selected");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No product selected");
            alert.setHeaderText("No product selected");
            alert.setContentText("No product selected. Please select a product to modify.");
            alert.showAndWait();
        } else {
            ModifyProductController.setProduct(product);
            Parent root = FXMLLoader.load(getClass().getResource("ModifyProductForm.fxml"));
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 600, 400);
            stage.setTitle("Modify Product");
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * When the user clicks the Delete Part Button they will see a confirmation asking them if they really want to delete
     * If there is no Part selected, no Part will be deleted
     * @param actionEvent
     */
    public void OnDeletePartBtnClicked(ActionEvent actionEvent) {
        System.out.println("OnDeletePartBtnClicked: ");
        Part part = (Part)partTable.getSelectionModel().getSelectedItem();

        if(part == null) {
            System.out.println("No part selected.");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No part selected");
            alert.setHeaderText("No part selected");
            alert.setContentText("No part selected. Please select a part to delete.");
            alert.showAndWait();
            return;
        } else {
            System.out.println("part: " + part.getName() + " selected.");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Delete");
            alert.setHeaderText("Really delete part: " + part.getName() + "?");
            alert.setContentText("Really delete part: " + part.getName() + "?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                inventory.deletePart(part);
                partTable.setItems(inventory.getAllParts());
            }
        }
    }

    /**
     * When the user clicks the Delete Part Button they will see a confirmation asking them if they really want to delete
     * If there is no Part selected, no Part will be deleted
     * @param actionEvent
     */
    public void OnDeleteProductBtnClicked(ActionEvent actionEvent) {
        System.out.println("OnDeleteProductBtnClicked: ");
        Product product = (Product)productTable.getSelectionModel().getSelectedItem();

        if(product == null) {
            System.out.println("No product selected.");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No product selected");
            alert.setHeaderText("No product selected");
            alert.setContentText("No product selected. Please select a product to delete.");
            alert.showAndWait();
            return;
        } else {
            System.out.println("product: " + product.getName() + " selected.");
            if(product.getAllAssociatedParts().size() > 0) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error: Cannot delete a product");
                alert.setHeaderText("Error: Cannot delete a product unless it has zero associated parts. Please remove all parts before deleting.");
                alert.setContentText("Error: Cannot delete a product unless it has zero associated parts. Please remove all parts before deleting.");
                alert.showAndWait();
                return;
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirm Delete");
                alert.setHeaderText("Really delete product: " + product.getName() + "?");
                alert.setContentText("Really delete product: " + product.getName() + "?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    inventory.deleteProduct(product);
                    productTable.setItems(inventory.getAllProducts());
                }
            }
        }
    }

    /**
     * Executed when the user presses Enter in the PartsSearchField
     * If the string is empty the table will show all of the Parts in the Inventory, otherwise
     * the table will show all parts containing the search string as a string or with a matching id number
     * @param actionEvent
     */
    public void getPartSearchResultsHandler(ActionEvent actionEvent) {
        String queryText = this.partsSearchField.getText();
        System.out.println("getPartSearchResultsHandler: " + queryText);

        if(queryText.isEmpty()) {
            this.partTable.setItems(inventory.getAllParts());
        }

        ObservableList<Part> parts = inventory.lookupPart(queryText);
        try {
            int idNum = Integer.parseInt(queryText);
            Part part = inventory.lookupPart(idNum);
            parts.add(part);
        } catch(NumberFormatException exception) {
            System.out.println("Non Fatal Error: " + queryText + " cannot be converted to Integer.");
        }

        if(parts.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error: no results for " + queryText);
            alert.setHeaderText("Error: no results for " + queryText);
            alert.setContentText("Error: no results for " + queryText);
            alert.showAndWait();
            return;
        }
        this.partTable.setItems(parts);
        this.partsSearchField.setText("");
    }

    /**
     * Executed when the user presses Enter in the ProductsSearchField
     * If the string is empty the table will show all of the Products in the Inventory, otherwise
     * the table will show all Products containing the search string as a string or with a matching id number
     * @param actionEvent
     */
    public void getProductSearchResultsHandler(ActionEvent actionEvent) {
        String queryText = this.productsSearchField.getText();
        System.out.println("getProductSearchResultsHandler: " + queryText);

        if(queryText.isEmpty()) {
            this.productTable.setItems(inventory.getAllProducts());
        }

        ObservableList<Product> products = inventory.lookupProduct(queryText);
        try {
            int idNum = Integer.parseInt(queryText);
            Product product = inventory.lookupProduct(idNum);
            products.add(product);
        } catch(NumberFormatException exception) {
            // string is not a number so we search by part name instead of ID
            System.out.println("Non Fatal Error: " + queryText + " cannot be converted to Integer.");
        }

        if(products.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error: no results for " + queryText);
            alert.setHeaderText("Error: no results for " + queryText);
            alert.setContentText("Error: no results for " + queryText);
            alert.showAndWait();
            return;
        }

        this.productTable.setItems(products);
        this.productsSearchField.setText("");
    }

    /**
     * Executed with the user clicks the exit button. Exits the program immediately.
     * @param actionEvent
     */
    public void onExitBtnClicked(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }
}
