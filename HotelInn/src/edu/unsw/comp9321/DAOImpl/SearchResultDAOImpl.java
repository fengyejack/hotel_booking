package edu.unsw.comp9321.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

import javax.servlet.ServletException;

import edu.unsw.comp9321.DAO.SearchResultDAO;
import edu.unsw.comp9321.exception.EmptyResultException;
import edu.unsw.comp9321.exception.ServiceLocatorException;

public class SearchResultDAOImpl implements SearchResultDAO{
	
	static Logger logger = Logger.getLogger(SearchResultDAOImpl.class.getName());
	private Connection connection;
	DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
	
	public SearchResultDAOImpl() throws ServiceLocatorException, SQLException{
		connection = DBConnectionFactory.getConnection();
		logger.info("Got connection");
	}
	
	public Connection getConnection(){
		return this.connection;
	}

	@Override
	public int getHotelID(String city) throws EmptyResultException, ServletException{
		// TODO Auto-generated method stub
		int hotelID = 0;
		
		try{
			PreparedStatement stmnt = connection.prepareStatement("SELECT TBL_HOTELS.HOTEL_ID FROM TBL_HOTELS WHERE TBL_HOTELS.CITY = (?)");
			stmnt.setString(1, city);
//			String query_searchHotelID = "SELECT TBL_HOTELS.HOTEL_ID FROM TBL_HOTELS WHERE TBL_HOTELS.CITY = (?);";
//			stmnt = connection.prepareStatement(sqlStr);
			ResultSet res = stmnt.executeQuery();//.executeQuery(query_searchHotelID);
			logger.info("The result set size is "+res.getFetchSize());
			while(res.next()){
				hotelID = res.getInt("HOTEL_ID");
				logger.info(" "+ hotelID);
			}
			res.close();
			stmnt.close();
			
		}catch(Exception e){
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		
		return hotelID;
	}
}
