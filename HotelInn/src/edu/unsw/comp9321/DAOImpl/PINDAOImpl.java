package edu.unsw.comp9321.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

import edu.unsw.comp9321.DAO.PINDAO;
import edu.unsw.comp9321.DTO.PINDTO;
import edu.unsw.comp9321.exception.DataAccessException;
import edu.unsw.comp9321.exception.ServiceLocatorException;

public class PINDAOImpl implements PINDAO{
	
	static Logger logger = Logger.getLogger(PINDAOImpl.class.getName());
	private Connection connection;
	DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
	
	public PINDAOImpl() throws ServiceLocatorException, SQLException{
		connection = DBConnectionFactory.getConnection();
		logger.info("Got connection");
	}
	
	public Connection getConnection(){
		return this.connection;
	}

	@Override
	public int getPINByURL(String URL) {
		// TODO Auto-generated method stub
		int PIN = 0;
		try{
			PreparedStatement stmnt = connection.prepareStatement("SELECT PIN FROM TBL_PIN WHERE CAST(URL AS VARCHAR(128)) = ?");
			stmnt.setString(1, URL);
			
			ResultSet res = stmnt.executeQuery();
			logger.info("The result set size is "+res.getFetchSize());
			while(res.next()){
				PIN = res.getInt("PIN");
				logger.info("PIN:  "+ PIN);
			}
			res.close();
			stmnt.close();
			
		}catch(Exception e){
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		return PIN;
	}

	@Override
	public void addPIN(PINDTO pin) {
		// TODO Auto-generated method stub
		try{
			PreparedStatement stmnt = connection.prepareStatement("INSERT INTO TBL_PIN(PIN, URL) VALUES(?, ?)");
			stmnt.setInt(1, pin.getPIN());
			stmnt.setString(2, pin.getURL());
			
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
	public void deleteByPIN(int PIN) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement stmnt = connection.prepareStatement("DELETE FROM TBL_PIN WHERE PIN = ?");
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
	}
