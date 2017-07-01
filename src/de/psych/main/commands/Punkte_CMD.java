package de.psych.main.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;


public class Punkte_CMD implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String cmdlabel, String[] args) {
		
		if(cs instanceof ConsoleCommandSender){
			cs.sendMessage("You are no Player!");
			return true;
		}
		Player p = (Player) cs;
		
		if(args.length == 3){
			if(!(p.hasPermission("dnetwork.punkte"))){
				p.sendMessage("§6>> §eDarkNetwork §6| §7Keine Berechtigung.");
				return true;
			}
			try{
				int punkte = Integer.parseInt(args[2]);
				Player ap = Bukkit.getPlayer(args[1]);
				
				if(args[0].contains("add")){
					p.sendMessage("§6>> §eDarkNetwork §6| §7Dem Spieler §b"+ap.getName()+" §7wurden §b"+punkte+" §7Punkte hinzugefügt.");
					ap.sendMessage("§6>> §eDarkNetwork §6| §7Du hast §b"+punkte+" §7Punkte erhalten.");
					addPunkte(ap, punkte);
				}else if(args[0].contains("remove")){
					p.sendMessage("§6>> §eDarkNetwork §6| §7Dem Spieler §b"+ap.getName()+" §7wurden §b"+punkte+" §7Punkte abgezogen.");
					removePunkte(ap, punkte);
					ap.sendMessage("§6>> §eDarkNetwork §6| §7Dir wurden §b"+punkte+" §7Punkte abgezogen.");
				}else if(args[0].contains("set")){
					p.sendMessage("§6>> §eDarkNetwork §6| §7Dem Spieler §b"+ap.getName()+" §7wurden die Punkte auf §b"+punkte+" §7gesetzt.");
					setPunkte(ap, punkte);
					ap.sendMessage("§6>> §eDarkNetwork §6| §7Deine Punkte wurden auf §b"+punkte+" §7gesetzt.");
				}else if(args[0].contains("enough")){
					if(hasEnough(ap, punkte)){
						p.sendMessage("§6>> §eDarkNetwork §6| §7Hat genug Punkte.");
					}else{
						p.sendMessage("§6>> §eDarkNetwork §6| §7Hat nicht genug Punkte.");
				}
					
				} else {
					p.sendMessage("§6>> §eDarkNetwork §6| §7Bitte nutze §b/punkte <add/remove/set/enough> <Spieler> <Punkte>§7.");
				}
					
				}catch (NumberFormatException e){
					p.sendMessage("§6>> §eDarkNetwork §6| §7Du musst eine Zahl angeben.");
				}catch (NullPointerException e){
					p.sendMessage("§6>> §eDarkNetwork §6| §7Der angegebene Spieler ist §boffline§7.");
				}
			
	/*		if(hasEnough(p, 100) == true){
				p.sendMessage("cGENUG");
			} else {
				p.sendMessage("cNicht Genug");
			}
			*/
			
		} else {
			p.sendMessage("§6>> §eDarkNetwork §6| §7Dein Aktueller Punktestand ist: "+getPunkte(p));
		}
		
		return false;
	}

	public static int getPunkte(Player player) {
		int punkte = 0;
		
		File ordner = new File("plugins//DarkNetwork");
		File file = new File("plugins//DarkNetwork//Punkte");
		
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		if(!(ordner.exists())){
			ordner.mkdir();
		}
		
		if(!(file.exists())){
			try {
				file.createNewFile();
				
				cfg.set("Punkte."+player.getName(), 0);
				cfg.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		punkte = cfg.getInt("Punkte."+player.getName());
		
		return punkte;
	}
	
	
	public static void addPunkte(Player player, int punkte){
		File ordner = new File("plugins//DarkNetwork");
		File file = new File("plugins//DarkNetwork//Punkte");
		
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		if(!(ordner.exists())){
			ordner.mkdir();
		}
		
		if(!(file.exists())){
			try {
				file.createNewFile();
				cfg.set("Punkte."+player.getName(), 0);
				cfg.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		int punkteV = cfg.getInt("Punkte."+player.getName());
		int punkteN = punkteV+punkte;
		
		cfg.set("Punkte."+player.getName(),punkteN);
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void removePunkte(Player player, int punkte){
		File ordner = new File("plugins//DarkNetwork");
		File file = new File("plugins//DarkNetwork//Punkte");
		
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		if(!(ordner.exists())){
			ordner.mkdir();
		}
		
		if(!(file.exists())){
			try {
				file.createNewFile();
				cfg.set("Punkte."+player.getName(), 0);
				cfg.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		int punkteV = cfg.getInt("Punkte."+player.getName());
		int punkteN = punkteV-punkte;
		
		if(punkteN < 0){
			cfg.set("Punkte."+player.getName(),0);
		}else{
			cfg.set("Punkte."+player.getName(),punkteN);
		}
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static boolean hasEnough(Player player, int punkte){
		boolean hasEnough = false;
		
		int punkteV = getPunkte(player);
		
		if(punkteV >= punkte){
			hasEnough = true;
		} else {
			hasEnough = false;
		}
		
		return hasEnough;
	}
	public static void setPunkte(Player player, int punkte){
		File ordner = new File("plugins//DarkNetwork");
		File file = new File("plugins//DarkNetwork//Punkte");
		
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		if(!(ordner.exists())){
			ordner.mkdir();
		}
		
		if(!(file.exists())){
			try {
				file.createNewFile();
				cfg.set("Punkte."+player.getName(), punkte);
				cfg.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		cfg.set("Punkte."+player.getName(), punkte);
		
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
