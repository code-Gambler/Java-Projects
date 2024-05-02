/**********************************************
Workshop 5&6
Course:APD545 - Winter 2024
Last Name: Pillay
First Name:Steven David
ID:162218218
Section:ZAA
This assignment represents my own work in accordance with Seneca Academic Policy.
Signature
Date:14-04-2024
**********************************************/
package data;
import java.io.*;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

public class FileHandler {

    // Save objects to file
    public static void saveObjects(String filePath, Object object) {
    	File f = new File(filePath);
    	
    	//If the file is empty write the file header using class ObjectOutputStream 
    	if (f.length() == 0) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath, true))) {
            outputStream.writeObject(object);
            System.out.println("Objects saved successfully to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    	}
    	//If the file is not empty write the file header using custom class MyObjectOutputStream
    	else {
    		try (MyObjectOutputStream outputStream = new MyObjectOutputStream(new FileOutputStream(filePath, true))) {
                outputStream.writeObject(object);
                System.out.println("Objects saved successfully to " + filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
    	}
    }

    public static void writePart(ObservableList<Part> partList) {
        String filePath = "part.dat";
 
        // Save objects
        for (Part part : partList) {
        	saveObjects(filePath, part);
        }
    }
    
    public static void writeProduct(ObservableList<Product> productList) {
        String filePath = "product.dat";
        
        for (Product product : productList) {
        	product.PartList = new ArrayList<Part>();
        	for (Part IndPart: product.getProductParts()) {
        		product.PartList.add(IndPart);
        	}
        }
        
        for (Product product : productList) {
        	saveObjects(filePath, product);
        }
    }
    
    public static ObservableList<Part> loadPart() throws IOException, ClassNotFoundException {
        ObservableList<Part> partList = FXCollections.observableArrayList();
        String filePath = "part.dat";

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            while (true) {
                try {
                    Part part = (Part) inputStream.readObject();
                    System.out.println("Part load successfully:" + part.getName());
                    partList.add(part);
                } catch (EOFException e) {
                	System.out.println("EOL Reached");
                    break;
                }
            }
        } catch (FileNotFoundException e) {
        	System.out.println("FileNotFoundException: The File was not Found:" + filePath);
        } catch (IOException e) {
            throw e;
        }

        return partList;
    }
    
    public static ObservableList<Product> loadProduct() throws IOException, ClassNotFoundException {
        ObservableList<Product> productList = FXCollections.observableArrayList();
        String filePath = "product.dat";

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            while (true) {
                try {
                	ObservableList<Part> partList = FXCollections.observableArrayList();
                    Product product = (Product) inputStream.readObject();
                    System.out.println("Product load successfully:" + product.getName());
                    for (Part part:product.PartList) {
                    	partList.add(part);
                    }
                    product.setProductParts(partList);
                    productList.add(product);
                } catch (EOFException e) {
                	System.out.println("EOL Reached");
                    break;
                }
            }
        } catch (FileNotFoundException e) {
        	System.out.println("FileNotFoundException: The File was not Found:" + filePath);
        } catch (IOException e) {
            throw e;
        }

        return productList;
    }
}
