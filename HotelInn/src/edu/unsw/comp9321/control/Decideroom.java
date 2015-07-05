package edu.unsw.comp9321.control;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.unsw.comp9321.DAOImpl.RoomSpecificDAOImpl;
import edu.unsw.comp9321.DTO.RoomSpecificDTO;

public class Decideroom implements Command{
	
	static Logger logger = Logger.getLogger(AssignRooms.class.getName());
	DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		try {
		
		RoomSpecificDAOImpl castspecific = new RoomSpecificDAOImpl();
		//int i = Integer.parseInt(request.getParameter("resultList"));
		//System.out.println("username" + i);
		//List<RoomSpecificDTO> RoomList =castspecific.getRoomSpecificByCondition("OCCUPIED");
		
//		request.getSession().setAttribute("occupied", castspecific.getRoomSpecificByCondition("OCCUPIED"));
		ArrayList<RoomSpecificDTO> OccupiedList = (ArrayList<RoomSpecificDTO>) request.getSession().getAttribute("occupied");
		ArrayList<RoomSpecificDTO> ResultList = (ArrayList<RoomSpecificDTO>) request.getSession().getAttribute("rooms");
		
		int ID = Integer.parseInt(request.getParameter("resultList"));
		System.out.println("decide" + ID);
		
		castspecific.UpdataRoomSpecificChecksout(ResultList.get(ID).getRoomID(),1);
		OccupiedList.add(new RoomSpecificDTO(ResultList.get(ID).getRoomID(), ResultList.get(ID).getNumber(), ResultList.get(ID).getType(), "OCCUPIED", ResultList.get(ID).getHotelID()));
		
		request.getSession().setAttribute("occupied", OccupiedList);
		
		castspecific.getConnection().close();
		
		return "/roomlist.jsp";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "/error.jsp";
		}
	}
}