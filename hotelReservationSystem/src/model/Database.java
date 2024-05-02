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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Database {

	private static final String DatabaseURL="jdbc:mysql://127.0.0.1:3306/sys?useSSL=false";
	private static final String Username = "root";
	private static final String Password = "APD545ZAA";
	
	
	public boolean validateAdmin(Login login) {
	    String SELECT_QRY = "SELECT * FROM admin_credentials WHERE username = ? AND pass = ?";
	    try (Connection conn = DriverManager.getConnection(DatabaseURL, Username, Password);
	         PreparedStatement ps = conn.prepareStatement(SELECT_QRY)) {

	        ps.setString(1, login.getLoginUsername());
	        ps.setString(2, login.getLoginPassword());

	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            return true;
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return false;
	}
	
    public List<Room> fetchRooms() {
        List<Room> rooms = new ArrayList<>();
        String SELECT_QRY = "SELECT * FROM room WHERE isAvailable = TRUE";
        try (Connection conn = DriverManager.getConnection(DatabaseURL, Username, Password);
             PreparedStatement ps = conn.prepareStatement(SELECT_QRY)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int roomNumber = rs.getInt("roomNumber");
                String roomType = rs.getString("roomType");
                double roomPrice = rs.getDouble("roomPrice");

                Room room = createRoomObject(roomNumber, roomType, roomPrice);
                rooms.add(room);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rooms;
    }

    private Room createRoomObject(int roomNumber, String roomType, double roomPrice) {
        if (roomType.equals("Single Room")) {
            return new SingleRoom(roomNumber, roomPrice);
        } else if (roomType.equals("Double Room")) {
            return new DoubleRoom(roomNumber, roomPrice);
        } else if (roomType.equals("Deluxe Room")) {
            return new DeluxeRoom(roomNumber, roomPrice);
        } else if (roomType.equals("Penthouse")) {
            return new PentHouse(roomNumber, roomPrice);
        }
        return null;
    }
    
    public int countAvailableRooms() {
        int count = 0;
        String COUNT_QRY = "SELECT COUNT(*) FROM room WHERE isAvailable = TRUE"; 

        try (Connection conn = DriverManager.getConnection(DatabaseURL, Username, Password);
             PreparedStatement ps = conn.prepareStatement(COUNT_QRY)) {

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count;
    }
    
    public boolean insertGuest(Guest guest) {
        String INSERT_QRY = "INSERT INTO guest (title, firstname, lastname, address, phone, emailaddress) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DriverManager.getConnection(DatabaseURL, Username, Password);
             PreparedStatement ps = conn.prepareStatement(INSERT_QRY)) {

            ps.setString(1, guest.getTitle());
            ps.setString(2, guest.getFirstName());
            ps.setString(3, guest.getLastName());
            ps.setString(4, guest.getAddress());
            ps.setString(5, guest.getPhone());
            ps.setString(6, guest.getEmail());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public boolean insertBooking(Reservation booking) {
        String INSERT_QRY = "INSERT INTO booking (bookingid, bookingdate, email, numofrooms, numofdays, price, roomType) VALUES (?, ?, ?, ?, ?, ?,?)";
        
        try (Connection conn = DriverManager.getConnection(DatabaseURL, Username, Password);
             PreparedStatement ps = conn.prepareStatement(INSERT_QRY)) {

            ps.setInt(1, booking.getBookID());
            ps.setString(2, booking.getBookDate());
            ps.setString(3,booking.getGuestEmail() );
            ps.setInt(4,booking.getNumOfRoomsBooked());
            ps.setInt(5,booking.getNumOfDaysBooked() );
            ps.setDouble(6,booking.getRatePerNight());
            ps.setString(7,booking.getRoomType());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public boolean checkRoomsAvailability(String roomType, int numOfRooms) {
        String QUERY = "SELECT COUNT(*) FROM room WHERE roomType = ? AND isAvailable = TRUE";
        
        try (Connection conn = DriverManager.getConnection(DatabaseURL, Username, Password);
             PreparedStatement ps = conn.prepareStatement(QUERY)) {

            ps.setString(1, roomType);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                int availableRooms = rs.getInt(1);
                return availableRooms >= numOfRooms;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public boolean deleteRooms(String roomType, int numOfRooms) {
        String DELETE_QUERY = "UPDATE room SET isAvailable = FALSE WHERE roomType = ? AND isAvailable = TRUE LIMIT ?";
        
        try (Connection conn = DriverManager.getConnection(DatabaseURL, Username, Password);
             PreparedStatement ps = conn.prepareStatement(DELETE_QUERY)) {

            ps.setString(1, roomType);
            ps.setInt(2, numOfRooms);
            
            int rowsAffected = ps.executeUpdate();
            return rowsAffected >= numOfRooms;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    public Reservation fetchBookingDetails(int bookingId) {
        String SELECT_QRY = "SELECT * FROM booking WHERE bookingid = ?";
        
        try (Connection conn = DriverManager.getConnection(DatabaseURL, Username, Password);
             PreparedStatement ps = conn.prepareStatement(SELECT_QRY)) {

            ps.setInt(1, bookingId);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                int bookId = rs.getInt("bookingid");
                String email = rs.getString("email");
                int numOfRooms = rs.getInt("numofrooms");
                int numOfDays = rs.getInt("numofdays");
                double price = rs.getDouble("price");
                String roomType = rs.getString("roomType");
                
                Reservation booking = new Reservation(bookId, email, numOfRooms, numOfDays, price, roomType);
                return booking;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public int countBookings() {
        int count = 0;
        String COUNT_QRY = "SELECT COUNT(*) FROM booking"; 

        try (Connection conn = DriverManager.getConnection(DatabaseURL, Username, Password);
             PreparedStatement ps = conn.prepareStatement(COUNT_QRY)) {

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count;
    }
    public List<Reservation> fetchBookings() {
        String SELECT_QRY = "SELECT * FROM booking";
        List<Reservation> bookings = new ArrayList<>();
        
        try (Connection conn = DriverManager.getConnection(DatabaseURL, Username, Password);
             PreparedStatement ps = conn.prepareStatement(SELECT_QRY)) {

            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                int bookId = rs.getInt("bookingid");
                String email = rs.getString("email");
                int numOfRooms = rs.getInt("numofrooms");
                int numOfDays = rs.getInt("numofdays");
                double price = rs.getDouble("price");
                String roomType = rs.getString("roomType");
                
                Reservation booking = new Reservation(bookId, email, numOfRooms, numOfDays, price, roomType);
                bookings.add(booking);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return bookings;
    }
}
