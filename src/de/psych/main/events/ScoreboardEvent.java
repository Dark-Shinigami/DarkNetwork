package de.psych.main.events;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.DisplaySlot;

import me.Stijn.ScoreboardAPI.ScoreboardAPI;
import net.minecraft.server.v1_8_R3.IScoreboardCriteria;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardDisplayObjective;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardObjective;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardScore;
import net.minecraft.server.v1_8_R3.Scoreboard;
import net.minecraft.server.v1_8_R3.ScoreboardObjective;
import net.minecraft.server.v1_8_R3.ScoreboardScore;


public class ScoreboardEvent implements Listener{

	public static int coins = 0;
	
	public static void setBoard(Player p){
		Scoreboard sb = new Scoreboard();

		
		ScoreboardObjective obj = sb.registerObjective("ad", IScoreboardCriteria.b);
		obj.setDisplayName("§e§l>> §c§lDarkException");
		PacketPlayOutScoreboardObjective createPacket = new PacketPlayOutScoreboardObjective(obj,0);
		PacketPlayOutScoreboardDisplayObjective display = new PacketPlayOutScoreboardDisplayObjective(1, obj);
		ScoreboardScore a = new ScoreboardScore(sb, obj, "§1");
		ScoreboardScore a1 = new ScoreboardScore(sb, obj, "§lDein Rang:");
		ScoreboardScore a2 = null;
		if(p.hasPermission("dnetwork.admin")){
			a2 = new ScoreboardScore(sb, obj, "§4Admin");
		}else if(p.hasPermission("dnetwork.dev")){
			a2 = new ScoreboardScore(sb, obj, "§9Developer");
		}else if(p.hasPermission("dnetwork.mod")){
			a2 = new ScoreboardScore(sb, obj, "§2Moderator");
		}else{
			a2 = new ScoreboardScore(sb, obj, "§aSpieler");
		}
		ScoreboardScore a9 = new ScoreboardScore(sb, obj, "§4");
		ScoreboardScore a10 = new ScoreboardScore(sb, obj, "§lTeamSpeak:");
		ScoreboardScore a11 = new ScoreboardScore(sb, obj, "§bCRACKHD.NET");
		
		a.setScore(6);a1.setScore(5);a2.setScore(4);;a9.setScore(3);
		a10.setScore(2);a11.setScore(1);
		PacketPlayOutScoreboardObjective removePacket = new PacketPlayOutScoreboardObjective(obj, 1);
		PacketPlayOutScoreboardScore pa = new PacketPlayOutScoreboardScore(a);
		PacketPlayOutScoreboardScore pa1 = new PacketPlayOutScoreboardScore(a1);
		PacketPlayOutScoreboardScore pa2 = new PacketPlayOutScoreboardScore(a2);
		PacketPlayOutScoreboardScore pa9 = new PacketPlayOutScoreboardScore(a9);
		PacketPlayOutScoreboardScore pa10 = new PacketPlayOutScoreboardScore(a10);
		PacketPlayOutScoreboardScore pa11 = new PacketPlayOutScoreboardScore(a11);
		
		
		sendPacket(p, removePacket);
		sendPacket(p, createPacket);
		sendPacket(p, display);
		
		sendPacket(p, pa);
		sendPacket(p, pa1);
		sendPacket(p, pa2);
		sendPacket(p, pa9);
		sendPacket(p, pa10);
		sendPacket(p, pa11);
		
		
	}
	
	public static void setScoreboardWithAPI(Player p){
		ScoreboardAPI sb = new ScoreboardAPI("aaa", "§e§l>> §cDarkNetwork", DisplaySlot.SIDEBAR);
		sb.setScore("§1", 9);
		sb.setScore("§lDein Rang:", 8);
		if(p.hasPermission("dnetwork.admin")){
			sb.setScore("§4Admin", 7);
		}else if(p.hasPermission("dnetwork.dev")){
			sb.setScore("§9Developer",7);
		}else if(p.hasPermission("dnetwork.mod")){
			sb.setScore("§2Moderator",7);
		}else{
			sb.setScore("§aSpieler",7);
		}
		sb.setScore("§2", 6);
		sb.setScore("§lTeamSpeak:", 2);
		sb.setScore("§bDarkNetwork.eu", 1);
		
	}
	
	private static void sendPacket(Player p, Packet<?> packet){
		((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
	}
	
	
}
