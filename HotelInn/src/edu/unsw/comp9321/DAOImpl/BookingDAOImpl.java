package edu.unsw.comp9321.DAOImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import edu.unsw.comp9321.DAO.BookingDAO;
import edu.unsw.comp9321.DTO.BookingDTO;
import edu.unsw.comp9321.exception.*;

public class BookingDAOImpl implements BookingDAO{
	
	static Logger logger = Logger.getLogger(BookingDAOImpl.class.getName());
	private Connection connection;
	DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
	
	public BookingDAOImpl() throws ServiceLocatorException, SQLException{
		connection = DBConnectionFactory.getConnection();
		logger.info("Got connection");
	}
	
	public Connection getConnection(){
		return this.connection;
	}

	@Override
	public int getNextBookingID() {
		// TODO Auto-generated method stub
		int bookingID = 0;
		try{
			PreparedStatement stmnt = connection.prepareStatement("SELECT MAX(BOOKING_ID) AS BOOKING_ID FROM TBL_BOOKINGS");
			ResultSet res = stmnt.executeQuery();
			logger.info("The result set size is "+res.getFetchSize());
			while(res.next()){
				bookingID = res.getInt("BOOKING_ID");
				logger.info("Booking ID: "+ bookingID);
			}
			res.close();
			stmnt.close();
			
		}catch(Exception e){
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		return (bookingID + 1);
	}
	
	@Override
	public void addBooking(BookingDTO booking) {
		// TODO Auto-generated method stub
		
		try{
			PreparedStatement stmnt = connection.prepareStatement("INSERT INTO TBL_BOOKINGS (CUSTOMER_ID, HOTEL_ID, PIN, ROOM_TYPE, ROOM_QUANTITY, EXTRA_BED, TOTALPRICE, FROMDATE, TODATE, DETAILS) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			stmnt.setInt(1, booking.getCustomerID());
			stmnt.setInt(2, booking.getHotelID());
			stmnt.setInt(3, booking.getPIN());
			stmnt.setString(4, booking.getType());
			stmnt.setInt(5, booking.getQuantity());
			stmnt.setInt(6, booking.getExtraBed());
			stmnt.setInt(7, booking.getPrice());
			stmnt.setDate(8, new java.sql.Date(booking.getCheckin().getTime()));
			stmnt.setDate(9, new java.sql.Date(booking.getCheckout().getTime()));
			stmnt.setString(10, booking.getDetails());
			
			int n = stmnt.executeUpdate();
		     if (n != 1)//remember to catch the exceptions
		 	   throw new DataAccessException("Did not insert one row into database");
		   } catch (SQLException e) {
		       throw new DataAccessException("Unable to execute query; " + e.getMessage(), e);
		   } finally {
		      if (connection != null) {
		         try {
		        	 connection.close();//and close the connections etc
		   		 } catch (SQLException e1) {  //if not close properly
		           e1.printStackTrace();
		         }
		      }
		   }
	}

	@Override
	public ArrayList<BookingDTO> getBookingByPIN(int PIN) {
		// TODO Auto-generated method stub
		
		int bookingID, customerID, hotelID, pin, roomQuantity, extraBed, totalPrice;
		String roomType, details;
		Date checkIn, checkOut;
		BookingDTO booking = null;
		ArrayList<BookingDTO> bookingList = new ArrayList<BookingDTO>();
		
		try{
			PreparedStatement stmnt = connection.prepareStatement("SELECT * FROM TBL_BOOKINGS WHERE PIN = ?");
			stmnt.setInt(1, PIN);
			ResultSet res = stmnt.executeQuery();
			logger.info("The result set size is "+res.getFetchSize());
			while(res.next()){
				bookingID = res.getInt("BOOKING_ID");
				logger.info("Booking_ID:::::::::: " + bookingID);
				customerID = res.getInt("CUSTOMER_ID");
				hotelID = res.getInt("HOTEL_ID");
				pin = res.getInt("PIN");
				roomQuantity = res.getInt("ROOM_QUANTITY");
				extraBed = res.getInt("EXTRA_BED");
				totalPrice = res.getInt("TOTALPRICE");
				roomType = res.getString("ROOM_TYPE");
				details = res.getString("DETAILS");
				checkIn = res.getDate("FROMDATE");
				checkOut = res.getDate("TODATE");
				
				booking = new BookingDTO(bookingID, customerID, hotelID, roomType, roomQuantity, extraBed, totalPrice, checkIn, checkOut, details, pin);
				bookingList.add(booking);
				logger.info("Booking PIN:  "+ PIN);
			}
			res.close();
			stmnt.close();
			
		}catch(Exception e){
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		return bookingList;
	}
	
	@Override
	public void deleteBookingByPIN(int PIN) {
		// TODO Auto-generated method stub
		
		
		try {
			PreparedStatement stmnt = connection.prepareStatement("DELETE FROM TBL_BOOKINGS WHERE PIN = ?");
			stmnt.setInt(1, PIN);
			int n = stmnt.executeUpdate();
//		     if (n != 1)//remember to catch the exceptions
//		 	   throw new DataAccessException("Did not delete one row from database");
		   } catch (SQLException e) {
		       throw new DataAccessException("Unable to execute query; " + e.getMessage(), e);
		   } finally {
		      if (connection != null) {
		         try {
		        	 connection.close();//and close the connections etc
		   		 } catch (SQLException e1) {  //if not close properly
		           e1.printStackTrace();
		         }
		      }
		   }
		}
	

	@Override
	public List<BookingDTO> getAllBooking() {
		// TODO Auto-generated method stub
		ArrayList<BookingDTO> cast = new ArrayList<BookingDTO>();
		try{
			Statement stmnt = connection.createStatement();
			String query_cast = "SELECT BOOKING_ID, CUSTOMER_ID, HOTEL_ID, ROOM_TYPE,ROOM_QUANTITY,EXTRA_BED,TOTALPRICE,FROMDATE,TODATE,DETAILS FROM TBL_BOOKINGS";
			ResultSet res = stmnt.executeQuery(query_cast);
			logger.info("The result set size is "+res.getFetchSize());
			while(res.next()){
				int ID = res.getInt("BOOKING_ID");
				logger.info(" "+ID);
				int customer_ID = res.getInt("CUSTOMER_ID");
				logger.info(" "+customer_ID);
				int hotel_ID = res.getInt("HOTEL_ID");
				logger.info(" "+hotel_ID);
				String type= res.getString("ROOM_TYPE");
				logger.info(type);
				int quantity = res.getInt("ROOM_QUANTITY");
				logger.info(" "+quantity);
				int extra_bed = res.getInt("EXTRA_BED");
				logger.info(" "+extra_bed);
				int price = res.getInt("TOTALPRICE");
				logger.info(" "+price);
				Timestamp checkin = res.getTimestamp("FROMDATE");
				logger.info(" "+checkin);
				Timestamp checkout = res.getTimestamp("TODATE");
				logger.info(" "+checkout);
				String details= res.getString("DETAILS");
				logger.info(details);
				
				
				cast.add(new BookingDTO(ID, customer_ID, hotel_ID,type,quantity,extra_bed,price,checkin,checkout,details, 0));
			}
			
			res.close();
			stmnt.close();
			
		}catch(Exception e){
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		return cast;
	}

}
