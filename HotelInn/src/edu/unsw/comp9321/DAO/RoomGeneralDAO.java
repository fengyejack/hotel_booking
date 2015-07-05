package edu.unsw.comp9321.DAO;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import edu.unsw.comp9321.DTO.RoomGeneralDTO;
import edu.unsw.comp9321.exception.EmptyResultException;

public interface RoomGeneralDAO {
	
	public List<RoomGeneralDTO> getRoomSearchResult(String city, Timestamp from, Timestamp to, int quantity, int price) throws EmptyResultException;
	
	public int Updatadiscount(int ID, String type, Date from, Date to, double discount) throws EmptyResultException;

}