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

public class Pizza {
	Size m_size;
	Crust m_crust;
	ArrayList<Topping> m_toppings;
	
	public Pizza(String size, String crust, ArrayList<Topping> toppings) {
		m_size = Size.getSize(size);
		m_crust = Crust.getCrust(crust);
		m_toppings = (ArrayList<Topping>) toppings.clone();
	}

	/**
	 * @return the m_size
	 */
	public String getSize() {
		return m_size.toString();
	}

	/**
	 * @return the m_crust
	 */
	public String getCrust() {
		return m_crust.toString();
	}
	
	/**
	 * @return the m_toppings
	 */
	public ArrayList<Topping> getToppings() {
		return m_toppings;
	}
}
