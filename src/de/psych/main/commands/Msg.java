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


public class Msg implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String cmdlabel, String[] args) {
		
		if(cs instanceof ConsoleCommandSender){
			
			if(args.length >= 2){
				Player a = Bukkit.getPlayer(args[0]);
				try{
				
				if(a == cs){
				
					cs.sendMessage("§6>> §eMSG §6| §7Es müssen verschiedene Spieler angegeben sein.");
					
				}
				
				String msg = "";
				for(int i = 1; i != args.length; i++){
					msg = msg + " " + args[i];
				}
				
				cs.sendMessage("[MSG] Konsole ->  "+a.getName()+" >> "+msg);
				a.sendMessage("§7[§6MSG§7] §cConsole§6 ➤§9 You §6>> §b"+msg);
				
				
				} catch (NullPointerException e){
				
					cs.sendMessage("§6>> §eMSG §6| §7Der angegebene Spieler ist §boffline§7.");
					
				}
				
			} else {
		
				cs.sendMessage("§6>> §eDarkNetwork §6| §7Bitte benutze §b/msg <Spieler> <Nachricht>§7.");
				
			}
			return true;
			
		}
		
		
		Player p = (Player) cs;
		
		if(args.length >= 2){
			Player a = p.getServer().getPlayer(args[0]);
			try{
			
			if(a == p){
				p.sendMessage("§6>> §eDarkNetwork §6| §7Es müssen verschiedene Spieler angegeben sein.");
				return true;
			}
			
			String msg = "";
			for(int i = 1; i != args.length; i++){
				msg = msg + " " + args[i];
			}
			
			p.sendMessage("§7[§6MSG§7] §9You §6 ➤§9 "+a.getName()+" §6>> §b"+msg);
			a.sendMessage("§7[§6MSG§7] §9"+p.getName()+"§6 ➤§9 You §6>> §b"+msg);
			
			for(Player all : Bukkit.getOnlinePlayers()){
				File ordner = new File("plugins//DarkNetwork");
				File file = new File("plugins//DarkNetwork//MSGSpyOption");
				
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
				
				boolean spy = cfg.getBoolean(all.getName());
				
				if(spy == true){
					all.sendMessage("§7[§6MSG §cSPY§7] §7From = §9"+p.getName()+"§8 ➤§9"+a.getName()+" §8>> §b"+msg);
				}
			}
			
			} catch (NullPointerException e){
				p.sendMessage("§6>> §eDarkNetwork §6| §7Der Spieler ist offline.");
				return true;
			}
			
		} else {
			p.sendMessage("§6>> §eDarkNetwork §6| §7Bitte benutze §b/msg <Spieler> <Nachricht>§7.");
			return true;
		}
		
		return false;
	}
	
	

}
