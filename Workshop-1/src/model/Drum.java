package model;

public class Drum extends PercussionFamily {

//	private double price;
	private String pitchType;
	private String HowToPlay;
	private String MakeSound;
	private String HowToFix;
	public Drum(double price) {
		super(price);
		this.pitchType="Sonic pitch";
		this.HowToPlay="by hitting the membrane";
		this.MakeSound="vibrating stretched membrane";
		this.HowToFix="replace the membrane ";
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
		return "Drum";
	}
	
	public String getFamily() {
		return super.getFamilyName();
	}
}
