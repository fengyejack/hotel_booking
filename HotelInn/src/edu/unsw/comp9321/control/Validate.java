package edu.unsw.comp9321.control;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.unsw.comp9321.DAOImpl.BookingDAOImpl;
import edu.unsw.comp9321.DAOImpl.UserDAOImpl;
import edu.unsw.comp9321.DTO.BookingDTO;
import edu.unsw.comp9321.DTO.UserDTO;
import edu.unsw.comp9321.exception.ServiceLocatorException;

public class Validate implements Command{
	
	static Logger logger = Logger.getLogger(URL.class.getName());
	DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		int PIN = (Integer) request.getSession().getAttribute("PIN");
		UserDTO user = null;
		ArrayList<BookingDTO> bookingList = new ArrayList<BookingDTO>();
		
		try {
			UserDAOImpl UserDAOImpl = new UserDAOImpl();
			user = UserDAOImpl.getCustomerByPIN(PIN);
			request.getSession().setAttribute("user", user);
			UserDAOImpl.getConnection().close();
			
			BookingDAOImpl BookingDAOImpl= new BookingDAOImpl();
			bookingList = BookingDAOImpl.getBookingByPIN(PIN);
			request.getSession().setAttribute("bookingList", bookingList);
			BookingDAOImpl.getConnection().close();
			
			Date date = new Date();
			
			if(PIN == 0 || bookingList == null || user == null){
				System.out.println("PIN is not found!!!");
				return "/wrongURL.jsp";
			}
			
			for(int i = 0; i < bookingList.size(); i++){
				if((bookingList.get(i).getCheckin().getTime() - date.getTime()) / 3600000 < 48){
					System.out.println(bookingList.get(i).getCheckin().getTime() + " - " + date.getTime());
					System.out.println((bookingList.get(i).getCheckin().getTime() - date.getTime()) / 3600000);
					return "/tooLateToView.jsp";
				}
			}
		} catch (ServiceLocatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "/customerDisplay.jsp";
	}
}
