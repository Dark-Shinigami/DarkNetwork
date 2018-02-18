package de.psych.mysql;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

public class MySQL {
	
	public static String host;
	public static String port;
	public static String database;
	public static String username;
	public static String password;
	public static Connection con;

	
	public static void connect(){
		if(!isConnected()){
			
			
			try {
				con = DriverManager.getConnection("jdbc:mysql://"+ host + ":" + port + "/" + database, username, password);
				System.out.println("[MySQL] Verbindung aufgebaut!");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void close(){
		if(isConnected()){
			try {
				con.close();
				System.out.println("[MySQL] Verbindung geschlossen!");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static boolean isConnected(){
		return (con == null ? false : true);
	
	}
	
	public static void update(String qry){
		if(isConnected()){
			try{
				con.createStatement().executeUpdate(qry);
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	public static Connection getConnection(){
		
		return con;
	}
	
	public static void createTable(){
		/*
		 * 
		 * Syntax: Spielername, UUID, Ende, Grund
		 * 
		 */
		
		
		if(isConnected()){
		try {
			con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS BannedPlayers (Spielername VARCHAR(100), UUID VARCHAR(100), Ende VARCHAR(100), Grund VARCHAR(100))");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
	}
	
	public static ResultSet getResult(String qry){
		if(isConnected()){
			try {
				return con.createStatement().executeQuery(qry);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return null;
	}
	
}
