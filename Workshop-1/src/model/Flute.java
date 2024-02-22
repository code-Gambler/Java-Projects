package model;

public class Flute extends WoodwindFamily {

//	private double price;
	private String pitchType;
	private String HowToPlay;
	private String MakeSound;
	public Flute(double price) {
		super(price);
		this.pitchType="Fundamental pitch is middle C";
		this.HowToPlay="by blowing into the flute";
		this.MakeSound="guiding a stream of air";
//		this.price = price;
	}

	@Override
	public String HowToPlay() {
		return HowToPlay;
	}
	
	@Override
	public String HowToFix() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String MakeSound() {
		// TODO Auto-generated method stub
		return MakeSound;
	}

	@Override
	public double GetPrice() {
		// TODO Auto-generated method stub
		return super.getPrice();
	}

	@Override
	public String GetPitchType() {
		// TODO Auto-generated method stub
		return pitchType;
	}
	
	@Override
	public String toString() {
		return "Flute";
	}
	
	public String getFamily() {
		return super.getFamilyName();
	}

}
