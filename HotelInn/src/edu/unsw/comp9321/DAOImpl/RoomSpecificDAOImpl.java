package edu.unsw.comp9321.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import edu.unsw.comp9321.DAO.RoomSpecificDAO;
import edu.unsw.comp9321.DTO.OccupancyDTO;
import edu.unsw.comp9321.DTO.RoomSpecificDTO;
import edu.unsw.comp9321.exception.EmptyResultException;
import edu.unsw.comp9321.exception.ServiceLocatorException;

public class RoomSpecificDAOImpl implements RoomSpecificDAO {
	static Logger logger = Logger.getLogger(PriceDAOImpl.class.getName());
	private Connection connection;
	DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
	
	
	public RoomSpecificDAOImpl() throws ServiceLocatorException, SQLException{
		connection = DBConnectionFactory.getConnection();
		logger.info("Got connection");
	}
	
	public Connection getConnection(){
		return this.connection;
	}
	
	@Override
	public List<RoomSpecificDTO> getRoomSpecificByCondition(String condition)
			throws EmptyResultException {
		// TODO Auto-generated method stub
		ArrayList<RoomSpecificDTO> cast = new ArrayList<RoomSpecificDTO>();
		try{
			//Statement stmnt = connection.createStatement();
			String query_cast = "SELECT ROOM_ID, ROOM_NO, ROOM_TYPE_SPECIFIC, HOTEL_ID FROM TBL_ROOMS_SPECIFIC WHERE ROOM_CONDITION = (?)";
			PreparedStatement count_stmnt = connection.prepareStatement(query_cast);
			count_stmnt.setString(1, condition);
			ResultSet res = count_stmnt.executeQuery();
			logger.info("The result set size is "+res.getFetchSize());
			while(res.next()){
				int ID = res.getInt("ROOM_ID");
				logger.info(" "+ID);
				int number = res.getInt("ROOM_NO");
				logger.info(" "+number);
				int hotel_ID = res.getInt("HOTEL_ID");
				logger.info(" "+hotel_ID);
				String type= res.getString("ROOM_TYPE_SPECIFIC");
				logger.info(type);
				
				
				cast.add(new RoomSpecificDTO(ID, number,type,condition,hotel_ID));
			}
			
			res.close();
			count_stmnt.close();
			
		}catch(Exception e){
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		return cast;
	}

	@Override
	public List<RoomSpecificDTO> getRoomSpecificByBooking(int hotel_ID,
			String type) throws EmptyResultException {
		// TODO Auto-generated method stub
		ArrayList<RoomSpecificDTO> cast = new ArrayList<RoomSpecificDTO>();
		try{
			//Statement stmnt = connection.createStatement();
			String query_cast = "SELECT ROOM_ID, ROOM_NO, ROOM_TYPE_SPECIFIC, HOTEL_ID FROM TBL_ROOMS_SPECIFIC WHERE ROOM_CONDITION = 'AVAILABLE' AND ROOM_TYPE_SPECIFIC = (?) AND HOTEL_ID = (?)";
			PreparedStatement count_stmnt = connection.prepareStatement(query_cast);
			count_stmnt.setString(1,type);
			count_stmnt.setInt(2,hotel_ID);
			ResultSet res = count_stmnt.executeQuery();
			logger.info("The result set size is "+res.getFetchSize());
			while(res.next()){
				int ID = res.getInt("ROOM_ID");
				logger.info(" "+ID);
				int number = res.getInt("ROOM_NO");
				logger.info(" "+number);
				hotel_ID = res.getInt("HOTEL_ID");

				
				
				cast.add(new RoomSpecificDTO(ID, number,type,"AVAILABLE",hotel_ID));
			}
			
			res.close();
			count_stmnt.close();
			
		}catch(Exception e){
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		return cast;
	}
	
	
	@Override
	public int UpdataRoomSpecificChecksout(int ID,int n)
			throws EmptyResultException {
		// TODO Auto-generated method stub
		ArrayList<RoomSpecificDTO> cast = new ArrayList<RoomSpecificDTO>();
		try{
			//Statement stmnt = connection.createStatement();
			if(n==0)
			{
			String query_cast = "UPDATE TBL_ROOMS_SPECIFIC SET ROOM_CONDITION = 'AVAILABLE' WHERE ROOM_ID = (?)";
			PreparedStatement count_stmnt = connection.prepareStatement(query_cast);
			count_stmnt.setInt(1,ID);
			int res = count_stmnt.executeUpdate();
			//logger.info("The result set size is "+res.getFetchSize());
			
			//res.close();
			count_stmnt.close();
			return res;
			}
			else
			{
				String query_cast = "UPDATE TBL_ROOMS_SPECIFIC SET ROOM_CONDITION = 'OCCUPIED' WHERE ROOM_ID = (?)";
				PreparedStatement count_stmnt = connection.prepareStatement(query_cast);
				count_stmnt.setInt(1,ID);
				int res = count_stmnt.executeUpdate();
				//logger.info("The result set size is "+res.getFetchSize());
				
				//res.close();
				count_stmnt.close();
				return res;
			}
			
		}catch(Exception e){
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public List<OccupancyDTO> getOccupancy() throws EmptyResultException {
		// TODO Auto-generated method stub
		ArrayList<OccupancyDTO> cast = new ArrayList<OccupancyDTO>();
		try{
			Statement stmnt = connection.createStatement();
			String query_cast = "SELECT DISTINCT COUNT1.HOTEL_ID AS HOTEL_ID,COUNT1.COUNTO AS COUNTA,COUNT2.COUNTA AS COUNTB FROM (SELECT HOTEL_ID,COUNT(HOTEL_ID) AS COUNTO  FROM TBL_ROOMS_SPECIFIC WHERE ROOM_CONDITION = 'OCCUPIED' GROUP BY HOTEL_ID) as COUNT1 RIGHT JOIN(SELECT HOTEL_ID,COUNT(HOTEL_ID) AS COUNTA  FROM TBL_ROOMS_SPECIFIC WHERE ROOM_CONDITION = 'AVAILABLE' GROUP BY HOTEL_ID) as COUNT2 ON COUNT1.HOTEL_ID = COUNT2.HOTEL_ID";
			ResultSet res = stmnt.executeQuery(query_cast);
			
			while(res.next()){
				int hotel_ID = res.getInt("HOTEL_ID");
				logger.info(" "+hotel_ID);
				int available_number = res.getInt("COUNTA");
				logger.info(" "+available_number);
				int occupied_number = res.getInt("COUNTB");
				logger.info(" "+occupied_number);

				
				cast.add(new OccupancyDTO(hotel_ID,available_number,occupied_number));
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
