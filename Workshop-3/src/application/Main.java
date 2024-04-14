///**********************************************
//Workshop #3
//Course:APD545 - Semester Winter 2024
//Last Name:Pillay
//First Name:Steven
//ID:162218218
//Section:ZAA
//This assignment represents my own work in accordance with Seneca Academic Policy.
//Signature
//Date:08-03-2024
//Sources: https://youtu.be/9XJicRt_FaI?si=mxZjsIWIjGk9lvMX
//**********************************************/
//package application;
//
//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.stage.Stage;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//
//
//public class Main extends Application {
//	@Override
//    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("/view/FrontScreen.fxml"));
//        Scene scene = new Scene(root);
//        primaryStage.setTitle("Loan App");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    public static void main(String[] args) {
//       
//    	launch(args);
//    }
//	
//}


import java.io.*;
public class CopyCharacterExample {
    public static void main(String[] args) throws IOException{
        try{
            BufferedReader bf = new BufferedReader("in.txt");
            BufferedWriter bw = new BufferedWriter("Buff.txt");
            String line;
            while((line = bf.readLine()) != null){
                bw.write(line);
                bw.newline();
            }
            bw.flush();
        }catch(FileNotFoundException fnfe){
            
        }
    }
}