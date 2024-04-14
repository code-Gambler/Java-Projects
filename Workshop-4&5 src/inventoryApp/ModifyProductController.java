package inventoryApp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.CodingErrorAction;
import java.util.Optional;
import java.util.ResourceBundle;

public class ModifyProductController implements Initializable {

    /**
     * The Product object being modified
     */
    public static Product product;

    /**
     * A snapshot of the original Product object passed to the ModifyProduct form
     */
    public static Product productOriginal;

    /**
     * Search for Parts search field
     */
    public TextField partsSearchField;

    /**
     * Add Part Button. Adds a part from the allPartsTable to the productPartsTable on the bottom half of the form
     */
    public Button addButton;

    /**
     * Remove Associated Part Button - removes a part from the productPartsTable on the bottom half of the form
     */
    public Button removePartBtn;

    /**
     * Saves the Product being modified and returns to the main form
     */
    public Button saveBtn;

    /**
     * Does not save the product being modified and returns to main form
     */
    public Button cancelBtn;

    /**
     * Holds an auto generated id, is disabled by default so that it cannot be edited
     */
    public TextField idTextField;

    /**
     * Accepts string input with no constraints for the Product Name eg. "Toyota", "Sedan", etc.
     */
    public TextField nameTextField;

    /**
     * Accepts string input which is then converted to integer to represent the number of named product currently in inventory. ie. the number of products available to sell.
     */
    public TextField invTextField;

    /**
     * Accepts string input which is then converted to Double to represent the price in Dollars of the product. Ie. How much the customer will pay
     * when purchasing the product
     */
    public TextField priceTextField;

    /**
     * The minimum number of product held in inventory
     */
    public TextField minTextField;

    /**
     * The maximum number of product held in inventory
     */
    public TextField maxTextField;

    /**
     * Table that holds the set of all parts. User can select a part from this table and then add it to the productPartsTable
     */
    public TableView allPartsTable;

    /**
     * Table that holds the set of all parts that the product being edited has. The user can add or remove parts from the product.
     */
    public TableView productPartsTable;

    /**
     * The list of parts associated with the Product being edited
     */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**
     * Holds the set of all Products and all Parts
     */
    private static Inventory inventory = new Inventory();

    /**
     * Sets listeners to filter all the text fields input and sets the product being modified as well as populates
     * all the tables
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        productOriginal = new Product(product.getId(), product.getName(), product.getPrice(), product.getStock(), product.getMin(), product.getMax());

        idTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.matches("\\d*")) {
                    idTextField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        invTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.matches("\\d*")) {
                    invTextField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        priceTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.matches("\\d*")) {
                    priceTextField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        minTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.matches("\\d*")) {
                    minTextField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        maxTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.matches("\\d*")) {
                    maxTextField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        idTextField.setText(Integer.toString(product.getId()));
        idTextField.setEditable(false);
        idTextField.setDisable(true);
        nameTextField.setText(product.getName());
        priceTextField.setText(Double.toString(product.getPrice()));
        maxTextField.setText(Integer.toString(product.getMax()));
        minTextField.setText(Integer.toString(product.getMin()));
        invTextField.setText(Integer.toString(product.getStock()));

        if(product.getAllAssociatedParts() != null) {
            System.out.println("associated part list size: " + Integer.toString(product.getAllAssociatedParts().size()));
            for(Part part : product.getAllAssociatedParts()) {
                associatedParts.add(part);
            }
        }

        this.allPartsTable.setEditable(true);
        this.productPartsTable.setEditable(true);

        TableColumn productIDCol = new TableColumn("Part ID");
        productIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn productNameCol = new TableColumn("Part Name");
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn inventoryLevelCol = new TableColumn("Inventory Level");
        inventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));

        TableColumn priceCostPerUnitCol = new TableColumn("Price/Cost per Unit");
        priceCostPerUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        allPartsTable.getColumns().addAll(productIDCol, productNameCol, inventoryLevelCol, priceCostPerUnitCol);


        allPartsTable.setItems(inventory.getAllParts());
        System.out.println("Set Part Table 1 parts list length: " + Integer.toString(inventory.getAllParts().size()));

        TableColumn productIDCol2 = new TableColumn("Part ID");
        productIDCol2.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn productNameCol2 = new TableColumn("Part Name");
        productNameCol2.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn inventoryLevelCol2 = new TableColumn("Inventory Level");
        inventoryLevelCol2.setCellValueFactory(new PropertyValueFactory<>("stock"));

        TableColumn priceCostPerUnitCol2 = new TableColumn("Price/Cost per Unit");
        priceCostPerUnitCol2.setCellValueFactory(new PropertyValueFactory<>("price"));
        productPartsTable.getColumns().addAll(productIDCol2, productNameCol2, inventoryLevelCol2, priceCostPerUnitCol2);

        System.out.println("Setting bottom table to have " + associatedParts.size() + " parts.");
        productPartsTable.setItems(associatedParts);

    }

    /**
     * Called when the user presses enter on the SearchField. Searches the table by name and id for the part in the inventory and displays
     * the results in the table
     * @param actionEvent - contains info about the event, not currently used
     */
    public void getPartSearchResultsHandler(ActionEvent actionEvent) {
        String queryText = this.partsSearchField.getText();
        System.out.println("getPartSearchResultsHandler: " + queryText);

        if(queryText.isEmpty()) {
            this.allPartsTable.setItems(inventory.getAllParts());
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
            // string is not a number so we search by part name instead of ID
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error: no results for " + queryText);
            alert.setHeaderText("Error: no results for " + queryText);
            alert.setContentText("Error: no results for " + queryText);
            alert.showAndWait();
            return;
        }
        this.allPartsTable.setItems(parts);
        this.partsSearchField.setText("");
    }

