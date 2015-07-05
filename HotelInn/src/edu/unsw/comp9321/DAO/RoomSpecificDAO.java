package edu.unsw.comp9321.DAO;

import java.util.List;

import edu.unsw.comp9321.DTO.OccupancyDTO;
import edu.unsw.comp9321.DTO.RoomSpecificDTO;
import edu.unsw.comp9321.exception.EmptyResultException;

public interface RoomSpecificDAO {
	
	public List<RoomSpecificDTO> getRoomSpecificByCondition(String condition) throws EmptyResultException;
	
	public List<RoomSpecificDTO> getRoomSpecificByBooking(int hotelID,
			String type) throws EmptyResultException;
	public int UpdataRoomSpecificChecksout(int ID,int n)
			throws EmptyResultException;
	public List<OccupancyDTO> getOccupancy() throws EmptyResultException;

}
