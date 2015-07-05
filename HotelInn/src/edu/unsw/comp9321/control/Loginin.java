package edu.unsw.comp9321.control;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.unsw.comp9321.DAOImpl.UserDAOImpl;
import edu.unsw.comp9321.DAOImpl.RoomSpecificDAOImpl;
import edu.unsw.comp9321.DAOImpl.BookingDAOImpl;

public class Loginin implements Command{
	
	static Logger logger = Logger.getLogger(Loginin.class.getName());
	DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			UserDAOImpl castuser = new UserDAOImpl();
			BookingDAOImpl castbooking = new BookingDAOImpl();
			RoomSpecificDAOImpl castspecific = new RoomSpecificDAOImpl();
			if(castuser.getByLogin(username,password,"MANAGER") > 0){
				request.getSession().setAttribute("occupied", castspecific.getRoomSpecificByCondition("OCCUPIED"));
				request.getSession().setAttribute("bookings",castbooking.getAllBooking());
				
				castuser.getConnection().close();
				castbooking.getConnection().close();
				castspecific.getConnection().close();
				
				return "/roomlist.jsp";
			}
			else{
				castuser.getConnection().close();
				castbooking.getConnection().close();
				castspecific.getConnection().close();
				
				return "/loginfail.jsp";
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "/index.jsp";
		}
	}
}
