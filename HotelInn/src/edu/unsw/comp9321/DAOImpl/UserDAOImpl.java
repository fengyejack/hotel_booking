package edu.unsw.comp9321.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

import edu.unsw.comp9321.DAO.UserDAO;
import edu.unsw.comp9321.DTO.UserDTO;
import edu.unsw.comp9321.exception.DataAccessException;
import edu.unsw.comp9321.exception.ServiceLocatorException;

public class UserDAOImpl implements UserDAO {
	
	static Logger logger = Logger.getLogger(UserDAOImpl.class.getName());
	private Connection connection;
	DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
	
	public UserDAOImpl() throws ServiceLocatorException, SQLException{
		connection = DBConnectionFactory.getConnection();
		logger.info("Got connection");
	}
	
	public Connection getConnection(){
		return this.connection;
	}

	@Override
	public int getNextCustomerID() {
		// TODO Auto-generated method stub
		int customerID = 0;
		try{
			PreparedStatement stmnt = connection.prepareStatement("SELECT MAX(USER_ID) AS USER_ID FROM TBL_USERS");
			ResultSet res = stmnt.executeQuery();
			logger.info("The result set size is "+res.getFetchSize());
			while(res.next()){
				customerID = res.getInt("USER_ID");
				logger.info("Customer ID: "+ customerID);
			}
			res.close();
			stmnt.close();
			
		}catch(Exception e){
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		return (customerID + 1);
	}

	@Override
	public void addCustomer(UserDTO user) {
		// TODO Auto-generated method stub
		try{
			PreparedStatement stmnt = connection.prepareStatement("INSERT INTO TBL_USERS (SURNAME, OTHERNAME, ROLE, USERNAME, PASSWORD, EMAIL, DETAILS, PIN) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
			stmnt.setString(1, user.getSurname());
			stmnt.setString(2, user.getOthername());
			stmnt.setString(3, user.getRole());
			stmnt.setString(4, user.getUsername());
			stmnt.setString(5, user.getPassword());
			stmnt.setString(6, user.getEmail());
			stmnt.setString(7, user.getDetails());
			stmnt.setInt(8, user.getPIN());
			
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
	public UserDTO getCustomerByPIN(int PIN) {
		// TODO Auto-generated method stub
		int userID;
		String surname;
		String othername;
		String role;
		String username;
		String password;
		String email;
		String details;
		int pin;
		UserDTO user = null;
		
		try{
			PreparedStatement stmnt = connection.prepareStatement("SELECT * FROM TBL_USERS WHERE PIN = ?");
			stmnt.setInt(1, PIN);
			ResultSet res = stmnt.executeQuery();
			logger.info("The result set size is "+res.getFetchSize());
			while(res.next()){
				userID = res.getInt("USER_ID");
				surname = res.getString("SURNAME");
				othername = res.getString("OTHERNAME");
				role = res.getString("ROLE");
				username = res.getString("USERNAME");
				password = res.getString("PASSWORD");
				email = res.getString("EMAIL");
				details = res.getString("DETAILS");
				pin = res.getInt("PIN");
				user = new UserDTO(userID, surname, othername, role, username, password, email, details, pin);
				logger.info("User PIN:  "+ PIN);
			}
			res.close();
			stmnt.close();
			
		}catch(Exception e){
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public int getByLogin(String username, String password, String role) {
		// TODO Auto-generated method stub
		int numRows = 0;
		try{
			String count_query = "SELECT COUNT(*) FROM TBL_USERS WHERE USERNAME = (?) AND PASSWORD = (?) AND ROLE = (?)";
			
			PreparedStatement count_stmnt = connection.prepareStatement(count_query);
			count_stmnt.setString(1, username);
			count_stmnt.setString(2, password);
			count_stmnt.setString(3, role);
			ResultSet count_res = count_stmnt.executeQuery();
			count_res.next();
			numRows = count_res.getInt(1);
			System.out.println("numRow" + count_query);
			count_res.close();
			count_stmnt.close();
			
		}catch(Exception e){
			logger.severe("Failed to get by login in "+e.getStackTrace());
		}
		
		return numRows;
	}
}
