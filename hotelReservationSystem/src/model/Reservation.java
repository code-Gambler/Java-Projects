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

public class Reservation {
    private int bookID;
    private String bookDate;
    private String guestEmail;
    private int numOfRoomsBooked;
    private int numOfDaysBooked;
    private double ratePerNight;
    private String roomType;
    
    public Reservation(int bookID, String guest, int numRooms, int days, double rate, String roomType) { 
        this.bookID = bookID;
        this.guestEmail = guest;
        this.numOfRoomsBooked = numRooms;
        this.numOfDaysBooked = days;
        this.ratePerNight = rate;
        this.roomType = roomType;
    }

    // Getter and Setter methods for each property
    
    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getBookDate() {
        return bookDate;
    }

    public void setBookDate(String bookDate) {
        this.bookDate = bookDate;
    }

    public String getGuestEmail() {
        return this.guestEmail;
    }

    public void setGuest(String guest) {
        this.guestEmail = guest;
    }

    public int getNumOfRoomsBooked() {
        return numOfRoomsBooked;
    }

    public void setNumOfRoomsBooked(int numOfRoomsBooked) {
        this.numOfRoomsBooked = numOfRoomsBooked;
    }

    public int getNumOfDaysBooked() {
        return numOfDaysBooked;
    }

    public void setNumOfDaysBooked(int numOfDaysBooked) {
        this.numOfDaysBooked = numOfDaysBooked;
    }

    public double getRatePerNight() {
        return ratePerNight;
    }

    public void setRatePerNight(double ratePerNight) {
        this.ratePerNight = ratePerNight;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
}
