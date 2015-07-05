package edu.unsw.comp9321.control;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.unsw.comp9321.DAOImpl.RoomSpecificDAOImpl;
import edu.unsw.comp9321.DAOImpl.BookingDAOImpl;
import edu.unsw.comp9321.DTO.BookingDTO;

public class AssignRooms implements Command{
	
	static Logger logger = Logger.getLogger(AssignRooms.class.getName());
	DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		try {
		BookingDAOImpl castbooking = new BookingDAOImpl();
		RoomSpecificDAOImpl castspecific = new RoomSpecificDAOImpl();
		int i = Integer.parseInt(request.getParameter("bookingList"));
		System.out.println("username" + i);
		List<BookingDTO> BookingList =castbooking.getAllBooking();
		int hotelID = BookingList.get(i).getHotelID();
		String type = BookingList.get(i).getType();
		int Quantity = BookingList.get(i).getQuantity();
		int ExtraBed = BookingList.get(i).getExtraBed();
		int TotalPrice = BookingList.get(i).getPrice();
		Date Checkin = BookingList.get(i).getCheckin();
		Date Checkout = BookingList.get(i).getCheckout();
		int Price = TotalPrice/Quantity;
		request.getSession().setAttribute("rooms", castspecific.getRoomSpecificByBooking(hotelID,type));
		
		castbooking.getConnection().close();
		castspecific.getConnection().close();
		
		return "/assignrooms.jsp";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "/index.jsp";
		}
	}
}
