package edu.unsw.comp9321.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import edu.unsw.comp9321.DAO.PriceDAO;
import edu.unsw.comp9321.exception.EmptyResultException;
import edu.unsw.comp9321.exception.ServiceLocatorException;

public class PriceDAOImpl implements PriceDAO{
	
	static Logger logger = Logger.getLogger(PriceDAOImpl.class.getName());
	private Connection connection;
	DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
//	DateFormat fmt1 = new SimpleDateFormat("MM-dd");
	
	public PriceDAOImpl() throws ServiceLocatorException, SQLException{
		connection = DBConnectionFactory.getConnection();
		logger.info("Got connection");
	}
	
	public Connection getConnection(){
		return this.connection;
	}

	@Override
	public int getPrice(int hotelID, String type, Date bookFrom, Date bookTo)
			throws EmptyResultException {
		// TODO Auto-generated method stub
		int normalPrice = 0;
		double discountRate = 0;
		Date discountFrom = null;
		Date discountTo = null;
		double peakRate = 0;
		Date peakFrom = null;
		Date peakTo = null;
		int finalPrice = 0;
		java.sql.Date sqlFrom = new java.sql.Date(bookFrom.getTime());
		java.sql.Date sqlTo = new java.sql.Date(bookTo.getTime());
		
		
		try{
			PreparedStatement stmnt = connection.prepareStatement("SELECT ROOM_PRICE FROM TBL_ROOMS_GENERAL WHERE HOTEL_ID = ? AND ROOM_TYPE = ?");
			stmnt.setInt(1, hotelID);
			stmnt.setString(2, type);
			ResultSet res = stmnt.executeQuery();
//			Statement stmnt = connection.createStatement();
//			String query_normalPrice = 
//					"SELECT TBL_ROOMS_GENERAL.ROOM_PRICE"
//					+ "FROM TBL_ROOMS_GENERAL"
//					+ "WHERE TBL_ROOMS_GENERAL.HOTEL_ID = 1"
//					+ "AND TBL_ROOMS_GENERAL.ROOM_TYPE = 'SINGLE';";
//			
//			ResultSet res = stmnt.executeQuery(query_normalPrice);
//			logger.info("The normalPrice set size is "+res.getFetchSize());
			while(res.next()){
				normalPrice = res.getInt("ROOM_PRICE");
				logger.info("Hotel " + hotelID + "Roomtype "  + type + " (normalPrice): " + normalPrice);
			}
			res.close();
			stmnt.close();
		}catch(Exception e){
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		
		
		try{
			PreparedStatement stmnt = connection.prepareStatement("SELECT ROOM_DISCOUNT, ROOM_DISCOUNTFROM, ROOM_DISCOUNTTO FROM TBL_ROOMS_GENERAL WHERE ((ROOM_DISCOUNTFROM <= (?) AND ROOM_DISCOUNTTO >= (?)) OR (ROOM_DISCOUNTFROM <= (?) AND ROOM_DISCOUNTTO >= (?))) AND HOTEL_ID = (?) AND ROOM_TYPE = (?)");
			stmnt.setDate(1, sqlFrom);
			stmnt.setDate(2, sqlFrom);
			stmnt.setDate(3, sqlTo);
			stmnt.setDate(4, sqlTo);
			stmnt.setInt(5, hotelID);
			stmnt.setString(6, type);
			ResultSet res = stmnt.executeQuery();
//			Statement stmnt = connection.createStatement();
//			String query_dicountRate = 
//					"SELECT TBL_ROOMS_GENERAL.ROOM_DISCOUNT, TBL_ROOMS_GENERAL.ROOM_DISCOUNTFROM, TBL_ROOMS_GENERAL.ROOM_DISCOUNTTO"
//					+ "FROM TBL_ROOMS_GENERAL"
//					+ "WHERE ((TBL_ROOMS_GENERAL.ROOM_DISCOUNTFROM <= '2014-09-10' AND TBL_ROOMS_GENERAL.ROOM_DISCOUNTTO >= '2014-09-10')"
//					+ "OR (TBL_ROOMS_GENERAL.ROOM_DISCOUNTFROM <= '2014-09-13' AND TBL_ROOMS_GENERAL.ROOM_DISCOUNTTO >= '2014-09-13'))"
//					+ "AND (TBL_ROOMS_GENERAL.HOTEL_ID = 1)"
//					+ "AND (TBL_ROOMS_GENERAL.ROOM_TYPE = 'SINGLE');";
//			ResultSet res = stmnt.executeQuery(query_dicountRate);
//			logger.info("The discountRate set size is "+res.getFetchSize());
			while(res.next()){
				discountRate = res.getDouble("ROOM_DISCOUNT");
				logger.info("Hotel " + hotelID + "Roomtype "  + type + "(discountRate): " + discountRate);
				discountFrom = res.getDate("ROOM_DISCOUNTFROM");
				logger.info("Hotel " + hotelID + "Roomtype "  + type + "(discountFrom): " + discountFrom);
				discountTo = res.getDate("ROOM_DISCOUNTTO");
				logger.info("Hotel " + hotelID + "Roomtype "  + type + "(discountTo): " + discountTo);
			}
			res.close();
			stmnt.close();
		}catch(Exception e){
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		
		
		try{
			PreparedStatement stmnt = connection.prepareStatement("SELECT ROOM_PEAK, ROOM_PEAKFROM, ROOM_PEAKTO FROM TBL_PEAK WHERE ((ROOM_PEAKFROM <= (?) AND ROOM_PEAKTO >= (?)) OR (ROOM_PEAKFROM <= (?) AND ROOM_PEAKTO >= (?))) AND ROOM_TYPE = (?)");
			stmnt.setDate(1, sqlFrom);
			stmnt.setDate(2, sqlFrom);
			stmnt.setDate(3, sqlTo);
			stmnt.setDate(4, sqlTo);
			stmnt.setString(5, type);
			ResultSet res = stmnt.executeQuery();
//			Statement stmnt = connection.createStatement();
//			String query_peakRate = 
//					"SELECT TBL_PEAK.ROOM_PEAK, TBL_PEAK.ROOM_PEAKFROM, TBL_PEAK.ROOM_PEAKTO"
//					+ "FROM TBL_PEAK"
//					+ "WHERE ((TBL_PEAK.ROOM_PEAKFROM <= '2014-09-10' AND TBL_PEAK.ROOM_PEAKTO >= '2014-09-10')"
//					+ "OR (TBL_PEAK.ROOM_PEAKFROM <= '2014-09-13' AND TBL_PEAK.ROOM_PEAKTO >= '2014-09-13'))"
//					+ "AND (TBL_PEAK.ROOM_TYPE = 'SINGLE');";
//			
//			ResultSet res = stmnt.executeQuery(query_peakRate);
			logger.info("The peakRate set size is "+res.getFetchSize());
			while(res.next()){
				peakRate = res.getDouble("ROOM_PEAK");
				logger.info("Hotel " + hotelID + "Roomtype "  + type + "(PeakRate): " + peakRate);
				peakFrom = res.getDate("ROOM_PEAKFROM");
				logger.info("Hotel " + hotelID + "Roomtype "  + type + "(PeakFrom): " + peakFrom);
				peakTo = res.getDate("ROOM_PEAKTO");
				logger.info("Hotel " + hotelID + "Roomtype "  + type + "(PeakTo): " + peakTo);
			}
			res.close();
			stmnt.close();
		}catch(Exception e){
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		
		
		if(peakFrom == null){
			if(discountFrom == null){
				finalPrice = normalPrice;
			}
			else{
				// BF DF BT DT
				if(daysDiff(bookFrom, discountFrom) >= 0 && daysDiff(discountFrom, bookTo) >= 0 && daysDiff(bookTo, discountTo) >= 0){
					finalPrice = (int) ((daysDiff(bookFrom, discountFrom)  * normalPrice
							+ daysDiff(discountFrom, bookTo) * discountRate * normalPrice) / 
							daysDiff(bookFrom, bookTo));
				}
				// DF BF BT DT
				else if(daysDiff(discountFrom, bookFrom) >= 0 && daysDiff(bookTo, discountTo) >= 0){
					finalPrice = (int) (discountRate * normalPrice);					
				}
				// DF BF DT BT
				else if(daysDiff(discountFrom, bookFrom) >= 0 && daysDiff(bookFrom, discountTo) >= 0 && daysDiff(discountTo, bookTo) >= 0){
					finalPrice = (int) ((daysDiff(bookFrom, discountTo)  * normalPrice* discountRate 
							+ daysDiff(discountTo, bookTo) * normalPrice) / 
							daysDiff(bookFrom, bookTo));					
				}
				else{
					finalPrice = normalPrice;
				}					
			}
		}
		else{
			if(discountFrom == null){
				// BF PF BT PT
				if(daysDiff(bookFrom, peakFrom) >= 0 && daysDiff(peakFrom, bookTo) >= 0 && daysDiff(bookTo, peakTo) >= 0){
					finalPrice = (int) ((daysDiff(bookFrom, peakFrom)  * normalPrice
							+ daysDiff(peakFrom, bookTo) * peakRate * normalPrice) / 
							daysDiff(bookFrom, bookTo));
				}
				// PF BF BT PT
				else if(daysDiff(peakFrom, bookFrom) >= 0 && daysDiff(bookTo, peakTo) >= 0){
					finalPrice = (int) (peakRate * normalPrice);					
				}
				// PF BF PT BT
				else if(daysDiff(peakFrom,bookFrom) >= 0 && daysDiff(bookFrom,peakTo) >= 0 && daysDiff(peakTo, bookTo) >= 0){
					finalPrice = (int) ((daysDiff(bookFrom, peakTo)  * normalPrice* peakRate 
							+ daysDiff(peakTo, bookTo) * normalPrice) / 
							daysDiff(bookFrom, bookTo));
				}
				else{
					finalPrice = normalPrice;
				}					
			}
			else{
				// DF DT PF PT
				if(daysDiff(discountFrom, peakFrom) >= 0){
					// DF BF DT PF BT PT
					finalPrice = (int) ((daysDiff(bookFrom, discountTo) * discountRate * normalPrice 
							+ daysDiff(discountTo, peakFrom) * normalPrice
							+ daysDiff(peakFrom, bookTo) * peakRate * normalPrice)
							/ daysDiff(bookFrom, bookTo));
				}
				// PF PT DF DT 
				else if(daysDiff(peakTo, discountFrom) >= 0){
					// PF BF PT DF BT DT
					finalPrice = (int) ((daysDiff(bookFrom, peakTo) * peakRate * normalPrice
							+ daysDiff(peakTo, discountFrom) * normalPrice
							+ daysDiff(discountFrom, bookTo) * discountRate * normalPrice)
							/ daysDiff(bookFrom, bookTo));
				}
				// DF PF PT DT
				else if(daysDiff(discountFrom, peakFrom) >= 0 && daysDiff(peakTo, discountTo) >= 0){
					//DF BF PF BT PT DT
					if(daysDiff(discountFrom, bookFrom) >= 0 && daysDiff(bookFrom, peakFrom) >= 0
							&& daysDiff(peakFrom, bookTo) >= 0 && daysDiff(bookTo, peakTo) >= 0){
						finalPrice = (int) ((daysDiff(bookFrom, peakFrom) * discountRate * normalPrice 
								+ daysDiff(peakFrom, bookTo) * peakRate * discountRate * normalPrice) 
								/ daysDiff(bookFrom, bookTo));
					}
					// DF PF BF BT PT DT
					else if(daysDiff(peakFrom, bookFrom) >= 0 && daysDiff(bookTo, peakTo) >= 0){
						finalPrice = (int) (peakRate * normalPrice * discountRate);
					}
					// DF PF BF PT BT DT
					else if(daysDiff(peakFrom, bookFrom) >= 0 && daysDiff(bookFrom, peakTo) >= 0
							&& daysDiff(peakTo, bookTo) >= 0 && daysDiff(bookTo, discountTo) >= 0){
						finalPrice = (int) ((daysDiff(bookFrom, peakTo) * discountRate * peakRate * normalPrice
								+ daysDiff(peakTo, bookTo) * discountRate * normalPrice)
								/ daysDiff(bookFrom, bookTo));
					}
					// BF DF PF BT PT DT
					else if(daysDiff(bookFrom, discountFrom) >= 0 && daysDiff(peakFrom, bookTo) >= 0 && daysDiff(bookTo, peakTo) >= 0){
						finalPrice = (int) ((daysDiff(bookFrom, discountFrom) * normalPrice
								+ daysDiff(discountFrom, peakFrom) * discountRate * normalPrice
								+ daysDiff(peakFrom, bookTo) * discountRate * peakRate * normalPrice)
								/ daysDiff(bookFrom, bookTo));
					}
					// DF PF BF PT DT BT
					else if(daysDiff(peakFrom, bookFrom) >= 0 && daysDiff(bookFrom, peakTo) >= 0 && daysDiff(discountTo, bookTo) >= 0){
						finalPrice = (int) ((daysDiff(bookFrom, peakTo) * discountRate * peakRate * normalPrice
								+ daysDiff(peakTo, discountTo) * discountRate * normalPrice
								+ daysDiff(discountTo, bookTo) * normalPrice)
								/ daysDiff(bookFrom, bookTo));
					}
					else{
						finalPrice = normalPrice;
					}
				}
				// PF DF DT PT
				else if(daysDiff(peakFrom, discountFrom) >= 0 && daysDiff(discountTo, peakTo) >= 0){
					// PF BF DF BT DT PT
					if(daysDiff(peakFrom, bookFrom) >= 0 && daysDiff(bookFrom, discountFrom) >= 0 
							&& daysDiff(discountFrom, bookTo) >= 0 && daysDiff(bookTo, discountTo) >= 0){
						finalPrice = (int) ((daysDiff(bookFrom, discountFrom) * peakRate * normalPrice 
								+ daysDiff(discountFrom, bookTo) * peakRate * discountRate * normalPrice) 
								/ daysDiff(bookFrom, bookTo));
					}
					// PF DF BF BT DT PT
					else if(daysDiff(discountFrom, bookFrom) >= 0 && daysDiff(bookTo, discountTo) >= 0){
						finalPrice = (int) (peakRate * normalPrice * discountRate);						
					}
					// PF DF BF DT BT PT
					else if(daysDiff(peakFrom, bookFrom) >= 0 && daysDiff(bookFrom, discountTo) >= 0 
							&& daysDiff(discountTo, bookTo) >= 0 && daysDiff(bookTo, peakTo) >= 0){
						finalPrice = (int) ((daysDiff(bookFrom, discountTo) * peakRate * discountRate * normalPrice 
								+ daysDiff(discountTo, bookTo) * peakRate * normalPrice) 
								/ daysDiff(bookFrom, bookTo));
						
					}
					// BF PF DF BT DT PT
					else if(daysDiff(bookFrom, peakFrom) >= 0 && daysDiff(discountFrom, bookTo) >= 0 && daysDiff(bookTo, discountTo) >= 0){
						finalPrice = (int) ((daysDiff(bookFrom, peakFrom) * normalPrice
								+ daysDiff(peakFrom, discountFrom) * peakRate *normalPrice
								+ daysDiff(discountFrom, bookTo) * peakRate * discountRate * normalPrice)
								/ daysDiff(bookFrom, bookTo));
					}
					// PF DF BF DT PT BT
					else if(daysDiff(discountFrom, bookFrom) >= 0 && daysDiff(bookFrom, discountTo) >= 0 && daysDiff(peakTo, bookTo) >= 0){
						finalPrice = (int) ((daysDiff(bookFrom, discountTo) * peakRate * discountRate * normalPrice
								+ daysDiff(discountTo, peakTo) * peakRate * normalPrice
								+ daysDiff(peakTo, bookTo) * normalPrice)
								/ daysDiff(bookFrom, bookTo));
					}
					else{
						finalPrice = normalPrice;
					}
				}
				// DF PF DT PT
				else if(daysDiff(discountFrom, peakFrom) >= 0 && daysDiff(peakFrom, discountTo) >= 0 && daysDiff(discountTo, peakTo) >= 0){
					// BF DF PF BT DT PT
					if(daysDiff(bookFrom, discountFrom) >= 0 && daysDiff(peakFrom, bookTo) >= 0 && daysDiff(bookTo, discountTo) >= 0){
						finalPrice = (int) ((daysDiff(bookFrom, discountFrom) * normalPrice
								+ daysDiff(discountFrom, peakFrom) * discountRate * normalPrice
								+ daysDiff(peakFrom, bookTo) * peakRate * discountRate * normalPrice)
								/ daysDiff(bookFrom, bookTo));						
					}
					// DF BF PF BT DT PT
					else if(daysDiff(discountFrom, bookFrom) >= 0 && daysDiff(bookFrom, peakFrom) >= 0 
							&& daysDiff(peakFrom, bookTo) >= 0 && daysDiff(bookTo, discountTo) >= 0){
						finalPrice = (int) ((daysDiff(bookFrom, peakFrom) * discountRate * normalPrice
								+ daysDiff(peakFrom, bookTo) * peakRate * discountRate * normalPrice)
								/ daysDiff(bookFrom, bookTo));
					}
					// DF BF PF DT BT PT
					else if(daysDiff(discountFrom, bookFrom) >= 0 && daysDiff(bookFrom, peakFrom) >= 0 &&
							daysDiff(discountTo, bookTo) >= 0 && daysDiff(bookTo, peakTo) >= 0){
						finalPrice = (int) ((daysDiff(bookFrom, peakFrom) * discountRate * normalPrice
								+ daysDiff(peakFrom, discountTo) * peakRate * discountRate * normalPrice
								+ daysDiff(discountTo, bookTo) * peakRate * normalPrice)
								/ daysDiff(bookFrom, bookTo));
					}
					// DF PF BF BT DT PT
					else if(daysDiff(peakFrom, bookFrom) >= 0 && daysDiff(bookTo, discountTo) >= 0){
						finalPrice = (int) (peakRate * discountRate * normalPrice);								
							}
					// DF PF BF DT BT PT
					else if(daysDiff(peakFrom, bookFrom) >= 0 && daysDiff(bookFrom, discountTo) >= 0 &&
							daysDiff(discountTo, bookTo) >= 0 && daysDiff(bookTo, peakTo) >= 0){
						finalPrice = (int) ((daysDiff(bookFrom, discountTo) * peakRate * discountRate * normalPrice
								+ daysDiff(discountTo, bookTo) * peakRate * normalPrice)
								/ daysDiff(bookFrom, bookTo));
					}
					// DF PF BF DT PT BT
					else if(daysDiff(peakFrom, bookFrom) >= 0 && daysDiff(bookFrom, discountTo) >= 0 && daysDiff(peakTo, bookTo) >= 0){
						finalPrice = (int) ((daysDiff(bookFrom, discountTo) * peakRate * discountRate * normalPrice
								+ daysDiff(discountTo, peakTo) * peakRate * normalPrice
								+ daysDiff(peakTo, bookTo) * normalPrice)
								/ daysDiff(bookFrom, bookTo));
					}
					else{
						finalPrice = normalPrice;
					}	
				}
				// PF DF PT DT
				else if(daysDiff(peakFrom, discountFrom) >= 0 && daysDiff(discountFrom, peakTo) >= 0 && daysDiff(peakTo, discountTo) >= 0){
					// BF PF DF BT PT DT
					if(daysDiff(bookFrom, peakFrom) >= 0 && daysDiff(discountFrom, bookTo) >= 0 && daysDiff(bookTo, peakTo) >= 0){
						finalPrice = (int) ((daysDiff(bookFrom, peakFrom) * normalPrice
								+ daysDiff(peakFrom, discountFrom) * peakRate * normalPrice
								+ daysDiff(discountFrom, bookTo) * peakRate * discountRate * normalPrice)
								/ daysDiff(bookFrom, bookTo));
					}
					// PF BF DF BT PT DT
					else if(daysDiff(peakFrom, bookFrom) >= 0 && daysDiff(bookFrom, discountFrom) >= 0 &&
							daysDiff(discountFrom, bookTo) >= 0 && daysDiff(bookTo, peakTo) >= 0){
						finalPrice = (int) ((daysDiff(bookFrom, discountFrom) * peakRate * normalPrice
								+ daysDiff(discountFrom, bookTo) * peakRate * discountRate * normalPrice)
								/ daysDiff(bookFrom, bookTo));
					}
					// PF BF DF PT BT DT
					else if(daysDiff(peakFrom, bookFrom) >= 0 && daysDiff(bookFrom, discountFrom) >= 0 
							&& daysDiff(peakTo, bookTo) >= 0 && daysDiff(bookTo, discountTo) >= 0){
						finalPrice = (int) ((daysDiff(bookFrom, discountFrom) * peakRate * normalPrice
								+ daysDiff(discountFrom, peakTo) * peakRate * discountRate * normalPrice
								+ daysDiff(peakTo, bookTo) * discountRate * normalPrice)
								/ daysDiff(bookFrom, bookTo));
					}
					// PF DF BF BT PT DT
					else if(daysDiff(discountFrom, bookFrom) >= 0 && daysDiff(bookTo, peakTo) >= 0){
						finalPrice = (int) (peakRate * discountRate * normalPrice);
					}
					// PF DF BF PT BT DT
					else if(daysDiff(discountFrom, bookFrom) >= 0 && daysDiff(bookFrom, peakTo) >= 0 
							&& daysDiff(peakTo, bookTo) >= 0 && daysDiff(bookTo, discountTo) >= 0){
						finalPrice = (int) ((daysDiff(bookFrom, peakTo) * peakRate * discountRate * normalPrice
								+ daysDiff(peakTo, bookTo) * discountRate * normalPrice)
								/ daysDiff(bookFrom, bookTo));
					}
					// PF DF BF PT DT BT
					else if(daysDiff(discountFrom, bookFrom) >= 0 && daysDiff(bookFrom, peakTo) >= 0 && daysDiff(discountTo, bookTo) >= 0 ){
						finalPrice = (int) ((daysDiff(bookFrom, peakTo) * peakRate * discountRate * normalPrice
								+ daysDiff(peakTo, discountTo) * discountRate * normalPrice
								+ daysDiff(discountTo, bookTo) * normalPrice)
								/ daysDiff(bookFrom, bookTo));
					}
				}
				else{
					finalPrice = normalPrice;
				}
			}
			
		}
		return finalPrice;
	}

	private float daysDiff(Date from, Date to) {
		// TODO Auto-generated method stub
		return Math.round((to.getTime() - from.getTime()) / 86400000D);
	}
}
