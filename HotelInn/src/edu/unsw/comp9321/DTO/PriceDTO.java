package edu.unsw.comp9321.DTO;

public class PriceDTO {
	
	private String type;
	private int price;

	public PriceDTO(String type, int price){
		super();
		this.type = type;
		this.price = price;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public void setPrice(int price){
		this.price = price;
	}
	
	public String getType(){
		return this.type;
	}
	
	public int getPrice(){
		return this.price;
	}
}