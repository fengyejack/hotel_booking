package edu.unsw.comp9321.DTO;

public class PINDTO {
	
	private int PIN;
	private String URL;

	public PINDTO(int PIN, String URL){
		super();
		this.PIN = PIN;
		this.URL = URL;
	}
	
	public void setPIN(int PIN){
		this.PIN = PIN;
	}
	
	public void setURL(String URL){
		this.URL = URL;
	}
	
	public int getPIN(){
		return this.PIN;
	}
	
	public String getURL(){
		return this.URL;
	}

}
