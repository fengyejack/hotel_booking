package edu.unsw.comp9321.DTO;

import java.sql.Timestamp;

public class RoomGeneralDTO {

	private int ID;
	private String type;
	private int quantity;
	private int price;
	private double discount;
	private Timestamp discountfrom;
	private Timestamp discountto;
	private int hotel_ID;

	public RoomGeneralDTO(int ID, String type, int quantity, int price, double discount,
			Timestamp from, Timestamp to, int hotel_ID){
		super();
		this.ID = ID;
		this.type = type;
		this.quantity = quantity;
		this.price = price;
		this.discount = discount;
		this.discountfrom = from;
		this.discountto = to;
		this.hotel_ID = hotel_ID;
	}
	
	public void setRoomID(int ID){
		this.ID = ID;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}
	
	public void setPrice(int price){
		this.price = price;
	}
	
	public void setDiscount(double discount){
		this.discount = discount;
	}
	
	public void setDiscountFrom(Timestamp from){
		this.discountfrom = from;
	}
	
	public void setDiscountTo(Timestamp to){
		this.discountto = to;
	}
	
	public void setHotelID(int ID){
		this.hotel_ID = ID;
	}
	
	public int getRoomID(){
		return this.ID;
	}
	
	public String getType(){
		return this.type;
	}
	
	public int getQuantity(){
		return this.quantity;
	}
	
	public int getPrice(){
		return this.price;
	}
	
	public double getDiscount(){
		return this.discount;
	}
	
	public Timestamp getDiscountFrom(){
		return this.discountfrom;
	}
	
	public Timestamp getDiscountTo(){
		return this.discountto;
	}
	
	public int getHotelID(){
		return this.hotel_ID;
	}
}
