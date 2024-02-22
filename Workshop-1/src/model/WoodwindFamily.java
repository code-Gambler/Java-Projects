package model;
import infrastructure.IPlayable;
public abstract class WoodwindFamily extends MusicalInstrument implements IPlayable{

	WoodwindFamily(double price){
		super(price);
	}
	
	public double getPrice() {
		return super.getPrice();
	}
	
	public String getFamilyName() {
		return "Woodwind";
	}
}
