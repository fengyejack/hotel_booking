package edu.unsw.comp9321.control;

import java.rmi.server.UID;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.unsw.comp9321.DAOImpl.BookingDAOImpl;
import edu.unsw.comp9321.DAOImpl.PINDAOImpl;
import edu.unsw.comp9321.DAOImpl.UserDAOImpl;
import edu.unsw.comp9321.DTO.BookingDTO;
import edu.unsw.comp9321.DTO.PINDTO;
import edu.unsw.comp9321.DTO.SearchResultDTO;
import edu.unsw.comp9321.DTO.UserDTO;
import edu.unsw.comp9321.exception.ServiceLocatorException;

public class Book implements Command{
	
	static Logger logger = Logger.getLogger(Confirm.class.getName());
	DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String surname = request.getParameter("surname");
		String othername = request.getParameter("othername");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String paymentDetails = request.getParameter("payment");
		String bookDetails = request.getParameter("booking");
		SearchResultDTO selection = (SearchResultDTO) request.getSession().getAttribute("selection");
		int PIN = 0;
		String URL = null;
//		System.out.println("=============" + surname.length() + othername.length() + username + password + email + paymentDetails + bookDetails + "=============");
		
		if(!isValid(surname) || !isValid(othername) || !isValid(username) || !isValid(surname) 
				|| !isValid(password) || !isValid(email) || !isValid(paymentDetails) || !isValid(bookDetails)){
			return "/invalidInput.jsp";
		}
		
		try {
			UserDAOImpl userDAOImpl = new UserDAOImpl();
			BookingDAOImpl bookingDAOImpl = new BookingDAOImpl();
			
			int customerID = userDAOImpl.getNextCustomerID();
			int bookingID = bookingDAOImpl.getNextBookingID();
			PIN = (int) (0.5 * (customerID + bookingID) *(customerID + bookingID + 1) + bookingID);
			UID ID = new UID();
			URL = "http://localhost:8080/HotelInn/ServletControl?control=URL&ID=" + ID;
			
			System.out.println(PIN + "++++++++++++++++++++++++++++++++++++++++++++++++++++++" + URL);
			
			PINDAOImpl PINDAOImpl = new PINDAOImpl();
			PINDTO pin= new PINDTO(PIN, URL);
			PINDAOImpl.addPIN(pin);
			PINDAOImpl.getConnection().close();
			
			UserDTO user = new UserDTO(customerID, surname, othername, "CUSTOMER", username, password, email, paymentDetails, PIN);
			userDAOImpl.addCustomer(user);
			userDAOImpl.getConnection().close();
			
			BookingDTO book = new BookingDTO(bookingID, customerID, selection.getHotelID(), selection.getRoomType(),
					selection.getQuantity(), selection.getExtraBed(), selection.getPrice(), 
					selection.getFromDate(), selection.getToDate(), bookDetails, PIN);
			bookingDAOImpl.addBooking(book);
			bookingDAOImpl.getConnection().close();
			
			request.getSession().setAttribute("PIN", PIN);
			request.getSession().setAttribute("URL", URL);
			
		} catch (ServiceLocatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MailSender sender;
		try{
			sender = MailSender.getMailSender();
			String fromAddress = "zfan667@cse.unsw.edu.au";
			String subject = "Your booking details";
			String content = "Your booking PIN is: " + PIN + "/n" + 
							 "Please follow below link to check your booking infomation:" + URL; 
			sender.sendMessage(fromAddress, email, subject, content);
		}catch (Exception e){
			logger.severe("Oopsies, could not send message "+e.getMessage());
			e.printStackTrace();
		}
		return "/book.jsp";
	}

	private boolean isValid(String str) {
		// TODO Auto-generated method stub
		if(str == null){
			return false;
		}
		int length = str.length();
		if(length == 0){
			return false;
		}
		for(int i = 0; i < length; i++){
			if(str.charAt(i) == ';' || str.charAt(i) == '!' || str.charAt(i) == '?' || str.charAt(i) == '*' || str.charAt(i) == '(' || str.charAt(i) == ')' || str.charAt(i) == '"'){
				return false;
			}
		}		
		return true;
	}
}