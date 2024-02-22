package model;
//import java.util.*;
public abstract class MusicalInstrument implements Comparable<MusicalInstrument>{

	public double price;
	public abstract String MakeSound();
	public abstract double GetPrice();
	public abstract String GetPitchType();
	public abstract String HowToPlay();
	public abstract String HowToFix();
	public abstract String getFamily();
	
	MusicalInstrument(double price){
		this.price = price;
	}
	// Implement the Comparable interface
	@Override 
	public int compareTo(MusicalInstrument value)
	{
		return (int)(value.getPrice() - this.price);
	}
	public double getPrice() {
		return this.price;
	}
	
	@Override
	public abstract String toString();
}
