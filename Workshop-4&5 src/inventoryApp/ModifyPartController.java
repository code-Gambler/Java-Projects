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
import java.util.ResourceBundle;

public class ModifyPartController implements Initializable {

    public static Part part;
    public static Part partOriginal;

    public TextField idTextField;
    public TextField nameTextField;
    public TextField priceTextField;
    public TextField minTextField;
    public TextField maxTextField;
    public TextField invTextField;
    public TextField bottomTextField;
    public Label bottomTextFieldLabel;
    public ChangeListener<String> bottomTextFieldListener;

    public Button cancelBtn;
    public Button saveBtn;

    private ToggleGroup toggleGroup = new ToggleGroup();
    public RadioButton outsourcedRadioButton;
    public RadioButton inHouseRadioButton;

    private static Inventory inventory = new Inventory();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if(part instanceof InHousePart) {
            System.out.println("Part is InHousePart");
            partOriginal = new InHousePart(part.getId(), part.getName(), part.getPrice(), part.getStock(), part.getMin(), part.getMax(), ((InHousePart) part).getMachineId());
            inHouseRadioButton.setSelected(true);
        } else if(part instanceof OutSourcedPart) {
            System.out.println("Part is OutsourcedPart");
            partOriginal = new OutSourcedPart(part.getId(), part.getName(), part.getPrice(), part.getStock(), part.getMin(), part.getMax(), ((OutSourcedPart) part).getCompanyName());
            outsourcedRadioButton.setSelected(true);
        }

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

        inHouseRadioButton.setToggleGroup(toggleGroup);
        outsourcedRadioButton.setToggleGroup(toggleGroup);

        idTextField.setText(Integer.toString(part.getId()));
        idTextField.setEditable(false);
        idTextField.setDisable(true);
        nameTextField.setText(part.getName());
        priceTextField.setText(Double.toString(part.getPrice()));
        maxTextField.setText(Integer.toString(part.getMax()));
        minTextField.setText(Integer.toString(part.getMin()));
        invTextField.setText(Integer.toString(part.getStock()));
        if(part instanceof InHousePart) {
            bottomTextField.setText(Integer.toString(((InHousePart) part).getMachineId()));
        } else if(part instanceof OutSourcedPart) {
            bottomTextField.setText(((OutSourcedPart) part).getCompanyName());
        }
    }

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

    public void savePart(ActionEvent actionEvent) throws IOException {
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
        }
        inventory.deletePart(partOriginal);
        part.setName(newName);
        part.setPrice(newPrice);
        part.setMin(newMin);
        part.setMax(newMax);
        part.setStock(newStock);
        if(part instanceof InHousePart) {
            ((InHousePart)part).setMachineId(newMachineId);
        } else if(part instanceof OutSourcedPart) {
            ((OutSourcedPart)part).setCompanyName(newCompanyName);
        }
        inventory.addPart(part);

        toMainForm(actionEvent);
    }

    public void toMainForm(ActionEvent actionEvent) throws IOException {
        if(outsourcedRadioButton.isSelected()) {
            System.out.println("Outsourced");
        }
        if(inHouseRadioButton.isSelected()) {
            System.out.println("In House");
        }

        Parent root = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("MainForm");
        stage.setScene(scene);
        stage.show();
    }

    public static void setPart(Part _part) {
        part = _part;
    }
}
