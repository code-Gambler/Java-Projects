package inventoryApp;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class CarPart extends Part implements Initializable {
    public CarPart(int id, String name, double price, int stock, int min, int max) {
        super(id, name, price, stock, min, max);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
