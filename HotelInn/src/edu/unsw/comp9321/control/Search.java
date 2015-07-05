package edu.unsw.comp9321.control;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.unsw.comp9321.DAOImpl.PriceDAOImpl;
import edu.unsw.comp9321.DAOImpl.QuantityDAOImpl;
import edu.unsw.comp9321.DAOImpl.SearchResultDAOImpl;
import edu.unsw.comp9321.DTO.BookingDTO;
import edu.unsw.comp9321.DTO.SearchResultDTO;

public class Search implements Command{
	
	static Logger logger = Logger.getLogger(Search.class.getName());
	DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		if(!isInteger(request.getParameter("quantity")) || !isInteger(request.getParameter("price"))
				|| request.getParameter("checkin") == null || request.getParameter("checkout") == null){
			return "/invalidInput.jsp";
		}

		ArrayList<String> roomType = new ArrayList(Arrays.asList("SINGLE", "TWIN", "QUEEN", "EXECUTIVE", "SUITE"));
		ArrayList<Integer> quantityList = new ArrayList<Integer>();
		ArrayList<Integer> priceList = new ArrayList<Integer>();
		ArrayList<SearchResultDTO> searchResult = new ArrayList<SearchResultDTO>();
		ArrayList<SearchResultDTO> resultList = new ArrayList<SearchResultDTO>();
		int hotelID, price, quantity;
		Date checkin, checkout;
		
		
		try {
			String city = request.getParameter("city");
			checkin = fmt.parse(request.getParameter("checkin"));
			checkout = fmt.parse(request.getParameter("checkout"));
			quantity = Integer.parseInt(request.getParameter("quantity"));
			price = Integer.parseInt(request.getParameter("price"));
			SearchResultDAOImpl result = new SearchResultDAOImpl();
			hotelID = result.getHotelID(city);
			result.getConnection().close();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "/index.jsp";
		}
		
		try {
			QuantityDAOImpl quantityDAO = new QuantityDAOImpl();
			for(int i = 0; i < roomType.size(); i ++){
				int availableQuantity = quantityDAO.getQuantity(hotelID, roomType.get(i), checkin, checkout);
				quantityList.add(availableQuantity);
			}
			quantityDAO.getConnection().close();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "/index.jsp";
		}	
		
		try {
			PriceDAOImpl priceDAO = new PriceDAOImpl();
			for(int i = 0; i < roomType.size(); i ++){
				int unitPrice = priceDAO.getPrice(hotelID, roomType.get(i), checkin, checkout);
				priceList.add(unitPrice);	
			}
			priceDAO.getConnection().close();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "/index.jsp";
		}	
			
			
		for(int i = 0; i < roomType.size(); i ++){
			searchResult.add(new SearchResultDTO(hotelID, roomType.get(i), quantityList.get(i), priceList.get(i), checkin, checkout, 0));
		}
		
		for(int i = 0; i < searchResult.size(); i++){
			if(searchResult.get(i).getPrice() <= price && searchResult.get(i).getQuantity() >= quantity){
				resultList.add(new SearchResultDTO(searchResult.get(i).getHotelID(), searchResult.get(i).getRoomType(), quantity, 
						searchResult.get(i).getPrice(), searchResult.get(i).getFromDate(), searchResult.get(i).getToDate(), 0));
//				totalAvaiableQuantity += searchResult.get(i).getQuantity();
			}
		}
		
		ArrayList<BookingDTO> bookingList = (ArrayList<BookingDTO>)request.getSession().getAttribute("bookingList");
		if(resultList.size() == 0){
			if(bookingList != null){
				return "/keepOrDelete.jsp";
			}
			else{
				return "/noResultFound.jsp";
			}
		}
		else{
			request.getSession().setAttribute("resultList", resultList);
			return "/searchResult.jsp";
		}	
	} 
	
	public static boolean isInteger(String str) {
		if (str == null) {
			return false;
		}
		int length = str.length();
		if (length == 0) {
			return false;
		}
		for (int i = 0; i < length; i++) {
			char c = str.charAt(i);
			if (c <= '/' || c >= ':') {
				return false;
			}
		}
		if(Integer.parseInt(str) == 0){
			return false;
		}
		return true;
	}
}
