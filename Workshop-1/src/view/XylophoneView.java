package view;
import java.util.*;

import controller.HomeController;
public class XylophoneView {

	private HomeController hc;
	
	
	public XylophoneView() {
		super();
		this.hc = new HomeController();
		hc.setXylophone(this);
	}

	public void CreateXylophone(Scanner scanner) {
		    System.out.print("Enter the price for Xylophone: ");
		    double price = scanner.nextDouble();
		    hc.createXylophone(price);
	}
	
	public void ShowXylophone() {
	    System.out.print("Xylophone’s cost is: $");
	    System.out.println(hc.getXylophonePrice());
	    System.out.print("Xylophone is played: ");
	    System.out.println(hc.getXylophoneHowToPlay());
	    System.out.print("Xylophone fixing: ");
	    System.out.println(hc.getXylophoneHowToFix());
	    System.out.print("Xylophone pitch type: ");
	    System.out.println(hc.getXylophonePitchType());
	}
}
