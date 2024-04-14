package inventoryApp;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;


import java.io.IOException;

//import static jdk.vm.ci.aarch64.AArch64.v1;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
        primaryStage.setTitle("Inventory Management System");
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static Inventory inventory = new Inventory();

    public static void main(String[] args) {
        InHousePart brakes = new InHousePart(1, "Brakes",15.00, 15, 0, 100, 1);
        InHousePart wheel = new InHousePart(2, "Wheel",11.00, 16, 0, 100, 2);
        InHousePart seat = new InHousePart(3, "Seat",15.00, 10, 0, 100, 3);

        inventory.addPart(brakes);
        inventory.addPart(wheel);
        inventory.addPart(seat);

        Product toyota = new Product(1, "Toyota",15.00, 15, 0, 100);
        Product ferrari = new Product(2, "Ferrari",11.00, 16, 0, 100);
        Product sedan = new Product(3, "Sedan",15.00, 10, 0, 100);

        for(Part part : inventory.getAllParts()) {
            toyota.addAssociatedPart(part);
            ferrari.addAssociatedPart(part);
            sedan.addAssociatedPart(part);
        }

        inventory.addProduct(toyota);
        inventory.addProduct(ferrari);
        inventory.addProduct(sedan);

        launch(args);
    }
}
