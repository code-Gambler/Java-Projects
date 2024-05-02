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
package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.Serializable;

public class Inventory implements Serializable{
    /**
	 * 
	 */ 
	private static final long serialVersionUID = 1L;
	private static final ObservableList<Part> allParts = FXCollections.observableArrayList();
    public static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static int partID = 0;
    private static int prodID = 0;

    public static void addPart(Part part) {
        allParts.add(part);
    }

    public static void addProduct(Product product) {
        allProducts.add(product);
    }

    public static Part findPartByID(int partID) {
        Part foundPart = null;
        for (Part part : allParts) {
            if (partID == part.getId()) {
                foundPart = part;
            }
        }
        return foundPart;
    }

    public static ObservableList<Part> findPartByName(String partName) {
        ObservableList<Part> parts = FXCollections.observableArrayList();
        for (Part part : allParts) {
            if (part.getName().toLowerCase().contains(partName.toLowerCase()) ||
                    (String.valueOf(part.getId()).contains(partName))) {
                parts.add(part);
            }
        }
        return parts;
    }

    public static void modifyPart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    public static boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);
    }

    public static Product findProductByID(int productID) {
        Product foundProduct = null;
        for (Product product : allProducts) {
            if (productID == product.getId()) {
                foundProduct = product;
            }
        }
        return foundProduct;
    }

    public static ObservableList<Product> findProductByName(String productName) {
        ObservableList<Product> products = FXCollections.observableArrayList();
        for (Product product : allProducts){
            if(product.getName().toLowerCase().contains(productName.toLowerCase())
                    ||
                    (String.valueOf(product.getId()).contains(productName))){
                products.add(product);
            }
        }
        return products;
    }

    public static int newPartID() {
        return ++partID;
    }

    public static int newProdID() {
        return ++prodID;
    }

    public static void modifyProduct(int index, Product selectedProduct) {
        allProducts.set(index, selectedProduct);
    }

    public static boolean deleteProduct(Product selectedProduct) {
        return allProducts.remove(selectedProduct);
    }

    public static void updatePart(int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }

    public static ObservableList<Part> getPartList() {
        return allParts;
    }

    public static ObservableList<Product> getProductList() {
        return allProducts;
    }

	public static void clear() {
		
		allParts.clear();
		allProducts.clear();
	}
}