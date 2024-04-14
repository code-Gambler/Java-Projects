/**********************************************
Workshop 4&5
Course:APD545 - Winter 2024
Last Name: Pillay
First Name:Steven David
ID:162218218
Section:ZAA
This assignment represents my own work in accordance with Seneca Academic Policy.
Signature
Date:30-03-2024
**********************************************/
package model;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private transient ObservableList<Part> productParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    public ArrayList<Part> PartList;
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    public Product() {
        this(0, null, 0.00, 0, 0, 0);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void addPart(Part part) {
        productParts.add(part);
    }

    public void addProductPart(ObservableList<Part> part) {
        this.productParts.addAll(part);
    }

    public ObservableList<Part> getProductParts() {
        return productParts;
    }

	/**
	 * @param productParts the productParts to set
	 */
//	public void setProductParts(ObservableList<Part> productParts) {
//	}

	public void setProductParts(ObservableList<Part> partList2) {
		// TODO Auto-generated method stub
		this.productParts = partList2;
		
	}

}