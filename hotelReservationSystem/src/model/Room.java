/**********************************************
Project
Course:APD545 - Winter 2024
Last Name: Pillay
First Name:Steven David
ID:162218218
Section:ZAA
This assignment represents my own work in accordance with Seneca Academic Policy.
Signature: Steven David Pillay
Date:14-04-2024
**********************************************/
package model;

public class Room {
    private int roomID;
    private String roomType;
    private double rate;

    public Room(int roomID, String roomType, double rate) {
        this.roomID = roomID;
        this.roomType = roomType;
        this.rate = rate;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
