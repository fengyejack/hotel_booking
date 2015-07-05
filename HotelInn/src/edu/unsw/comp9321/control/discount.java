package edu.unsw.comp9321.control;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.unsw.comp9321.DAOImpl.RoomGeneralDAOImpl;

public class discount implements Command{
	
	static Logger logger = Logger.getLogger(discount.class.getName());
	DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		try {
		
		RoomGeneralDAOImpl castspecific = new RoomGeneralDAOImpl();
		double discount = Double.parseDouble(request.getParameter("fare"));
		Date from = fmt.parse(request.getParameter("fromdata"));
		Date to = fmt.parse(request.getParameter("todata"));
		int ID = Integer.parseInt(request.getParameter("city"));
		String type = request.getParameter("roomtype");
		System.out.println("discount" +ID+":"+from+to+discount);
		
		castspecific.Updatadiscount(ID, type, from, to, discount);
		
		castspecific.getConnection().close();
		
		return "/occupancy.jsp";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "/invalidInput.jsp";
		}
	}
}