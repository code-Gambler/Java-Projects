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

public enum Crust {
	Normal,
	Thin,
	DeepDish;
	
	public String toString(){
        switch(this){
        case Normal :
            return "Normal";
        case Thin :
            return "Thin";
        case DeepDish :
            return "DeepDish";
        }
        return null;
    }
	
    public static Crust getCrust(String value){
        if(value.equalsIgnoreCase(Normal.toString()))
            return Crust.Normal;
        else if(value.equalsIgnoreCase(Thin.toString()))
            return Crust.Thin;
        else if(value.equalsIgnoreCase(DeepDish.toString()))
            return Crust.DeepDish;
        else
            return null;
    }
}
