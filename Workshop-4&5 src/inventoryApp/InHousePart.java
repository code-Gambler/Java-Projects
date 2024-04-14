package inventoryApp;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class InHousePart extends Part {

    private int machineId;

    public InHousePart(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    public int getMachineId() { return machineId; }

    public void setMachineId(int machineId) { this.machineId = machineId; }
}
