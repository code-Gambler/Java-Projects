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

public class Topping {
	private String m_name;
	private double m_price;
	
	public Topping(String name, double price) {
		m_name = name;
		m_price = price;
	}

	/**
	 * @return the m_name
	 */
	public String getName() {
		return m_name;
	}

	/**
	 * @return the m_price
	 */
	public double getPrice() {
		return m_price;
	}

}
