package edu.unsw.comp9321.DTO;

import java.util.Date;

public class BookingDTO {
	
	private int ID;
	private int customer_ID;
	private int hotel_ID;
	private String type;
	private int quantity;
	private int extra_bed;
	private int price;
	private Date checkin;
	private Date checkout;
	private String details;
	private int PIN;

	public BookingDTO(int ID, int customer_ID, int hotel_ID, String type, int quantity, int extra_bed, int price,
			Date checkin, Date checkout, String details, int PIN){
		super();
		this.ID = ID;
		this.customer_ID = customer_ID;
		this.hotel_ID = hotel_ID;
		this.type = type;
		this.quantity = quantity;
		this.extra_bed = extra_bed;
		this.price = price;
		this.checkin = checkin;
		this.checkout = checkout;
		this.details = details;
		this.PIN = PIN;
	}
	
	public void setBookingID(int ID){
		this.ID = ID;
	}
	
	public void setCustomerID(int ID){
		this.customer_ID = ID;
	}
	
	public void setHotelID(int ID){
		this.hotel_ID = ID;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}
	
	public void setExtraBed(int extra_bed){
		this.extra_bed = extra_bed;
	}
	
	public void setPrice(int price){
		this.price = price;
	}
	
	public void setCheckin(Date from){
		this.checkin = from;
	}
	
	public void setCheckout(Date to){
		this.checkout = to;
	}
	
	public void setDeatils(String details){
		this.details = details;
	}
	
	public void setPIN(int PIN){
		this.PIN = PIN;
	}
	
	public int getID(){
		return this.ID;
	}
	
	public int getCustomerID(){
		return this.customer_ID;
	}
	
	public int getHotelID(){
		return this.hotel_ID;
	}
	
	public String getType(){
		return this.type;
	}
	
	public int getQuantity(){
		return this.quantity;
	}
	
	public int getExtraBed(){
		return this.extra_bed;
	}
	
	public int getPrice(){
		return this.price;
	}
	
	public Date getCheckin(){
		return this.checkin;
	}
	
	public Date getCheckout(){
		return this.checkout;
	}
	
	public String getDetails(){
		return this.details;
	}
	
	public int getPIN(){
		return this.PIN;
	}
}