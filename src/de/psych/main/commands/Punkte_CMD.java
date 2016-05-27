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

import de.psych.main.Main;

public class Punkte_CMD implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String cmdlabel, String[] args) {
		
		if(cs instanceof ConsoleCommandSender){
			cs.sendMessage("Du musst ein Spieler sein.");
			return true;
		}
		Player p = (Player) cs;
		
		if(args.length == 3){
			if(!(p.hasPermission("elobby.punkte"))){
				p.sendMessage("§cKeine Rechte!");
				return true;
			}
			try{
				int punkte = Integer.parseInt(args[2]);
				Player ap = Bukkit.getPlayer(args[1]);
				
				if(args[0].contains("add")){
					p.sendMessage(Main.prP);
					p.sendMessage("");
					p.sendMessage(Main.head+"§7Dem Spieler §c"+ap.getName()+" §7wurden §3"+punkte+" §7Punkte §ahinzugefügt!");
					ap.sendMessage(Main.prP);
					ap.sendMessage("");
					ap.sendMessage(Main.head+"§7Du hast §3"+punkte+" §7Punkte §aerhalten§7!");
					addPunkte(ap, punkte);
				}else if(args[0].contains("remove")){
					p.sendMessage(Main.prP);
					p.sendMessage("");
					p.sendMessage(Main.head+"§7Dem Spieler §c"+ap.getName()+" §7wurden §3"+punkte+" §7Punkte §cabgezogen!");
					removePunkte(ap, punkte);
					ap.sendMessage(Main.prP);
					ap.sendMessage("");
					ap.sendMessage(Main.head+"§7Dir wurden §3"+punkte+" §cabgezogen§7!");
				}else if(args[0].contains("set")){
					p.sendMessage(Main.prP);
					p.sendMessage("");
					p.sendMessage(Main.head+"§7Dem Spieler §c"+ap.getName()+" §7wurden die Punkte auf §3"+punkte+" §7gesetzt!");
					setPunkte(ap, punkte);
					ap.sendMessage(Main.prP);
					ap.sendMessage("");
					ap.sendMessage(Main.head+"§7Deine Punkte wurden auf §3"+punkte+" §7gesetzt!");
				}else if(args[0].contains("enough")){
					if(hasEnough(ap, punkte)){
						p.sendMessage(Main.prP);
						p.sendMessage("");
						p.sendMessage(Main.head+"§7Hat genug!");
					}else{
						p.sendMessage(Main.prP);
						p.sendMessage("");
					p.sendMessage(Main.head+"§7Hat nicht genung!");
				}
					
				} else {
					p.sendMessage(Main.prP);
					p.sendMessage("");
					p.sendMessage(Main.head+"§7Bitte gebe §3/punkte <add/remove/set/enough> <Spieler> <Punkte> §7an!");
				}
					
				}catch (NumberFormatException e){
					p.sendMessage("§cDu musst eine Zahl angeben!");
				}catch (NullPointerException e){
					p.sendMessage(Main.prP);
					p.sendMessage("");
					p.sendMessage(Main.head+"§7Der Angegebene Spieler ist §cOffline§7!");
				}
			
	/*		if(hasEnough(p, 100) == true){
				p.sendMessage("cGENUG");
			} else {
				p.sendMessage("cNicht Genug");
			}
			*/
			
		} else {
			p.sendMessage(Main.prP);
			p.sendMessage("");
			p.sendMessage(Main.head+"Du hast aktuell §c"+getPunkte(p)+" §cPunkte§7!");
		}
		
		return false;
	}

	public static int getPunkte(Player player) {
		int punkte = 0;
		
		File ordner = new File("plugins//E-Lobby");
		File file = new File("plugins//E-Lobby//Punkte");
		
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
		File ordner = new File("plugins//E-Lobby");
		File file = new File("plugins//E-Lobby//Punkte");
		
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
		File ordner = new File("plugins//E-Lobby");
		File file = new File("plugins//E-Lobby//Punkte");
		
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
		File ordner = new File("plugins//E-Lobby");
		File file = new File("plugins//E-Lobby//Punkte");
		
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
