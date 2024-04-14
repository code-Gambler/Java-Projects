package inventoryApp;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public Label TheLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("Initialized");
    }

    public void OnButtonAction(ActionEvent actionEvent) {
        System.out.println("Button Clicked");
        TheLabel.setText("You Clicked!");
    }
}
