package de.psych.main.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.psych.main.Main;
import net.md_5.bungee.api.ChatColor;

public class Motd implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String cmdlabel, String[] args) {
		Player p = (Player) cs;
		
		if(args.length >= 1){
			String motd = "";
			for(int i = 0; i < args.length; i++){
				motd = motd + " " + args[i];
			}
			
			File file = new File("plugins//E-Lobby//Motd.yml");
			File ordner = new File("plugins//E-Lobby");
			
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
			
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
			
			cfg.set("Motd", motd);
			try {
				cfg.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			p.sendMessage(Main.prI);
			p.sendMessage("");
			p.sendMessage("§7§l[§c➤§7§l] §7Die neue Motd: "+ChatColor.translateAlternateColorCodes('&', motd));
			
			return true;
		}else if(args.length == 0){
			p.sendMessage(Main.prefixG);
			p.sendMessage("");
			p.sendMessage("§7§l[§c➤§7§l] §cUsage: /motd [Message]");
			return true;
		}
		
		return false;
		
		
		
	}

}
