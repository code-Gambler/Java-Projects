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

public class Customer {
	String m_name;
	String m_phone;

	public Customer(String name, String phone) {
		m_name = name;
		m_phone = phone;
	}

	/**
	 * @return the m_name
	 */
	public String getName() {
		return m_name;
	}

	/**
	 * @return the m_phone
	 */
	public String getPhone() {
		return m_phone;
	}
}
