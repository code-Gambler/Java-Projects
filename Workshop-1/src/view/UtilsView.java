package view;
import java.util.*;

import controller.HomeController;

public class UtilsView {
	private HomeController hc;
	
	public UtilsView() {
		super();
		this.hc = new HomeController();
	}
	
	public void showExpensiveInstrument() {
		System.out.print("The Most Expensive Instrument is: ");
		System.out.println(hc.getExpensiveInstrument());
		hc.showExpensiveInstrument();
	}
	
	public void showAllInstrumentDesOrder() {
		System.out.println("Instruments in price Descending Order:");
		System.out.println(hc.instrumentsInDesOrder().toString());
	}
	
	public void printFamily(Scanner scanner) {
	    System.out.print("Enter an instrument family: ");
	    String family = scanner.next();
	    ArrayList<String> familySounds = hc.familySounds(family);
	    for (String sounds : familySounds) {
	    	System.out.println(sounds);
		}
	    
	}

}
