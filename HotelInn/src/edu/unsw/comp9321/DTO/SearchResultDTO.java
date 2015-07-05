package edu.unsw.comp9321.DTO;

import java.util.Date;

public class SearchResultDTO {
	
	private String roomType;
	private int quantity;
	private int price;
	private Date from;
	private Date to;
	private int hotelID;
	private int extraBed;

	public SearchResultDTO(int hotelID, String roomType, int quantity, int price, Date from, Date to, int extraBed){
		super();
		this.hotelID = hotelID;
		this.roomType = roomType;
		this.quantity = quantity;
		this.price = price;
		this.from = from;
		this.to = to;
		this.extraBed = extraBed;
	}

	public void setRoomType(int hotelID){
		this.hotelID = hotelID;
	}
	
	public void setRoomType(String roomType){
		this.roomType = roomType;
	}
	
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}
	
	public void setPrice(int price){
		this.price = price;
	}
	
	public void setFromDate(Date from){
		this.from = from;
	}
	
	public void setToDate(Date to){
		this.to = to;
	}
	
	public void setExtraBed(int extraBed){
		this.extraBed = extraBed;
	}
	
	public int getHotelID(){
		return this.hotelID;
	}
	
	public String getRoomType(){
		return this.roomType;
	}
	
	public int getQuantity(){
		return this.quantity;
	}
	
	public int getPrice(){
		return this.price;
	}
	
	public Date getFromDate(){
		return this.from;
	}
	
	public Date getToDate(){
		return this.to;
	}
	
	public int getExtraBed(){
		return this.extraBed;
	}
}
