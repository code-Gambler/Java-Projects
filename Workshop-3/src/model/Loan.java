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
package model;

public class Loan {
    private String m_vehicleType;
    private String m_vehicleAge;
    private double m_price;
    private double m_downPaymentAmount;
    private double m_interestRate;
    private int m_durationInMonths;
    private String m_loanFrequency;
    private double m_loanPayment;

    public Loan(String vehicleType, String vehicleAge, double price, double downPaymentAmount, double interestRate,
                int durationInMonths, String loanFrequency, double loanPayment) {
        m_vehicleType = vehicleType;
        m_vehicleAge = vehicleAge;
        m_price = price;
        m_downPaymentAmount = downPaymentAmount;
        m_interestRate = interestRate;
        m_durationInMonths = durationInMonths;
        m_loanFrequency = loanFrequency;
        m_loanPayment = loanPayment;
    }

	public String getVehicleType() {
		return m_vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		m_vehicleType = vehicleType;
	}

	public String getVehicleAge() {
		return m_vehicleAge;
	}

	public void setVehicleAge(String vehicleAge) {
		m_vehicleAge = vehicleAge;
	}

	public double getPrice() {
		return m_price;
	}

	public void setPrice(double price) {
		m_price = price;
	}

	public double getDownPaymentAmount() {
		return m_downPaymentAmount;
	}

	public void setDownPaymentAmount(double downPaymentAmount) {
		m_downPaymentAmount = downPaymentAmount;
	}

	public double getInterestRate() {
		return m_interestRate;
	}

	public void setInterestRate(double interestRate) {
		m_interestRate = interestRate;
	}

	public int getDurationInMonths() {
		return m_durationInMonths;
	}

	public void setDurationInMonths(int durationInMonths) {
		m_durationInMonths = durationInMonths;
	}

	public String getLoanFrequency() {
		return m_loanFrequency;
	}

	public void setLoanFrequency(String loanFrequency) {
		m_loanFrequency = loanFrequency;
	}

	public double getLoanPayment() {
		return m_loanPayment;
	}

	public void setLoanPayment(double loanPayment) {
		m_loanPayment = loanPayment;
	}

    
}

