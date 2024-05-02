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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * A simple multi-threaded server application that accepts connections from clients
 * and broadcasts messages from one client to all connected clients.
 */

public class Server extends Application {
	// TextArea for displaying log messages
	private TextArea ta = new TextArea();
	
	// ServerSocket for accepting client connections
    private ServerSocket serverSocket;
    
    // Hashtable to store output streams of connected clients
    private Hashtable < Socket, DataOutputStream > outputStreams = new Hashtable < > ();

    /**
     * The main entry point for the JavaFX application.
     */
    @Override
    public void start(Stage ps) throws Exception {
        ta.setWrapText(true);
        
        Scene scene = new Scene(new ScrollPane(ta), 400, 200);
        ps.setTitle("Server");
        ps.setScene(scene);
        ps.show();
        
        // Start a new thread to listen for client connections
        new Thread(() -> listen()).start();
    }

    /**
     * Listens for client connections and handles them in separate threads.
     */
    private void listen() {
        try {
        	// Create a server socket
            serverSocket = new ServerSocket(13000);
            
            // Display server startup message
            Platform.runLater(() ->
                ta.appendText("MultiThreadServer started at " + new Date() + '\n'));
            
            // Continuously accept client connections
            while (true) {
            	
                // Listen for a new connection requests
                Socket socket = serverSocket.accept();
                
                // Display connection information
                Platform.runLater(() ->
                    ta.appendText("Connection from " + socket + " at " + new Date() + '\n'));
                
                // Create output stream for the client
                DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
                outputStreams.put(socket, dout);
                
                // Create a new thread to handle client communication
                new ServerThread(this, socket);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Returns an enumeration of output streams for all connected clients.
     */
    Enumeration < DataOutputStream > getOutputStreams() {
        return outputStreams.elements();
    }

    /**
     * Sends a message to all connected clients.
     */
    void sendToAll(String message) {
        for (Enumeration < DataOutputStream > e = getOutputStreams(); e.hasMoreElements();) {
            DataOutputStream dout = (DataOutputStream) e.nextElement();
            try {
            	// Write message to output stream
                dout.writeUTF(message);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Inner class representing a thread that handles communication with a single client.
     */
    class ServerThread extends Thread {
        private Server server;
        private Socket socket;
        
        /**
         * Constructs a new ServerThread.
         */
        public ServerThread(Server server, Socket socket) {
            this.socket = socket;
            this.server = server;
            start(); // Start the thread
        }

        /**
         * Runs the thread, continuously reading messages from the client and broadcasting them to all clients.
         */
        public void run() {
            try {
            	// Create data input stream for receiving messages from the client
                DataInputStream din = new DataInputStream(socket.getInputStream());
                // Continuously serve the client
                while (true) {
                	// Read message from the client
                	String string = din.readUTF();
                	
                	// Send message to all clients
                    server.sendToAll(string);
                    
                    // Add message to the server log
                    ta.appendText(string + '\n');
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    /**
     * The main method, launches the JavaFX application.
     */
    public static void main(String[] args) {
        launch(args);
    }
}