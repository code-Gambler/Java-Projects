/**********************************************
Project
Course:APD545 - Winter 2024
Last Name: Pillay
First Name:Steven David
ID:162218218
Section:ZAA
This assignment represents my own work in accordance with Seneca Academic Policy.
Signature: Steven David Pillay
Date:14-04-2024
**********************************************/
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Controller class for the Welcome.fxml.
 */
public class WelcomeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button adminLogin;

    /**
     * Handles the action when the admin login button is clicked.
     * @param event The action event triggered by the button click.
     */
    @FXML
    void handleLoginButton(ActionEvent event) {
        try {
            FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/view/Login.fxml"));
            Parent loginRoot = loginLoader.load();

            Stage loginStage = new Stage();
            loginStage.setTitle("Login");
            loginStage.setScene(new Scene(loginRoot));
            loginStage.show();

            // Close the current window (if needed)
            adminLogin.getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        assert adminLogin != null : "fx:id=\"adminLogin\" was not injected: check your FXML file '/view/Login.fxml'.";

    }

}
