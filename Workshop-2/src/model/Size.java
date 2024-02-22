/**********************************************
Workshop #2
Course:APD545 - Semester Winter 2024
Last Name:Pillay
First Name:Steven
ID:162218218
Section:ZAA
This assignment represents my own work in accordance with Seneca Academic Policy.
Signature
Date:11-02-2024
**********************************************/
package model;

public enum Size {
	Small,
	Medium,
	Large,
	ExtraLarge;
	
	public String toString(){
        switch(this){
        case Small :
            return "Small";
        case Medium :
            return "Medium";
        case Large :
            return "Large";
        case ExtraLarge :
        	return "ExtraLarge";
        }
        return null;
    }
	
    public static Size getSize(String value){
        if(value.equalsIgnoreCase(Small.toString()))
            return Size.Small;
        else if(value.equalsIgnoreCase(Medium.toString()))
            return Size.Medium;
        else if(value.equalsIgnoreCase(Large.toString()))
            return Size.Large;
        else if(value.equalsIgnoreCase(ExtraLarge.toString()))
            return Size.ExtraLarge;
        else
            return null;
    }
}
