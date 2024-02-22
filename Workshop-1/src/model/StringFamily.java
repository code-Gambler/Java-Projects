package model;
import infrastructure.*;
public abstract class StringFamily extends MusicalInstrument implements IPlayable, IFixable{

	StringFamily(double price){
		super(price);
	}
	
	public double getPrice() {
		return super.getPrice();
	}
	
	public String getFamilyName() {
		return "String";
	}
}
