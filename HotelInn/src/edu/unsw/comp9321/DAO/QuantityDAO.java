package edu.unsw.comp9321.DAO;

import java.util.Date;
import edu.unsw.comp9321.exception.EmptyResultException;

public interface QuantityDAO {
	
	public int getQuantity(int hotelID, String type, Date from, Date to) throws EmptyResultException;

}
