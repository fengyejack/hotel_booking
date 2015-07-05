package edu.unsw.comp9321.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import edu.unsw.comp9321.DAO.RoomGeneralDAO;
import edu.unsw.comp9321.DTO.RoomGeneralDTO;
import edu.unsw.comp9321.exception.EmptyResultException;
import edu.unsw.comp9321.exception.ServiceLocatorException;

public class RoomGeneralDAOImpl implements RoomGeneralDAO {
	
	static Logger logger = Logger.getLogger(RoomGeneralDAOImpl.class.getName());
	private Connection connection;
	DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
	
	public RoomGeneralDAOImpl() throws ServiceLocatorException, SQLException{
		connection = DBConnectionFactory.getConnection();
		logger.info("Got connection");
	}
	
	public Connection getConnection(){
		return this.connection;
	}

	@Override
	public List<RoomGeneralDTO> getRoomSearchResult(String city,
			Timestamp from, Timestamp to, int quantity, int price)
			throws EmptyResultException {
		// TODO Auto-generated method stub
		ArrayList<RoomGeneralDTO> searchResult = new ArrayList<RoomGeneralDTO>();
		try{
			int hotelID = 0;
			int availableQuantity = 0;
			Date bookFrom;
			Date bookTo;
			String roomType = null;
			String bookedRoomType;
			int bookedQuantity;
			Statement stmnt = connection.createStatement();
			
			String query_searchHotelID = "SELECT TBL_HOTELS.HOTEL_ID FROM TBL_HOTELS WHERE TBL_HOTELS.CITY = 'Sydney';";
			ResultSet res = stmnt.executeQuery(query_searchHotelID);
			logger.info("The result set size is "+res.getFetchSize());
			while(res.next()){
				hotelID = res.getInt("HOTEL_ID");
				logger.info(" "+ hotelID);
			}
			
			String query_searchAvailableQuantity = 
					"SELECT TBL_ROOMS_SPECIFIC.ROOM_TYPE_SPECIFIC AS ROOM_TYPE, COUNT(TBL_ROOMS_SPECIFIC.ROOM_TYPE_SPECIFIC) AS QUANTITY"
					+ "FROM TBL_ROOMS_SPECIFIC"
					+ "WHERE TBL_ROOMS_SPECIFIC.HOTEL_ID = 1"
					+ "AND TBL_ROOMS_SPECIFIC.ROOM_CONDITION = 'AVAILABLE'"
					+ "GROUP BY TBL_ROOMS_SPECIFIC.ROOM_TYPE_SPECIFIC;";
			ResultSet res1 = stmnt.executeQuery(query_searchAvailableQuantity);
			while(res1.next()){
				roomType = res.getString("ROOM_TYPE");
				logger.info(" "+ roomType);
				availableQuantity = res.getInt("QUANTITY");
				logger.info(" " + availableQuantity);
			}
			
			String query_searchBookedQuantity = 
					"SELECT TBL_BOOKINGS.ROOM_TYPE, TBL_BOOKINGS.ROOM_QUANTITY, TBL_BOOKINGS.FROMDATE, TBL_BOOKINGS.TODATE"
					+ "FROM TBL_BOOKINGS"
					+ "WHERE TBL_BOOKINGS.HOTEL_ID = 1;";
			ResultSet res3 = stmnt.executeQuery(query_searchBookedQuantity);
			while(res3.next()){
				bookedRoomType = res.getString("ROOM_TYPE");
				logger.info(" "+ bookedRoomType);
				bookedQuantity = res.getInt("ROOM_QUANTITY");
				logger.info(" " + bookedQuantity);
				bookFrom = fmt.parse(res3.getString("FROMDATE"));
				logger.info(" " + bookFrom);
				bookTo = fmt.parse(res3.getString("TODATE"));
				logger.info(" " + bookTo);
			}
			res.close();
			stmnt.close();
			
		}catch(Exception e){
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		return searchResult;
	}

	@Override
	public int Updatadiscount(int ID, String type, Date from, Date to, double discount)
			throws EmptyResultException {
		// TODO Auto-generated method stub
		try{
			//Statement stmnt = connection.createStatement();
			java.sql.Date sqlFrom = new java.sql.Date(from.getTime());
			java.sql.Date sqlTo = new java.sql.Date(to.getTime());
			String query_cast = "UPDATE TBL_ROOMS_GENERAL SET ROOM_DISCOUNT = (?) , ROOM_DISCOUNTFROM = (?) , ROOM_DISCOUNTTO = (?) WHERE HOTEL_ID = (?) AND ROOM_TYPE = (?)";
			//AND ROOM_DISCOUNTFROM = (?) AND ROOM_DISCOUNTTO = (?) 
			PreparedStatement count_stmnt = connection.prepareStatement(query_cast);
			count_stmnt.setDouble(1,discount);
			count_stmnt.setDate(2,sqlFrom);
			count_stmnt.setDate(3,sqlTo);
			count_stmnt.setInt(4,ID);
			count_stmnt.setString(5,type);
			System.out.println("discount in " + ID + " and TYPE " + type + ": " + sqlFrom + sqlTo + discount);
			int res = count_stmnt.executeUpdate();
			//logger.info("The result set size is "+res.getFetchSize());
			
			//res.close();
			count_stmnt.close();
			return res;
			
		}catch(Exception e){
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		return 0;
	}

}
