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
package controller;
import model.*;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class HomeController {
	ArrayList<Order> m_orders;
	String m_size, m_crust, m_name, m_phoneNumber;
	int m_quantity;
	ArrayList<Topping> m_toppings;
	@FXML
	private RadioButton small, medium, large, extraLarge,
						normal, thin, deepDish;
	@FXML
	private CheckBox pineapple, mushroom, extraCheese, driedShrimps,
					 anchovies, sunDriedTomatoes, bacon, spinach, 
					 roastedGarlic, ham, jalapeno, groundBeef, 
					 shreddedChicken, grilledChicken, pepperoni,
					 dacon;
	@FXML
	private TextField name, phone, quantity;
	@FXML
	private TextArea summary;

	public HomeController() {
		m_toppings = new ArrayList<Topping>();
		m_orders = new ArrayList<Order>();
		m_quantity = 0;
		m_size = "";
		m_crust = "";
	}
	
	private void setToppings() {
		if(pineapple.isSelected()) {
			Topping topping = new Topping("Pineapple", 1.10);
			m_toppings.add(topping);
		}
		if(mushroom.isSelected()) {
			Topping topping = new Topping("Mushroom", 1.10);
			m_toppings.add(topping);
		}
		if(extraCheese.isSelected()) {
			Topping topping = new Topping("Extra Cheese", 1.10);
			m_toppings.add(topping);
		}
		if(driedShrimps.isSelected()) {
			Topping topping = new Topping("Dried Shrimps", 1.10);
			m_toppings.add(topping);
		}
		if(anchovies.isSelected()) {
			Topping topping = new Topping("Anchovies", 1.10);
			m_toppings.add(topping);
		}
		if(sunDriedTomatoes.isSelected()) {
			Topping topping = new Topping("Sun Dried Tomatoes", 1.10);
			m_toppings.add(topping);
		}
		if(dacon.isSelected()) {
			Topping topping = new Topping("Dacon", 1.10);
			m_toppings.add(topping);
		}
		if(spinach.isSelected()) {
			Topping topping = new Topping("Spinach", 1.10);
			m_toppings.add(topping);
		}
		if(roastedGarlic.isSelected()) {
			Topping topping = new Topping("Roasted Garlic", 1.10);
			m_toppings.add(topping);
		}
		if(jalapeno.isSelected()) {
			Topping topping = new Topping("Jalapeno", 1.10);
			m_toppings.add(topping);
		}
		if(groundBeef.isSelected()) {
			Topping topping = new Topping("Ground Beef", 2.15);
			m_toppings.add(topping);
		}
		if(shreddedChicken.isSelected()) {
			Topping topping = new Topping("Shredded Chicken", 2.15);
			m_toppings.add(topping);
		}
		if(grilledChicken.isSelected()) {
			Topping topping = new Topping("Grilled Chicken", 2.15);
			m_toppings.add(topping);
		}
		if(pepperoni.isSelected()) {
			Topping topping = new Topping("Pepperoni", 2.15);
			m_toppings.add(topping);
		}
		if(ham.isSelected()) {
			Topping topping = new Topping("Ham", 2.15);
			m_toppings.add(topping);
		}
		if(bacon.isSelected()) {
			Topping topping = new Topping("Bacon", 2.15);
			m_toppings.add(topping);
		}
	}
	private void clearToppings() {
		if(pineapple.isSelected()) {
			pineapple.setSelected(false);
		}
		if(mushroom.isSelected()) {
			mushroom.setSelected(false);;
		}
		if(extraCheese.isSelected()) {
			extraCheese.setSelected(false);
		}
		if(driedShrimps.isSelected()) {
			driedShrimps.setSelected(false);
		}
		if(anchovies.isSelected()) {
			anchovies.setSelected(false);
		}
		if(sunDriedTomatoes.isSelected()) {
			sunDriedTomatoes.setSelected(false);
		}
		if(dacon.isSelected()) {
			dacon.setSelected(false);
		}
		if(spinach.isSelected()) {
			spinach.setSelected(false);
		}
		if(roastedGarlic.isSelected()) {
			roastedGarlic.setSelected(false);
		}
		if(jalapeno.isSelected()) {
			jalapeno.setSelected(false);
		}
		if(groundBeef.isSelected()) {
			groundBeef.setSelected(false);
		}
		if(shreddedChicken.isSelected()) {
			shreddedChicken.setSelected(false);
		}
		if(grilledChicken.isSelected()) {
			grilledChicken.setSelected(false);
		}
		if(pepperoni.isSelected()) {
			pepperoni.setSelected(false);
		}
		if(ham.isSelected()) {
			ham.setSelected(false);
		}
		if(bacon.isSelected()) {
			bacon.setSelected(false);
		}
	}
	
	public void setSize(ActionEvent e) {
		if(small.isSelected()) {
			m_size = "Small";
		}
		else if(medium.isSelected()) {
			m_size = "Medium";
		}
		else if(large.isSelected()) {
			m_size = "Large";
		}
		else if(extraLarge.isSelected()) {
			m_size = "ExtraLarge";
		}
	}
	
	public void setCrust(ActionEvent e) {
		if(normal.isSelected()) {
			m_crust = "Normal";
		}
		else if(thin.isSelected()) {
			m_crust = "Thin";
		}
		else if(deepDish.isSelected()) {
			m_crust = "DeepDish";
		}		
	}
	
	public void placeOrder(ActionEvent e) {
		if (m_size == "") {
			Alert noSize = new Alert(AlertType.ERROR);
			noSize.setTitle("No Size Selected");
			noSize.setHeaderText("Please Select a Size For your Pizza!");
			noSize.showAndWait();
		}
		else if (m_crust == "") {
			Alert noCrust = new Alert(AlertType.ERROR);
			noCrust.setTitle("No Crust Selected");
			noCrust.setHeaderText("Please Select a Crust for your Pizza!");
			noCrust.showAndWait();
		}
		else if (name.getText().isEmpty()) {
			Alert noName = new Alert(AlertType.ERROR);
			noName.setTitle("Missing Name");
			noName.setHeaderText("Please Enter Your Name!");
			noName.showAndWait();
		}
		else if (phone.getText().isEmpty()) {
			Alert noPhone = new Alert(AlertType.ERROR);
			noPhone.setTitle("Missing Phone Number");
			noPhone.setHeaderText("Please Enter Your Phone Number!");
			noPhone.showAndWait();
		}
		else if (quantity.getText().isEmpty()) {
			Alert noQuantity = new Alert(AlertType.ERROR);
			noQuantity.setTitle("No Quantity Enter");
			noQuantity.setHeaderText("Please enter how many pizza you'd like!");
			noQuantity.showAndWait();
		}
		else {
			Alert orderConfirmation = new Alert(AlertType.CONFIRMATION);
			orderConfirmation.setTitle("Place Order");
			orderConfirmation.setHeaderText("You are about to place the order");
			if (orderConfirmation.showAndWait().get() == ButtonType.OK) {
				setToppings();
				m_name = name.getText();
				m_phoneNumber = phone.getText();
				m_quantity = Integer.parseInt(quantity.getText());
				Order currentOrder = new Order(m_crust, m_size, m_toppings, m_name, m_phoneNumber, m_quantity);
				m_orders.add(currentOrder);
				m_orders.getLast().calculatePrice();
				summary.clear();
				summary.appendText("Customer Name: " + m_orders.getLast().getCustomer().getName() + "\n");
				summary.appendText("Phone: " + m_orders.getLast().getCustomer().getPhone() + "\n");
				summary.appendText("Pizza Type: " + m_orders.getLast().getPizza().getCrust() + "\n");
				summary.appendText("Pizza Size: " + m_orders.getLast().getPizza().getSize() + "\n");
				summary.appendText("Quantity: " + m_orders.getLast().getQuantity() + "\n");
				summary.appendText("Total Before Tax: " + String.format("%.2f", m_orders.getLast().getPrice()) + "\n");
				summary.appendText("Total to be paid: " + String.format("%.2f", m_orders.getLast().getPrice()*1.13) + "\n");
				clear();
		}
		}
	}
	
	private void clearSize() {
		if(small.isSelected()) {
			small.setSelected(false);
		}
		else if(medium.isSelected()) {
			medium.setSelected(false);
		}
		else if(large.isSelected()) {
			large.setSelected(false);
		}
		else if(extraLarge.isSelected()) {
			extraLarge.setSelected(false);
		}
	}
	
	private void clearCrust() {
		if(normal.isSelected()) {
			normal.setSelected(false);
		}
		else if(thin.isSelected()) {
			thin.setSelected(false);
		}
		else if(deepDish.isSelected()) {
			deepDish.setSelected(false);
		}
	}
	
	private void clearTextFields() {
		name.clear();
		phone.clear();
		quantity.clear();
	}
	
	public void clear(ActionEvent e) {
		clearSize();
		clearCrust();
		clearToppings();
		clearTextFields();
		m_size = "";
		m_crust = "";
	}
	
	public void clear() {
		clearSize();
		clearCrust();
		clearToppings();
		clearTextFields();
		m_size = "";
		m_crust = "";
	}
}
