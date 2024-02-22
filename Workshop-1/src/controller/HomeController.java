package controller;
import java.util.ArrayList;
import java.util.*;

import model.*;
import view.*;

public class HomeController {
	private static GuitarView guitar;
	private static HarpView harp;
	private static DrumView drum;
	private static XylophoneView xylophone;
	private static FluteView flute;
	private static ArrayList<MusicalInstrument> instrument = new ArrayList<MusicalInstrument>();

	public void createGuitar(double price) {
		Guitar guitar = new Guitar(price);
		//this.guitar = guitar;
		//this.ArrayList.insert(guitar)
		//this.instrument[0] = guitar;
		HomeController.instrument.add(guitar);
	}
	
	public String getGuitarHowToPlay() {
//		return guitar.HowToPlay();
		//for ArrayList
		//instrument.toString()=Guitar
		//
		for (MusicalInstrument ins : instrument) {
		      if(ins.toString() == "Guitar") {
		    	  return ins.HowToPlay();
		      }
		    }
		return "";
	}

	public String getGuitarHowToFix() {
		// TODO Auto-generated method stub
//		return this.instrument[0].HowToFix();
		for (MusicalInstrument ins : instrument) {
		      if(ins.toString() == "Guitar") {
		    	  return ins.HowToFix();
		      }
		    }
		return "";
	}

	public String getGuitarMakeSound() {
		// TODO Auto-generated method stub
		for (MusicalInstrument ins : instrument) {
		      if(ins.toString() == "Guitar") {
		    	  return ins.MakeSound();
		      }
		    }
		return "";
	}

	public double getGuitarPrice() {
		// TODO Auto-generated method stub
		for (MusicalInstrument ins : instrument) {
		      if(ins.toString() == "Guitar") {
		    	  return ins.getPrice();
		      }
		    }
		return 0;
	}

	public String getGuitarPitchType() {
		// TODO Auto-generated method stub
		for (MusicalInstrument ins : instrument) {
		      if(ins.toString() == "Guitar") {
		    	  return ins.GetPitchType();
		      }
		    }
		return "";
	}

	
	public void createHarp(double price) {
		Harp harp = new Harp(price);
//		this.harp = harp; 
		//this.instrument[1] = harp; 
		HomeController.instrument.add(harp);
	}
	
	public String getHarpHowToPlay() {
//		return guitar.HowToPlay();
		//for ArrayList
		//instrument.toString()=Harp
		//
		for (MusicalInstrument ins : instrument) {
		      if(ins.toString() == "Harp") {
		    	  return ins.HowToPlay();
		      }
		    }
		return "";
	}

	public String getHarpHowToFix() {
		// TODO Auto-generated method stub
//		return this.instrument[0].HowToFix();
		for (MusicalInstrument ins : instrument) {
		      if(ins.toString() == "Harp") {
		    	  return ins.HowToFix();
		      }
		    }
		return "";
	}

	public String getHarpMakeSound() {
		// TODO Auto-generated method stub
		for (MusicalInstrument ins : instrument) {
		      if(ins.toString() == "Harp") {
		    	  return ins.MakeSound();
		      }
		    }
		return "";
	}

	public double getHarpPrice() {
		// TODO Auto-generated method stub
		for (MusicalInstrument ins : instrument) {
		      if(ins.toString() == "Harp") {
		    	  return ins.getPrice();
		      }
		    }
		return 0;
	}

	public String getHarpPitchType() {
		// TODO Auto-generated method stub
		for (MusicalInstrument ins : instrument) {
		      if(ins.toString() == "Harp") {
		    	  return ins.GetPitchType();
		      }
		    }
		return "";
	}

	
	public void createDrum(double price) {
		Drum drum = new Drum(price);
//		this.drum = drum; 
		//this.instrument[2] = drum;
		HomeController.instrument.add(drum);
	}
	
	public String getDrumHowToPlay() {
//		return guitar.HowToPlay();
		//for ArrayList
		//instrument.toString()=Drum
		//
		for (MusicalInstrument ins : instrument) {
		      if(ins.toString() == "Drum") {
		    	  return ins.HowToPlay();
		      }
		    }
		return "";
	}

	public String getDrumHowToFix() {
		// TODO Auto-generated method stub
//		return this.instrument[0].HowToFix();
		for (MusicalInstrument ins : instrument) {
		      if(ins.toString() == "Drum") {
		    	  return ins.HowToFix();
		      }
		    }
		return "";
	}

	public String getDrumMakeSound() {
		// TODO Auto-generated method stub
		for (MusicalInstrument ins : instrument) {
		      if(ins.toString() == "Drum") {
		    	  return ins.MakeSound();
		      }
		    }
		return "";
	}

	public double getDrumPrice() {
		// TODO Auto-generated method stub
		for (MusicalInstrument ins : instrument) {
		      if(ins.toString() == "Drum") {
		    	  return ins.getPrice();
		      }
		    }
		return 0;
	}

	public String getDrumPitchType() {
		// TODO Auto-generated method stub
		for (MusicalInstrument ins : instrument) {
		      if(ins.toString() == "Drum") {
		    	  return ins.GetPitchType();
		      }
		    }
		return "";
	}
	
