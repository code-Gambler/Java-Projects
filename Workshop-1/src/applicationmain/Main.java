package applicationmain;
import java.util.Scanner;

import view.*;

public class Main {

	public static void main(String[] args) {
		    GuitarView gc = new GuitarView();
		    HarpView hc = new HarpView();
		    DrumView dc = new DrumView();
		    XylophoneView xc = new XylophoneView();
		    FluteView fc = new FluteView();
		    UtilsView uc = new UtilsView();
		    
		    try(Scanner scanner = new Scanner(System.in)){
		    	System.out.println("--: Requirement 1 :--");
		    	dc.CreateDrum(scanner);
		    	fc.CreateFlute(scanner);
		    	gc.CreateGuitar(scanner);
			    hc.CreateHarp(scanner);
			    xc.CreateXylophone(scanner);
			    System.out.print("\n");
			    System.out.println("--: Requirement 2 :--");
			    uc.showExpensiveInstrument();
			    System.out.print("\n");
			    System.out.println("--: Requirement 3 :--");
			    uc.showAllInstrumentDesOrder();
			    System.out.print("\n");
			    System.out.println("--: Requirement 4 :--");
			    uc.printFamily(scanner);
		    }
		    
	}
}
