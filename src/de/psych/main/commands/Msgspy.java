package de.psych.main.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.psych.main.Main;

public class Msgspy implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String cmdlabel, String[] args) {
		
		if(cs instanceof ConsoleCommandSender){
			cs.sendMessage("Nur für Spieler");
			return true;
		}
		
		File ordner = new File("plugins//E-Lobby");
		File file = new File("plugins//E-Lobby//MSGSpyOption");
		
		if(!(ordner.exists())){
			ordner.mkdir();
		}
		if(!(file.exists())){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		Player p = (Player) cs;
		
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		boolean spy = cfg.getBoolean(p.getName());
		
		if(spy == true){
			p.sendMessage(Main.prefixG);
			p.sendMessage(Main.head+"Die Überwachung der Privaten Nachichten wurde §cdeaktiviert§7.");
			cfg.set(p.getName(), false);
		} else {
			p.sendMessage(Main.prefixG);
			p.sendMessage(Main.head+"Die Überwachung der Privaten Nachichten wurde jetzt §aaktiviert§7.");
			cfg.set(p.getName(), true);
		}
		try {
			cfg.save(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

}
