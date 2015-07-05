package edu.unsw.comp9321.DTO;

public class QuantityDTO {
	
	private String type;
	private int quantity;

	public QuantityDTO(String type, int quantity){
		super();
		this.type = type;
		this.quantity = quantity;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public void setPrice(int quantity){
		this.quantity = quantity;
	}
	
	public String getType(){
		return this.type;
	}
	
	public int getQuantity(){
		return this.quantity;
	}

}
