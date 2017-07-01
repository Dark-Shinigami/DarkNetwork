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


public class Warp implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String cmdlabel, String[] args) {
		
		if(cs instanceof ConsoleCommandSender){
			cs.sendMessage("You are no Player!");
			return true;
		}
		Player p = (Player) cs;
		
		if(args.length == 1){
			     tpWarp(p, args[0]);
		}else if(args.length == 2){
			if(p.hasPermission("dnetwork.setwarp")){
				if(args[0].equalsIgnoreCase("set")){
					setWarp(args[1],p);
					
				}else if(args[0].equalsIgnoreCase("del")){
					delWarp(args[1],p);
					
				} else {
					p.sendMessage("§6>> §eDarkNetwork §6| §7Bitte nutze §b/warp <set/del> <Name>§7.");
					return true;
				}
			}else{
				p.sendMessage("§6>> §eDarkNetwork §6| §7Keine Berechtigung.");
				return true;
			}
			
		} else {
			p.sendMessage("§6>> §eDarkNetwork §6| §7Bitte nutze §b/warp <Name>§7.");
			return true;
		}
		
		return true;
		
	}
	
	public static void setWarp(String WarpName, Player p){
		File ordner = new File("plugins//DarkNetwork//Warps");
		File file = new File("plugins//DarkNetwork//Warps//"+ WarpName.toLowerCase()+".yml");
		
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
	  p.sendMessage("§6>> §eDarkNetwork §6| §7Der Warp §b"+WarpName.toLowerCase()+" §7wurde erfolgreich gesetzt.");
	}
	
	public static void tpWarp(Player p, String WarpName){
		File ordner = new File("plugins//DarkNetwork//Warps");
		File file = new File("plugins//DarkNetwork//Warps//"+ WarpName.toLowerCase()+".yml");
		if(!ordner.exists()){
			ordner.mkdir();
		}

		if(!file.exists()){
			p.sendMessage("§6>> §eDarkNetwork §6| §7Der angegebene Warp Punkt Existiert nicht.");
			return;
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
	}
	
	public static void delWarp(String WarpName, Player p){

		File file = new File("plugins//DarkNetwork//Warps//"+ WarpName.toLowerCase()+".yml");
		
		if(file.exists()){
			file.delete();
			p.sendMessage("§6>> §eDarkNetwork §6| §7Der Warp §b"+WarpName.toLowerCase()+" §7wurde erfolgreich gelöscht.");
		} else {
			p.sendMessage("§6>> §eDarkNetwork §6| §7Der angegebene Warp Punkt Existiert nicht.");
		}
	}
}

