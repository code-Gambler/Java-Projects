package data;
import java.io.*;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;
import data.MyObjectOutputStream;

public class WriteObject {

    // Save objects to file
    public static void saveObjects(String filePath, Object object) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath, true))) {
            outputStream.writeObject(object);
            System.out.println("Objects saved successfully to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load objects from file
    public static Object loadObjects(String filePath) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            Object object = inputStream.readObject();
            System.out.println("Objects loaded successfully from " + filePath);
            return object;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void writePart(ObservableList<Part> partList) {
        // Example usage
        String filePath = "part.dat";
 
        // Save objects
        for (Part part : partList) {
        	saveObjects(filePath, part);
        }
    }
    
    public static void writeProduct(ObservableList<Product> productList) {
        // Example usage
        String filePath = "product.dat";
        
        for (Product product : productList) {
        	product.PartList = new ArrayList<Part>();
        	for (Part IndPart: product.getProductParts()) {
        		product.PartList.add(IndPart);
        	}
        }
        
        // Save objects
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
            // Handle other IO exceptions
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
            // Handle other IO exceptions
            throw e;
        }

        return productList;
    }

//    Part loadedPart = (Part)loadObjects(filePath);
    
//    public static void writeProduct(ObservableList<Product> productList) {
//        // Example usage
//        String filePath = "product.dat";
//        
//        for (Product product : productList) {
//        	product.PartList = new ArrayList<Part>();
//        	for (Part IndPart: product.getProductParts()) {
//        		product.PartList.add(IndPart);
//        	}
//        }
//        
//        // Save objects
//        for (Product product : productList) {
//        	saveObjects(filePath, product);
//        }
//    }
}
