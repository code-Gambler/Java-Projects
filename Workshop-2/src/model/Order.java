/**********************************************
Workshop #2
Course:APD545 - Semester Winter 2024
Last Name:Pillay
First Name:Steven
ID:162218218
Section:ZAA
This assignment represents my own work in accordance with Seneca Academic Policy.
Signature
Date:11-02-2024
**********************************************/
package model;

import java.util.ArrayList;

public class Order {
	Pizza m_pizza;
	int m_quantity;
	Customer m_customer;
	double m_price;
	
	public Order(String crust, String size, ArrayList<Topping> toppings, String name, String phone, int quantity) {
		m_pizza = new Pizza(size, crust, toppings);
		m_customer = new Customer(name, phone);
		m_quantity = quantity;
	}
	
	public void calculatePrice() {
		if (m_pizza.getSize() == "Small") {
			m_price += 7.0;
		}
		else if (m_pizza.getSize() == "Medium") {
			m_price += 10.0;
		}
		else if (m_pizza.getSize() == "Large") {
			m_price += 13.0;
		}
		else if (m_pizza.getSize() == "Extra Large") {
			m_price += 15.0;
		}
		
		for (Topping currentTopping: m_pizza.getToppings()) {
			m_price += currentTopping.getPrice();
		}
		
		m_price *= m_quantity;
	}

	/**
	 * @return the m_pizza
	 */
	public Pizza getPizza() {
		return m_pizza;
	}

	/**
	 * @return the m_quantity
	 */
	public int getQuantity() {
		return m_quantity;
	}

	/**
	 * @return the m_customer
	 */
	public Customer getCustomer() {
		return m_customer;
	}

	/**
	 * @return the m_price
	 */
	public double getPrice() {
		return m_price;
	}
}
