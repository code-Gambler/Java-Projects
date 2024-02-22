package model;

public class Xylophone extends PercussionFamily {

	private String pitchType;
	private String HowToPlay;
	private String MakeSound;
	private String HowToFix;
	public Xylophone(double price) {
		super(price);
		this.pitchType="Each bar produces different pitch";
		this.HowToPlay="with two mallets";
		this.MakeSound="through resonators";
		this.HowToFix="replace bars";
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
		return "Xylophone";
	}
	
	public String getFamily() {
		return super.getFamilyName();
	}

}
