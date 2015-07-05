package edu.unsw.comp9321.DTO;

public class OccupancyDTO {
	
	private int hotel_ID;
	private int available_number;
	private int occupied_number;


	public OccupancyDTO(int hotel_ID, int available_number,int occupied_number){
		super();
		this.hotel_ID = hotel_ID;
		this.available_number = available_number;
		this.occupied_number = occupied_number;
	}
	
	public void sethotelID(int hotel_ID){
		this.hotel_ID = hotel_ID;
	}
	
	public void setavailablenumber(int available_number){
		this.available_number = available_number;
	}
	
	public void occupiednumber(int occupied_number){
		this.occupied_number = occupied_number;
	}
	
	
	public int gethotelID(){
		return this.hotel_ID;
	}
	
	public int getavailablenumber(){
		return this.available_number;
	}
	
	public int getoccupiednumber(){
		return this.occupied_number;
	}
	

}
