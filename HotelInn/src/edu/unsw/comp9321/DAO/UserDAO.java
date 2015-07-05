package edu.unsw.comp9321.DAO;

import edu.unsw.comp9321.DTO.UserDTO;

public interface UserDAO {
	
	public int getNextCustomerID();
	
	public void addCustomer(UserDTO user);
	
	public UserDTO getCustomerByPIN(int PIN);
	
	public int getByLogin(String username, String password,String role);

}