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
package application;
	
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	private Socket socket;

	private DataOutputStream dataOutputStream;

	private DataInputStream dataInputStream;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("/view/Welcome.fxml"));
			primaryStage.setTitle("Resorts Co.");
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		  try {
	            socket = new Socket("localhost", 13000);
	            dataOutputStream = new DataOutputStream(socket.getOutputStream());
	            dataInputStream = new DataInputStream(socket.getInputStream());
	            new Thread(()->run()).start();
	        }catch(IOException ex) {
	        	System.out.println("Exception Occured");
	            ex.printStackTrace();
	        }
	}
	public void run() {
        try {
            while(true) {
                String text = dataInputStream.readUTF();
            }

        }catch(IOException ex) {
            ex.printStackTrace();
        }
    }
	public static void main(String[] args) {
		launch(args);
	}
}

