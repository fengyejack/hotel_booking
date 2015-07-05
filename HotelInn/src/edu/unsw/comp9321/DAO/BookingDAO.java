package edu.unsw.comp9321.DAO;

import java.util.ArrayList;
import java.util.List;

import edu.unsw.comp9321.DTO.BookingDTO;

public interface BookingDAO {
	
	public int getNextBookingID();
	
	public List<BookingDTO> getAllBooking();
	
	public void addBooking(BookingDTO booking);
	
	public ArrayList<BookingDTO> getBookingByPIN(int PIN);
	
	public void deleteBookingByPIN(int PIN);
	
}
