package de.psych.main.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.psych.main.Main;

public class Warp implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String cmdlabel, String[] args) {
		
		if(cs instanceof ConsoleCommandSender){
			cs.sendMessage("Du musst ein Spieler sein.");
			return true;
		}
		Player p = (Player) cs;
		
		if(args.length == 1){
			     tpWarp(p, args[0]);
		}else if(args.length == 2){
			if(p.hasPermission("elobby.setwarp")){
				if(args[0].equalsIgnoreCase("set")){
					setWarp(args[1],p);
					
				}else if(args[0].equalsIgnoreCase("del")){
					delWarp(args[1],p);
					
				} else {
					p.sendMessage(Main.prI);
					p.sendMessage("");
					p.sendMessage(Main.head+"§7Bitte benutze §c/warp <set/del> <Name>");
				}
			}else{
				p.sendMessage(Main.prI);
				p.sendMessage("");
				p.sendMessage(Main.head+Main.np);
			}
			
		} else {
			p.sendMessage(Main.prI);
			p.sendMessage("");
			p.sendMessage(Main.head+"§7Bitte benutze §c/warp <Name>");
		}
		
		return true;
		
	}
	
	public static void setWarp(String WarpName, Player p){
		File ordner = new File("plugins//E-Lobby//Warps");
		File file = new File("plugins//E-Lobby//Warps//"+ WarpName.toLowerCase()+".yml");
		
		if(!ordner.exists()){
			ordner.mkdir();
		}
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	  FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
	  Location loc = p.getLocation();
	  
	  cfg.set("X", loc.getX());
	  cfg.set("Y", loc.getY());
	  cfg.set("Z", loc.getZ());
	  cfg.set("Yaw", loc.getYaw());
	  cfg.set("Pitch", loc.getPitch());
	  cfg.set("WorldName", loc.getWorld().getName());
	  
	  try {
		cfg.save(file);
	} catch (IOException e) {
		e.printStackTrace();
	}
		p.sendMessage(Main.prI);
		p.sendMessage("");
		p.sendMessage(Main.head+"§7Der Warp §3"+WarpName.toLowerCase()+" §7wurde gesetzt!");
	}
	
	public static void tpWarp(Player p, String WarpName){
		File ordner = new File("plugins//E-Lobby//Warps");
		File file = new File("plugins//E-Lobby//Warps//"+ WarpName.toLowerCase()+".yml");
		if(!ordner.exists()){
			ordner.mkdir();
		}

		if(!file.exists()){
			p.sendMessage(Main.prI);
			p.sendMessage("");
			p.sendMessage(Main.head+"§7Der Warp existiert nicht!");
		}
		
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		World welt = Bukkit.getWorld(cfg.getString("WorldName"));
		
		double yaw = cfg.getDouble("Yaw");
		double pitch = cfg.getDouble("Pitch");
		
		p.teleport(new Location(welt, 
				cfg.getDouble("X"),
				cfg.getDouble("Y"),
				cfg.getDouble("Z"),
				(float) yaw,
				(float) pitch));
		p.sendMessage("§7Teleported to §3"+WarpName.toLowerCase()+"§7.");
	}
	
	public static void delWarp(String WarpName, Player p){

		File file = new File("plugins//E-Lobby//Warps//"+ WarpName.toLowerCase()+".yml");
		
		if(file.exists()){
			file.delete();
			p.sendMessage(Main.prI);
			p.sendMessage("");
			p.sendMessage(Main.head+"§7Der Warp §3"+WarpName.toLowerCase()+" §7wurde gelöscht!");
		} else {
			p.sendMessage(Main.prI);
			p.sendMessage("");
			p.sendMessage(Main.head+"§7Der Warp existiert nicht!");
		}
	}
}

