package model;

public class Guitar extends StringFamily {

//	private double price;
	private String pitchType;
	private String HowToPlay;
	private String MakeSound;
	private String HowToFix;
	public Guitar(double price) {
		super(price);
		this.pitchType="Low to high pitch";
		this.HowToPlay="by strumming the strings";
		this.MakeSound="vibrating strings ";
		this.HowToFix="replace the strings";
//		this.price = price;
	}

	@Override
	public String HowToPlay() {
		return HowToPlay;
	}

	@Override
	public String HowToFix() {
		// TODO Auto-generated method stub
		return HowToFix;
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
		return "Guitar";
	}
	
	public String getFamily() {
		return super.getFamilyName();
	}

}
