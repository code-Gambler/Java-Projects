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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Login;
import model.Database;

/**
 * Controller class for handling control for Login.fxml
 */
public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;
    

    /**
     * Validates user login credentials.
     * @param event The action event triggered by the login button click.
     */
    @FXML
    void handleLoginButton(ActionEvent event) {
        Window owner = loginButton.getScene().getWindow();
        
        if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Missing Credentials", "Please enter both username and password");
            return;
        }

        String username = usernameField.getText();
        String password = passwordField.getText();

        Login login = new Login(username, password);

        Database db = new Database();
        boolean flag = db.validateAdmin(login);
        
        if (flag) {
            showAlert(Alert.AlertType.CONFIRMATION, owner, "Login Successful", "Thank you");
            
            try {
                FXMLLoader mainMenuLoader = new FXMLLoader(getClass().getResource("/view/MainMenu.fxml"));
                Parent mainMenuRoot = mainMenuLoader.load();

                Stage mainMenuStage = new Stage();
                mainMenuStage.setTitle("Main Menu");
                mainMenuStage.setScene(new Scene(mainMenuRoot));
                mainMenuStage.show();

               loginButton.getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
        	showAlert(Alert.AlertType.ERROR, owner, "Wrong Credentials", "Try again");
        }
    }

    /**
     * Displays an alert with the specified type, title, and message.
     * @param alertType The type of alert.
     * @param window The window owner of the alert.
     * @param title The title of the alert.
     * @param message The message to be displayed in the alert.
     */
    private void showAlert(Alert.AlertType alertType, Window window, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(window);
        alert.showAndWait();
		
	}

	@FXML
    void initialize() {
        assert loginButton != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'Login.fxml'.";
        assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'Login.fxml'.";
        assert usernameField != null : "fx:id=\"usernameField\" was not injected: check your FXML file 'Login.fxml'.";

    }

}
