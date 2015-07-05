package edu.unsw.comp9321.control;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.unsw.comp9321.DAOImpl.PINDAOImpl;
import edu.unsw.comp9321.exception.ServiceLocatorException;

public class URL implements Command{
	
	static Logger logger = Logger.getLogger(URL.class.getName());
	DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String URL = request.getRequestURL().append('?').append(request.getQueryString()).toString();
//				request.getRequestURL().toString();
//		String URL = request.getParameter("ID");
		System.out.println("URL: " + URL);
		System.out.println();
		
		try {
			PINDAOImpl PINDAOImpl = new PINDAOImpl();
			int PIN = PINDAOImpl.getPINByURL(URL);
			request.getSession().setAttribute("PIN", PIN);
			PINDAOImpl.getConnection().close();
			System.out.println("PIN :  "+ PIN);
			
			if(PIN == 0){
				return "/wrongURL.jsp";
			}

		} catch (ServiceLocatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "/enterPIN.jsp";
	}
}
