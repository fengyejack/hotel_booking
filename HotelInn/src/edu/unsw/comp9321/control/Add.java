package edu.unsw.comp9321.control;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.unsw.comp9321.DAOImpl.BookingDAOImpl;
import edu.unsw.comp9321.DTO.BookingDTO;
import edu.unsw.comp9321.DTO.SearchResultDTO;
import edu.unsw.comp9321.DTO.UserDTO;
import edu.unsw.comp9321.exception.ServiceLocatorException;

public class Add implements Command{
	
	static Logger logger = Logger.getLogger(Add.class.getName());
	DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		int PIN = (Integer) request.getSession().getAttribute("PIN");
		SearchResultDTO selection = (SearchResultDTO)request.getSession().getAttribute("selection");
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");
		ArrayList<BookingDTO> bookingList = (ArrayList<BookingDTO>)request.getSession().getAttribute("bookingList");
		BookingDTO booking = new BookingDTO(0, user.getUserID(), selection.getHotelID(), selection.getRoomType(), selection.getQuantity(),
				selection.getExtraBed(), selection.getPrice(), selection.getFromDate(), selection.getToDate(), "new booking", PIN);
		
		try {
			BookingDAOImpl bookingDAOImpl = new BookingDAOImpl();
			bookingDAOImpl.addBooking(booking);
			bookingDAOImpl.getConnection().close();
		} catch (ServiceLocatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "/add1.jsp";
	}
}
