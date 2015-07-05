package edu.unsw.comp9321.DTO;

public class RoomSpecificDTO {
	
	private int ID;
	private int number;
	private String type;
	private String condition;
	private int hotel_ID;

	public RoomSpecificDTO(int ID, int number, String type, String condition, int hotel_ID){
		super();
		this.ID = ID;
		this.number = number;
		this.type = type;
		this.condition = condition;
		this.hotel_ID = hotel_ID;
	}
	
	public void setRoomID(int ID){
		this.ID = ID;
	}
	
	public void setNumber(int number){
		this.number = number;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public void setCondition(String condition){
		this.condition = condition;
	}
	
	public void setHotelID(int ID){
		this.hotel_ID = ID;
	}
	
	public int getRoomID(){
		return this.ID;
	}
	
	public int getNumber(){
		return this.number;
	}
	
	public String getType(){
		return this.type;
	}
	
	public String getCondition(){
		return this.condition;
	}
	
	public int getHotelID(){
		return this.hotel_ID;
	}
}
