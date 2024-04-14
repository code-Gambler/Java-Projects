package inventoryApp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddPartController implements Initializable {

    /**
     * Holds the Part Id. Disabled and cannot be edited.
     */
    public TextField idTextField;

    /**
     * A field where the user can input a name for the Part being created
     */
    public TextField nameTextField;

    /**
     * A field where the user can input the amount of this part being created in inventory
     */
    public TextField invTextField;

    /**
     * A field where the user can input the price for the part being created
     */
    public TextField priceTextField;

    /**
     * The minimum number of the Part type being created that can be in Inventory
     */
    public TextField minTextField;

    /**
     * The maximum number of the Part type being created that can be Inventory
     */
    public TextField maxTextField;

    /**
     * If the part being created is an InHousePart this text field will take a machineId to represent the ID of the machine
     * that created the part. If it is an outSourcedPart this field will take a CompanyName to represent the company that made
     * the part being created.
     */
    public TextField bottomTextField;

    /**
     * Labels the bottomTextField. Says "Machine ID" when the part being created is an InHousePart, and "Company Name" if the part
     */
    public Label bottomTextFieldLabel;

    /**
     * A listener that changes based on whether the part being created is an InHousePart or an OutSourcedPart.
     * If it is an InHousePart then the textField will only accept numbers for the machineId,
     * If it is an outSourcedPart then the textField will accept all characters as acceptable input for the CompanyName
     * Updates based on whether the InHousePart radio button is selected, or the outsourcedPartRadioButton is selected
     */
    public ChangeListener<String> bottomTextFieldListener;

    /**
     * If selected the part being created is an InHousePart and has a MachineID for the machine that created the part
     */
    public RadioButton inHouseRadioButton;

    /**
     * If selected the part being created in an OutSourcedPart and has a CompanyName for the Company that created the part.
     */
    public RadioButton outsourcedRadioButton;

    /**
     * A static instance of the Inventory so that once the Part is finished being edited by the user, it can be added to the Inventory
     */
    private static Inventory inventory = new Inventory();

    /**
     * Toggle group that makes sure that only either inHouseRadioButton or outSourcedRadioButton can be selected
     */
    private ToggleGroup toggleGroup = new ToggleGroup();

    /**
     * Sets listeners to filter all the text fields input and sets the product being modified
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idTextField.setText(Integer.toString(inventory.newPartId()));
        idTextField.setEditable(false);
        idTextField.setDisable(true);

        inHouseRadioButton.setSelected(true);
        inHouseRadioButton.setToggleGroup(toggleGroup);
        outsourcedRadioButton.setToggleGroup(toggleGroup);

        invTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.matches("\\d*")) {
                    invTextField.setText(newValue.replaceAll("[^\\d]", ""));
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
        this.bottomTextFieldListener = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.matches("\\d*")) {
                    bottomTextField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        };
        bottomTextField.textProperty().addListener(bottomTextFieldListener);
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
     * Sets the Label of the bottomTextField to "Machine ID" when the inHouseRadioButton is selected
     * @param actionEvent
     */
    public void setLabelToMachineID(ActionEvent actionEvent) {
        System.out.println("setLabelToMachineID");
        bottomTextFieldLabel.setText("Machine ID");
        bottomTextField.setText("");
        bottomTextField.textProperty().removeListener(this.bottomTextFieldListener);
        this.bottomTextFieldListener = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.matches("\\d*")) {
                    bottomTextField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        };
        bottomTextField.textProperty().addListener(this.bottomTextFieldListener);
    }

    /**
     * Sets the Label of the bottomTextField to "Company Name" when the outsourcedRadioButton is selected
     * @param actionEvent
     */
    public void setLabelToCompanyName(ActionEvent actionEvent) {
        System.out.println("setLabelToCompanyName");
        bottomTextFieldLabel.setText("Company Name");
        bottomTextField.setText("");
        bottomTextField.textProperty().removeListener(this.bottomTextFieldListener);
        this.bottomTextFieldListener = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                bottomTextField.setText(newValue);
            }
        };
        bottomTextField.textProperty().addListener(this.bottomTextFieldListener);
    }

    /**
     * Validates the data in all the text fields and creates a Part object which is then added to the Inventory, and then the program returns
     * to the MainForm
     * @param actionEvent
     * @throws IOException
     */
    public void savePart(ActionEvent actionEvent) throws IOException {
        System.out.println("savePart()");
        String newName = nameTextField.getText();

        if(newName.length() == 0) {
            System.out.println("Error Invalid Name: " + newName);
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Name");
            alert.setHeaderText("Invalid Name");
            alert.setContentText("Invalid Name");
            alert.showAndWait();
            return;
        }

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

        int newMachineId = -1;
        String newCompanyName = "";
        if(inHouseRadioButton.isSelected()) {
            try {
                newMachineId = Integer.parseInt(bottomTextField.getText());
            } catch (NumberFormatException e) {
                System.out.println("Error parsing machineId to Integer: " + bottomTextField.getText());
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Invalid Machine ID");
                alert.setHeaderText("Invalid Machine ID");
                alert.setContentText("Invalid Machine ID");
                alert.showAndWait();
                return;
            }
            InHousePart part = new InHousePart(Integer.parseInt(idTextField.getText()), newName, newPrice, newStock, newMin, newMax, newMachineId);
            inventory.addPart(part);
            System.out.println("Add InHouse Part: " + part.getName());
        } else if(outsourcedRadioButton.isSelected()) {
            newCompanyName = bottomTextField.getText();
            if(newCompanyName.length() == 0) {
                System.out.println("Error: Empty company name");
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error: Empty company name");
                alert.setHeaderText("Error: Empty company name");
                alert.setContentText("Error: Empty company name");
                alert.showAndWait();
                return;
            }
            OutSourcedPart part = new OutSourcedPart(Integer.parseInt(idTextField.getText()), newName, newPrice, newStock, newMin, newMax, newCompanyName);
            inventory.addPart(part);
            System.out.println("Add Outsourced Part: " + part.getName());
        }
        toMainForm(actionEvent);
    }
}