    /**
     * Sets the product to be modified before this form is loaded. Called in the MainController class to pass the Product selected
     * in the Product table to the ModifyProduct form.
     * @param _product
     */
    public static void setProduct(Product _product) {
        System.out.println("SetProduct: " + _product.getName());
        product = _product;
    }

    /**
     * When user clicks the save button this function is called. It checks all the text fields for correct input and displays a
     * warning if the input is invalid. If all input is valid, the Product being edited is saved back to inventory and the program
     * returns to the MainForm.
     * @param actionEvent - not currently used
     * @throws IOException -
     */
    public void saveProduct(ActionEvent actionEvent) throws IOException {
        String newName = nameTextField.getText();

        Double newPrice;
        try {
            newPrice = Double.parseDouble(priceTextField.getText());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing Price to Integer: " + priceTextField.getText());
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Price");
            alert.setHeaderText("Invalid Price");
            alert.setContentText("Invalid Price");
            alert.showAndWait();
            return;
        }

        Integer newMin;
        try {
            newMin = Integer.parseInt(minTextField.getText());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing Min to Integer: " + minTextField.getText());
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Min");
            alert.setHeaderText("Invalid Min");
            alert.setContentText("Invalid Min");
            alert.showAndWait();
            return;
        }

        Integer newMax;
        try {
            newMax = Integer.parseInt(maxTextField.getText());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing Max to Integer: " + maxTextField.getText());
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Max");
            alert.setHeaderText("Invalid Max");
            alert.setContentText("Invalid Max");
            alert.showAndWait();
            return;
        }

        if(newMax < newMin) {
            System.out.println("Error: Max must be greater than min");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error: Max must be greater than min");
            alert.setHeaderText("Error: Max must be greater than min");
            alert.setContentText("Error: Max must be greater than min");
            alert.showAndWait();
            return;
        }

        Integer newStock;
        try {
            newStock = Integer.parseInt(invTextField.getText());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing Inventory/Stock to Integer: " + invTextField.getText());
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Inv");
            alert.setHeaderText("Invalid Inv");
            alert.setContentText("Invalid Inv");
            alert.showAndWait();
            return;
        }

        inventory.deleteProduct(productOriginal);
        product.setName(newName);
        product.setPrice(newPrice);
        product.setMin(newMin);
        product.setMax(newMax);
        product.setStock(newStock);
        inventory.addProduct(product);

        toMainForm(actionEvent);
    }

    /**
     * Returns the program from the ModifyProductForm (the current form) the MainForm (the initial program screen that shows two tables)
     * @param actionEvent
     * @throws IOException
     */
    public void toMainForm(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("MainForm");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Adds a Part to the productPartsTable when the user clicks the add button
     * @param actionEvent
     */
    public void addToProductPartsTable(ActionEvent actionEvent) {
        Part part = (Part) allPartsTable.getSelectionModel().getSelectedItem();
        if(part == null) {
            System.out.println("Error: no part selected");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No part selected");
            alert.setHeaderText("No part selected");
            alert.setContentText("No part selected. Please select a part to add to product.");
            alert.showAndWait();
        } else {
            associatedParts.add(part);
            product.addAssociatedPart(part);
            System.out.println("Associated parts list length: " + Integer.toString(associatedParts.size()));
            productPartsTable.setEditable(true);
            productPartsTable.setItems(associatedParts);
        }
    }

    /**
     * Removes a part from the productPartsTable when the user clicks the remove part button
     * @param actionEvent
     */
    public void onRemovePartBtn(ActionEvent actionEvent) {
        Part part = (Part) productPartsTable.getSelectionModel().getSelectedItem();
        int index = productPartsTable.getSelectionModel().getSelectedIndex();
        if(part == null) {
            System.out.println("Error: no part selected");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No part selected");
            alert.setHeaderText("No part selected");
            alert.setContentText("No part selected. Please select a part to add to product.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Delete");
            alert.setHeaderText("Really delete part: " + part.getName() + "?");
            alert.setContentText("Really delete product: " + part.getName() + "?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                associatedParts.remove(index);
                product.deleteAssociatedPart(part);
                productPartsTable.setItems(associatedParts);
            }
        }
    }
}
