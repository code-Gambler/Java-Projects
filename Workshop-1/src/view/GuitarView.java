package view;
import java.util.*;

import controller.HomeController;
public class GuitarView {

	private HomeController hc;
	
	
	public GuitarView() {
		super();
		this.hc = new HomeController();
		hc.setGuitar(this);
	}

	public void CreateGuitar(Scanner scanner) {
		    System.out.print("Enter the price for Guitar: ");
		    double price = scanner.nextDouble();
		    hc.createGuitar(price);
	}
	
	public void ShowGuitar() {
	    System.out.print("Guitar’s cost is: $");
	    System.out.println(hc.getGuitarPrice());
	    System.out.print("Guitar is played: ");
	    System.out.println(hc.getGuitarHowToPlay());
	    System.out.print("Guitar fixing: ");
	    System.out.println(hc.getGuitarHowToFix());
	    System.out.print("Guitar pitch type: ");
	    System.out.println(hc.getGuitarPitchType());
	    hc.sortExp();
	}
}
