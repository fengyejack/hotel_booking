package edu.unsw.comp9321.DTO;

public class UserDTO {
	
	private int ID;
	private String surname;
	private String otherName;
	private String role;
	private String username;
	private String password;
	private String email;
	private String details;
	private int PIN;
	
	public UserDTO(int ID, String surname, String otherName, String role, String username, String password, String email, String details, int PIN){
		super();
		this.ID = ID;
		this.surname = surname;
		this.otherName = otherName;
		this.role = role;
		this.username = username;
		this.password = password;
		this.email = email;	
		this.details = details;
		this.PIN = PIN;
	}

	public void setUserId(int ID) {
		this.ID = ID;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public void setOthername(String otherName) {
		this.otherName = otherName;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setDetails(String details){
		this.details = details;
	}
	
	public void setPIN(int PIN){
		this.PIN = PIN;
	}
	
	public int getUserID(){
		return this.ID;
	}
	
	public String getSurname() {
		return this.surname;
	}
	
	public String getOthername() {
		return this.otherName;
	}
	
	public String getRole() {
		return this.role;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getDetails(){
		return this.details;
	}
	
	public int getPIN(){
		return this.PIN;
	}
}
