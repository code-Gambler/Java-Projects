package view;
import java.util.*;

import controller.HomeController;
public class FluteView {

	private HomeController hc;
	
	
	public FluteView() {
		super();
		this.hc = new HomeController();
		hc.setFlute(this);
	}

	public void CreateFlute(Scanner scanner) {
		    System.out.print("Enter the price for Flute: ");
		    double price = scanner.nextDouble();
		    hc.createFlute(price);
	}
	
	public void ShowFlute() {
	    System.out.print("Flute’s cost is: $");
	    System.out.println(hc.getFlutePrice());
	    System.out.print("Flute is played: ");
	    System.out.println(hc.getFluteHowToPlay());
	    System.out.print("Flute pitch type: ");
	    System.out.println(hc.getFlutePitchType());
	}
}
