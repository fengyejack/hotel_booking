package edu.unsw.comp9321.DAO;

import edu.unsw.comp9321.DTO.PINDTO;

public interface PINDAO {
	
	public int getPINByURL(String URL);
	
	public void addPIN(PINDTO pin);
	
	public void deleteByPIN(int PIN);

}
