package view;
import java.util.*;

import controller.HomeController;
public class DrumView {

	private HomeController hc;
	
	
	public DrumView() {
		super();
		this.hc = new HomeController();
		hc.setDrum(this);
	}

	public void CreateDrum(Scanner scanner) {
		    System.out.print("Enter the price for Drum: ");
		    double price = scanner.nextDouble();
		    hc.createDrum(price);
	}
	
	public void ShowDrum() {
	    System.out.print("Drum’s cost is: $");
	    System.out.println(hc.getDrumPrice());
	    System.out.print("Drum is played: ");
	    System.out.println(hc.getDrumHowToPlay());
	    System.out.print("Drum fixing: ");
	    System.out.println(hc.getDrumHowToFix());
	    System.out.print("Drum pitch type: ");
	    System.out.println(hc.getDrumPitchType());
	}
}
