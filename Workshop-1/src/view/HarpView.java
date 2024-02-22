package view;
import java.util.*;

import controller.HomeController;
public class HarpView {

	private HomeController hc;
	
	
	public HarpView() {
		super();
		this.hc = new HomeController();
		hc.setHarp(this);
	}

	public void CreateHarp(Scanner scanner) {
		    System.out.print("Enter the price for Harp: ");
		    double price = scanner.nextDouble();
		    hc.createHarp(price);
	}
	
	public void ShowHarp() {
	    System.out.print("Harp’s cost is: $");
	    System.out.println(hc.getHarpPrice());
	    System.out.print("Harp is played: ");
	    System.out.println(hc.getHarpHowToPlay());
	    System.out.print("Harp fixing: ");
	    System.out.println(hc.getHarpHowToFix());
	    System.out.print("Harp pitch type: ");
	    System.out.println(hc.getHarpPitchType());
	}
}
