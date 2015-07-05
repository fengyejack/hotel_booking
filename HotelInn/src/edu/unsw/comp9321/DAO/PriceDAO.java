package edu.unsw.comp9321.DAO;

import java.util.Date;
import edu.unsw.comp9321.exception.EmptyResultException;

public interface PriceDAO {
	
	public int getPrice(int hotelID, String type, Date from, Date to) throws EmptyResultException;
	
}
