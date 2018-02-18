package de.psych.main.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;

import de.psych.mysql.MySQL;

public class BanManager {
	
	public static void ban(String uuid, String playername, String reason, long seconds){
		long end = 0;
		if(seconds == -1){
			end = -1;
		}else{
			long current = System.currentTimeMillis();
			long millis = seconds*1000;
			end = current+millis;	
		}
		MySQL.update("INSERT INTO BannedPlayers (Spielername, UUID, Ende, Grund) VALUES ('"+playername+"','"+uuid+"','"+end+"','"+reason+"')");
		if(Bukkit.getPlayer(playername) != null){
			Bukkit.getPlayer(playername).kickPlayer("�cDu wurdest vom Server gebannt!\n" + 
		   "\n" + 
			"�3Grund: �e" + getReason(uuid) + "\n" +
		    "\n" +
		//	"�3Verbleibende Zeit: �e" + getRemainingTime(uuid) +"\n" +
		     "�3Du kannst �c�nkeinen�r �3Entbannugsantrag stellen!");
		}
	}
	
	public static void unban(String uuid){
		MySQL.update("DELETE FROM BannedPlayers WHERE UUID='"+uuid+"'");
		
	}
	
	public static boolean isBanned(String uuid){
		ResultSet rs = MySQL.getResult("SELECT Ende FROM BannedPlayers WHERE UUID='"+uuid+"'");
		
		try {
			return rs.next();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static String getReason(String uuid){
		ResultSet rs = MySQL.getResult("SELECT * FROM BannedPlayers WHERE UUID='"+uuid+"'");
		try{
			while(rs.next()){
				return rs.getString("Grund");
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return "";
	}
	
	public static Long getEnd(String uuid){
		ResultSet rs = MySQL.getResult("SELECT * FROM BannedPlayers WHERE UUID='"+uuid+"'");
		try{
			while(rs.next()){
				return rs.getLong("Ende");
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<String> getBannedPlayers(){
		List<String> list = new ArrayList<String>();
		ResultSet rs = MySQL.getResult("SELECT * FROM BannedPlayers");
		
			try{
				while(rs.next()){
					list.add(rs.getString("Spielername"));
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
				
			return list;
		
	}

	public static String getRemainingTime(String uuid) {
		long current = System.currentTimeMillis();
		long end = getEnd(uuid);
		if(end == -1){
			return "�4PERMANENT";
		}
		long millis= end - current;
		
		long seconds = 0;
		long minutes=0;
		long hours=0;
		long days=0;
		long weeks=0;
		while(millis > 1000){
			millis-=1000;
			seconds++;
		}
		while(seconds > 60){
			seconds -=60;
			minutes++;
		}
		while(minutes >60){
			minutes-=60;
			hours++;
		}
		while(hours > 24){
			hours-=24;
			days++;
		}
		while(days > 7){
			days-=7;
			weeks++;
		}
		
		return "�e" + weeks + " Woche(n) "+days+ " Tag(e) "+hours+" Stunde(n) "+minutes+" Minute(n) "+ seconds + " Sekunde(n)";
	}

}