	public void createXylophone(double price) {
		Xylophone xylophone = new Xylophone(price);
//		this.xylophone = xylophone; 
		//this.instrument[3] = xylophone;
		HomeController.instrument.add(xylophone);
	}
	
	public String getXylophoneHowToPlay() {
//		return guitar.HowToPlay();
		//for ArrayList
		//instrument.toString()=Xylophone
		//
		for (MusicalInstrument ins : instrument) {
		      if(ins.toString() == "Xylophone") {
		    	  return ins.HowToPlay();
		      }
		    }
		return "";
	}

	public String getXylophoneHowToFix() {
		// TODO Auto-generated method stub
//		return this.instrument[0].HowToFix();
		for (MusicalInstrument ins : instrument) {
		      if(ins.toString() == "Xylophone") {
		    	  return ins.HowToFix();
		      }
		    }
		return "";
	}

	public String getXylophoneMakeSound() {
		// TODO Auto-generated method stub
		for (MusicalInstrument ins : instrument) {
		      if(ins.toString() == "Xylophone") {
		    	  return ins.MakeSound();
		      }
		    }
		return "";
	}

	public double getXylophonePrice() {
		// TODO Auto-generated method stub
		for (MusicalInstrument ins : instrument) {
		      if(ins.toString() == "Xylophone") {
		    	  return ins.getPrice();
		      }
		    }
		return 0;
	}

	public String getXylophonePitchType() {
		// TODO Auto-generated method stub
		for (MusicalInstrument ins : instrument) {
		      if(ins.toString() == "Xylophone") {
		    	  return ins.GetPitchType();
		      }
		    }
		return "";
	}
	
	public void createFlute(double price) {
		Flute flute = new Flute(price);
//		this.flute = flute; 
//		this.instrument[4] = flute; 
		HomeController.instrument.add(flute);
	}
	
	public String getFluteHowToPlay() {
//		return guitar.HowToPlay();
		//for ArrayList
		//instrument.toString()=Flute
		//
		for (MusicalInstrument ins : instrument) {
		      if(ins.toString() == "Flute") {
		    	  return ins.HowToPlay();
		      }
		    }
		return "";
	}

	public String getFluteHowToFix() {
		// TODO Auto-generated method stub
//		return this.instrument[0].HowToFix();
		for (MusicalInstrument ins : instrument) {
		      if(ins.toString() == "Flute") {
		    	  return ins.HowToFix();
		      }
		    }
		return "";
	}

	public String getFluteMakeSound() {
		// TODO Auto-generated method stub
		for (MusicalInstrument ins : instrument) {
		      if(ins.toString() == "Flute") {
		    	  return ins.MakeSound();
		      }
		    }
		return "";
	}

	public double getFlutePrice() {
		// TODO Auto-generated method stub
		for (MusicalInstrument ins : instrument) {
		      if(ins.toString() == "Flute") {
		    	  return ins.getPrice();
		      }
		    }
		return 0;
	}

	public String getFlutePitchType() {
		// TODO Auto-generated method stub
		for (MusicalInstrument ins : instrument) {
			if(ins.toString() == "Flute") {
				return ins.GetPitchType();
			}
		}
		return "";
	}
	
	public void sortExp() {
		Collections.sort(instrument);
	}
	
	public String getExpensiveInstrument() {
		this.sortExp();
		return instrument.get(0).toString();
	}
	
	public void showExpensiveInstrument() {
		this.sortExp();
		switch(instrument.get(0).toString()) {
		  case "Guitar":
		    guitar.ShowGuitar();
		    break;
		  case "Harp":
		    harp.ShowHarp();
		    break;
		  case "Drum":
			  drum.ShowDrum();
			  break;
		  case "Xylophone":
			  xylophone.ShowXylophone();
			  break;
		  case "Flute":
			  flute.ShowFlute();
			  break;
		  default:
		    // code block
		}
	}

	/**
	 * @param guitar the guitar to set
	 */
	public void setGuitar(GuitarView guitar) {
		HomeController.guitar = guitar;
	}

	/**
	 * @param harp the harp to set
	 */
	public void setHarp(HarpView harp) {
		HomeController.harp = harp;
	}

	/**
	 * @param drum the drum to set
	 */
	public void setDrum(DrumView drum) {
		HomeController.drum = drum;
	}

	/**
	 * @param xylophone the xylophone to set
	 */
	public void setXylophone(XylophoneView xylophone) {
		HomeController.xylophone = xylophone;
	}

	/**
	 * @param flute the flute to set
	 */
	public void setFlute(FluteView flute) {
		HomeController.flute = flute;
	}
	
	public ArrayList<String> instrumentsInDesOrder() {
		ArrayList<String> instrumentsDesOrder = new ArrayList<String>();
		this.sortExp();
		for (MusicalInstrument ins : instrument) {
			instrumentsDesOrder.add(new String(ins.toString()));
		}
		return instrumentsDesOrder;
	}
	
	//Implement getfamily in all the models 
	public ArrayList<String> familySounds(String FamilyName){
		ArrayList<String> familySounds = new ArrayList<String>();
		for (MusicalInstrument ins : instrument) {
			if (ins.getFamily().equals(FamilyName)) {
				familySounds.add(ins.toString()+ " makes sound " +(new String(ins.MakeSound()))+".");
			}
		}
		return familySounds;
	}
}
