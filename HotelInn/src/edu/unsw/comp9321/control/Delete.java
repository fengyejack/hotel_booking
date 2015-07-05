package edu.unsw.comp9321.control;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.unsw.comp9321.DAOImpl.BookingDAOImpl;
import edu.unsw.comp9321.DAOImpl.PINDAOImpl;
import edu.unsw.comp9321.exception.ServiceLocatorException;

public class Delete implements Command{
	
	static Logger logger = Logger.getLogger(Delete.class.getName());
	DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
//		BookingDTO booking = (BookingDTO) request.getSession().getAttribute("booking");
		int PIN = (Integer) request.getSession().getAttribute("PIN");
		
		try {
			BookingDAOImpl bookingDAOImpl = new BookingDAOImpl();
			bookingDAOImpl.deleteBookingByPIN(PIN);
			bookingDAOImpl.getConnection().close();
			
			PINDAOImpl PINDAOImpl = new PINDAOImpl();
			PINDAOImpl.deleteByPIN(PIN);
			PINDAOImpl.getConnection().close();
			
		} catch (ServiceLocatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "/delete.jsp";
	}
}
