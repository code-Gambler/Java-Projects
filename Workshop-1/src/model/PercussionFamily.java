package model;
import infrastructure.*;
public abstract class PercussionFamily extends MusicalInstrument implements IPlayable, IFixable{

	PercussionFamily(double price){
		super(price);
	}
	public double getPrice() {
		return super.getPrice();
	}
	
	public String getFamilyName() {
		return "Percussion";
	}
}
