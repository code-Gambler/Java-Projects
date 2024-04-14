/**********************************************
Workshop #3
Course:APD545 - Semester Winter 2024
Last Name:Pillay
First Name:Steven
ID:162218218
Section:ZAA
This assignment represents my own work in accordance with Seneca Academic Policy.
Signature
Date:08-03-2024
Sources: https://youtu.be/9XJicRt_FaI?si=mxZjsIWIjGk9lvMX
**********************************************/
package controller;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import model.Loan;

public class HomeController {

	private List<Loan> m_previousRates = new ArrayList<>();
	
	@FXML
    private Label paymentTime, emiFrequency;
	
	@FXML
    private ComboBox<String> vehicleType, vehicleAge, loanFrequency;
	
	@FXML
    private TextField vehiclePrice, downPayment, interestRate, calculatedEmi;
	
	@FXML
    private Slider paymentDuration;
	
	@FXML
	public void initialize() {
        // Set the Options
        vehicleType.getItems().addAll("Car", "Truck", "Van");
        vehicleAge.getItems().addAll("New", "Used");
        loanFrequency.getItems().addAll("Weekly", "Bi-weekly", "Monthly");
        
        // Set the Slider's Attributes
        paymentDuration.setMin(12);
        paymentDuration.setMax(96);
        paymentDuration.setMajorTickUnit(12);
        paymentDuration.setShowTickMarks(true);
        paymentDuration.setShowTickLabels(true);
        paymentDuration.valueProperty().addListener((observable, oldValue, newValue) -> {
            int selectedValue = newValue.intValue();
            paymentTime.setText(selectedValue + " months");
        });
    }
	
	public void clearFields() {
	    vehiclePrice.clear();
	    downPayment.clear();
	    interestRate.clear();
	    calculatedEmi.clear();
	    paymentTime.setText("");
	    vehicleType.setValue(null);
	    vehicleAge.setValue(null);
	    loanFrequency.setValue(null);
	}
	
	public void handleCalculateButton() {
		
		// Get input values from the fields
	    String chosenVehicleType = vehicleType.getValue();
	    String chosenVehicleAge = vehicleAge.getValue();
	    String chosenLoanFrequency = loanFrequency.getValue();
	    String enteredPriceText = vehiclePrice.getText();
	    String enteredDownPaymentText = downPayment.getText();
	    String enteredInterestRateText = interestRate.getText();
	    
	    double price, downPaymentAmount, interest;

	    if (chosenVehicleType == null) {
	    	showError("Missing Input", "Please fill in Vehicle Type!");
	    	return;
	    }
	    else if (chosenVehicleAge == null) {
	    	showError("Missing Input", "Please fill in all How is your Vehicle!");
	    	return;
	    }
	    else if (chosenLoanFrequency == null) {
	    	showError("Missing Input", "Please select your Loan's Frequency!");
	    	return;
	    }
	    else if (enteredPriceText.isEmpty()) {
	    	showError("Missing Input", "Please fill in your Vehicle's Price!");
	    	return;
	    }
	    else if (enteredDownPaymentText.isEmpty()) {
	    	showError("Missing Input", "Please fill in your Down Payment!");
	    	return;
	    }
	    else if (enteredInterestRateText.isEmpty()) {
	    	showError("Missing Input", "Please Enter your Interest Rate!");
	    	return;
	    }


	    try {
	        price = Double.parseDouble(enteredPriceText);
	        downPaymentAmount = Double.parseDouble(enteredDownPaymentText);
	        interest = Double.parseDouble(enteredInterestRateText);
	    } catch (NumberFormatException e) {
	        showError("Invalid Input", "Please provide valid numeric input values.");
	        return;
	    }
	    
	    int durationInMonths = (int) paymentDuration.getValue();

	    if (price <= 0 || downPaymentAmount < 0 || interest <= 0 || durationInMonths < 1) {
	    	showError("Invalid Input", "Please provide valid input values.");
	        return;
	    }
	
		double loanPayment = calculateLoanPayment(price, downPaymentAmount, interest, durationInMonths);

        Loan newLoan = new Loan(chosenVehicleType, chosenVehicleAge, price, downPaymentAmount,
        		interest, durationInMonths, chosenLoanFrequency, loanPayment);

        m_previousRates.add(newLoan);
        calculatedEmi.setText(String.format("%.2f", loanPayment));
        emiFrequency.setText(chosenLoanFrequency);
	}
	
	public void handleSavedRatesButton() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Saved Rates");
        alert.setHeaderText(null);

        TextArea textArea = new TextArea();
        textArea.setEditable(false);

        for (Loan loan : m_previousRates) {
            String rateInfo = String.format(
                "Vehicle Type: %s\nVehicle Age: %s\nPrice: %s\nLoan Payment: $%.2f\nFrequency: %s\n\n",
                loan.getVehicleType(), loan.getVehicleAge(), loan.getPrice(), loan.getLoanPayment() , loan.getLoanFrequency()
            );
            textArea.appendText(rateInfo);
        }

        alert.getDialogPane().setContent(textArea);

        alert.showAndWait();
    }
	
	private double calculateLoanPayment(double price, double downPayment, double annualInterestRate, int durationInMonths) {
	    double monthlyInterestRate = (annualInterestRate / 12) / 100;
	    double loanAmount = price - downPayment;
	    double monthlyPayment = (loanAmount * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -durationInMonths));
	    return monthlyPayment;
	}
	
	private void showError(String title, String content) {
	    Alert alert = new Alert(AlertType.ERROR);
	    alert.setTitle(title);
	    alert.setHeaderText(null);
	    alert.setContentText(content);
	    alert.showAndWait();
	}

	
}
