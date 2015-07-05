package edu.unsw.comp9321.DAO;

import javax.servlet.ServletException;

import edu.unsw.comp9321.exception.EmptyResultException;

public interface SearchResultDAO {
	
	public int getHotelID(String city) throws EmptyResultException,ServletException;

}
