package edu.unsw.comp9321.control;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.unsw.comp9321.DTO.SearchResultDTO;

public class Confirm implements Command{

	static Logger logger = Logger.getLogger(Confirm.class.getName());
	DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		int index = 0;
		ArrayList<SearchResultDTO> resultList = (ArrayList<SearchResultDTO>) request.getSession().getAttribute("resultList");
//		BookingDTO booking = (BookingDTO) request.getSession().getAttribute("booking");
		System.out.println(resultList.size());
//		logger.info("Extra Bed String: " + request.getParameter("extraBed"));
//		logger.info("Extra Bed Integer: " + Integer.parseInt(request.getParameter("extraBed")));
//		logger.info("Result List String: " + request.getParameter("resultListIndex"));
//		logger.info("Result List Integer: " + Integer.parseInt(request.getParameter("extraBed")));
		
		if(request.getParameter("extraBed") == null){
			logger.info("extraBed null");
			if(request.getParameter("resultListIndex") == null){
				return "/invalidInput.jsp";
			}
			else if(request.getParameter("resultListIndex") == "0"){
				index = 0;
				resultList.get(index).setExtraBed(0);
			}
			else{
				index = Integer.parseInt(request.getParameter("resultListIndex"));
			}
		}
		else {
			logger.info("extraBed not null");
			if(request.getParameter("resultListIndex") == null || 
					Integer.parseInt(request.getParameter("extraBed")) != Integer.parseInt(request.getParameter("resultListIndex"))){
				return "/invalidInput.jsp";
			}
			else {
				index = Integer.parseInt(request.getParameter("extraBed"));
				resultList.get(index).setExtraBed(1);
			}	
		}
		
		SearchResultDTO selection = resultList.get(index);
		request.getSession().setAttribute("selection", selection);
		return "/confirmation1.jsp";
	}
}
