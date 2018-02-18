package de.psych.main.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import de.psych.main.utils.BanManager;
import de.psych.main.utils.BanUnit;

public class BanCommands implements CommandExecutor{
	

	/*
	 	/ban <Spieler> <Grund>
		/tempban <Spieler> <Zahlenwert> <Einheit> <Grund>
		/unban <Spieler>
		/check (list) <Spieler>
	*/
	
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {

		if(cmd.getName().equalsIgnoreCase("ban")){
			if(args.length >= 2){
				String playername = args[0];
				if(BanManager.isBanned(getUUID(playername))){
					cs.sendMessage("§cDieser Spieler ist bereits gebannt!");
					return true;
				}
				String reason = "";
				for(int i = 1; i < args.length; i++){
					reason += args[i] + " ";
				}
				BanManager.ban(getUUID(playername), playername, reason, -1);
				cs.sendMessage("§7Du hast §e"+playername+" §4PERMANENT §7gebannt!");
				return true;
			}
			cs.sendMessage("§c/ban <Spieler> <Grund>");
		}
		
		if(cmd.getName().equalsIgnoreCase("tempban")){
			if(args.length >= 4){
				String playername = args[0];
				if(BanManager.isBanned(getUUID(playername))){
					cs.sendMessage("§cDieser Spieler ist bereits gebannt!");
					return true;
				}
				long value;
				try{
					value = Long.valueOf(args[1]);
				}catch(NumberFormatException e){
					cs.sendMessage("§c<Zahlenwert> muss eine Zahl sein!");
					return true;
				}
				if(value >= 500){
					cs.sendMessage("§cDie Zahl muss unter 500 liegen!");
					return true;
				}
				String unitString = args[2];
				String reason = "";
				for(int i = 3; i < args.length; i++){
					reason += args[i] + " ";
				}
				List<String> unitList = BanUnit.getUnitsAsString();
				if(unitList.contains(unitString.toLowerCase())){
					BanUnit unit = BanUnit.getUnit(unitString);
					long seconds = value * unit.getToSecond();
					BanManager.ban(getUUID(playername), playername, reason, seconds);
					cs.sendMessage("§7Du hast §e"+ playername + " §7für §c"+value+ unit.getName() + " §7gebannt!");
					return true;
				}
				cs.sendMessage("§cDiese <Einheit> existiert nicht!");
				return true;
			}
			cs.sendMessage("§c/tempban <Spieler> <Zahlenwert> <Einheit> <Grund>");
			return true;
			
		}
		
		if(cmd.getName().equalsIgnoreCase("check")){
			if(args.length == 1){
				if(args[0].equalsIgnoreCase("list")){
					List<String> list = BanManager.getBannedPlayers();
					if(list.size() == 0){
						cs.sendMessage("§cEs sind aktuell keine Spieler gebannt!");
					}
					cs.sendMessage("§7---------- §6§lBan-Liste §7----------");
					for(String allBanned : BanManager.getBannedPlayers()){
						cs.sendMessage("§e"+allBanned + " §7(Grund: §r" + BanManager.getReason(getUUID(allBanned))+"§7)");
					}
					return true;
				}
				String playername = args[0];
				cs.sendMessage("§7---------- §6§lBan-Infos §7----------");
				cs.sendMessage("§eName: §r" + playername);
				cs.sendMessage("§eGebannt: §r" + (BanManager.isBanned(getUUID(playername)) ? "§aJa!" : "§cNein!"));
				if(BanManager.isBanned(getUUID(playername))){
					cs.sendMessage("§eGrund: §r" + BanManager.getReason(getUUID(playername)));
					cs.sendMessage("§eVerbleibende Zeit: §r" + BanManager.getRemainingTime(getUUID(playername)));
				}
				
				return true;
			}
			cs.sendMessage("§c/check (list) <Spieler>");
		}
		
		if(cmd.getName().equalsIgnoreCase("unban")){
			if(args.length == 1){
				String playername = args[0];
				if(!BanManager.isBanned(getUUID(playername))){
					cs.sendMessage("§cDieser Spieler ist nicht gebannt!");
					return true;
				}
				BanManager.unban(getUUID(playername));
				cs.sendMessage("§7Du hast §e" + playername + " §7entbannt!");
			}
		}
		
		return true;
	}
	
	@SuppressWarnings("deprecation")
	private String getUUID(String playername){
		return Bukkit.getOfflinePlayer(playername).getUniqueId().toString();
	}

}
