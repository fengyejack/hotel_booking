package edu.unsw.comp9321.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import edu.unsw.comp9321.DAO.QuantityDAO;
import edu.unsw.comp9321.DTO.QuantityDTO;
import edu.unsw.comp9321.exception.EmptyResultException;
import edu.unsw.comp9321.exception.ServiceLocatorException;

public class QuantityDAOImpl implements QuantityDAO{
	
	static Logger logger = Logger.getLogger(QuantityDAOImpl.class.getName());
	private Connection connection;
	DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
	
	public QuantityDAOImpl() throws ServiceLocatorException, SQLException{
		connection = DBConnectionFactory.getConnection();
		logger.info("Got connection");
	}
	
	public Connection getConnection(){
		return this.connection;
	}

	@Override
	public int getQuantity(int hotelID, String type, Date from,
			Date to) throws EmptyResultException {
		// TODO Auto-generated method stub
		ArrayList<QuantityDTO> totalQuantity = new ArrayList<QuantityDTO>();
		ArrayList<QuantityDTO> bookedQuantity = new ArrayList<QuantityDTO>();
		try{
			PreparedStatement stmnt = connection.prepareStatement("SELECT ROOM_QUANTITY FROM TBL_ROOMS_GENERAL WHERE HOTEL_ID = (?) AND ROOM_TYPE = (?)");
			stmnt.setInt(1, hotelID);
			stmnt.setString(2, type);
			ResultSet res = stmnt.executeQuery();
//			Statement stmnt = connection.createStatement();
//			String query_totalQuantity = 
//					"SELECT TBL_ROOMS_GENERAL.ROOM_QUANTITY"
//					+ "FROM TBL_ROOMS_GENERAL"
//					+ "WHERE TBL_ROOMS_GENERAL.HOTEL_ID = (?)"
//					+ "AND TBL_ROOMS_GENERAL.ROOM_TYPE = (?);";
			
//			ResultSet res = stmnt.executeQuery(query_totalQuantity);
			logger.info("The totalQuantity set size is "+res.getFetchSize());
			while(res.next()){
				int quantity = res.getInt("ROOM_QUANTITY");
				logger.info("Hotel " + hotelID + "Roomtype "  + type + " (Total): " + quantity);
				totalQuantity.add(new QuantityDTO(type, quantity));
			}
			res.close();
			stmnt.close();
		}catch(Exception e){
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
			
		try{
			java.sql.Date sqlFrom = new java.sql.Date(from.getTime());
			java.sql.Date sqlTo = new java.sql.Date(to.getTime());
			PreparedStatement stmnt = connection.prepareStatement("SELECT ROOM_QUANTITY FROM TBL_BOOKINGS WHERE ((FROMDATE >= (?) AND FROMDATE <= (?)) OR (TODATE >= (?) AND TODATE <= (?))) AND (HOTEL_ID = (?)) AND (ROOM_TYPE = (?))");
			stmnt.setDate(1, sqlFrom);
			stmnt.setDate(2, sqlTo);
			stmnt.setDate(3, sqlFrom);
			stmnt.setDate(4, sqlTo);
			stmnt.setInt(5, hotelID);
			stmnt.setString(6, type);
			ResultSet res = stmnt.executeQuery();
//			Statement stmnt = connection.createStatement();
//			String query_bookedQuantity =		
//				"SELECT TBL_BOOKINGS.ROOM_QUANTITY"
//				+ "FROM TBL_BOOKINGS"
//				+ "WHERE ((TBL_BOOKINGS.FROMDATE <= '2014-09-10' AND TBL_BOOKINGS.TODATE >= '2014-09-10')"
//				+ "OR (TBL_BOOKINGS.FROMDATE <= '2014-09-13' AND TBL_BOOKINGS.TODATE >= '2014-09-13'))"
//				+ "AND (TBL_BOOKINGS.HOTEL_ID = 1)"
//				+ "AND (TBL_BOOKINGS.ROOM_TYPE = 'SINGLE');";
//			ResultSet res = stmnt.executeQuery(query_bookedQuantity);
			logger.info("The bookedQuantity set size is "+res.getFetchSize());
			while(res.next()){
				int quantity = res.getInt("ROOM_QUANTITY");
				logger.info("Hotel " + hotelID + "Roomtype "  + type + " (Booked): " + quantity);
				bookedQuantity.add(new QuantityDTO(type, quantity));
			}
			res.close();
			stmnt.close();
//			connection.close();
		}catch(Exception e){
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		
		int totalRoomQuantity = 0;
		for(int i = 0; i < totalQuantity.size(); i++){
			totalRoomQuantity += totalQuantity.get(i).getQuantity();
		}
		logger.info(" " + totalRoomQuantity);
		
		int totalBookedQuantity = 0;
		for(int i = 0; i < bookedQuantity.size(); i++){
			totalBookedQuantity += bookedQuantity.get(i).getQuantity();
		}
		logger.info(" " + totalBookedQuantity);
		
		return (totalRoomQuantity - totalBookedQuantity);
	}
}
