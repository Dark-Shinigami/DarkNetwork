package de.psych.main.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;


public class PermCmd implements CommandExecutor {
	
	private de.psych.main.Main plugin;
	
	public PermCmd(de.psych.main.Main plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,String[] args){
		if(!(sender instanceof Player)) {
			sender.sendMessage("§cYou are no Player!");
				return true;
				
		}
		Player p = (Player) sender;
		if(p.hasPermission("dnetwork.permissions")) {
		
		
			
			
			// /permission <add/remove> <Spieler> <Permission>
			
			
				
				
				if(args.length == 1){
				String perm = args[0];
				
				if(p.hasPermission(perm)){
					
					p.sendMessage("§6>> §eDarkNetwork §6| §7Du besitzt die Permission §b"+perm+"§7.");
					return true;
				} else {
					p.sendMessage("§6>> §eDarkNetwork §6| §7Du besitzt die Permission §b"+perm+" §7nicht.");
					return true;
				}
			}
			
			if(args.length ==3) {
				if(args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("remove")){
					Player target = Bukkit.getPlayer(args[1]);
					String permission = args[2];
					if(target != null) {
					
						boolean done = setPermission(args[0], target, permission);
						
						if(done){
							p.sendMessage("§6>> §eDarkNetwork §6| §7Rechte hinzugefügt.");
							
						} else {
							p.sendMessage("§6>> §eDarkNetwork §6| §7Ein Fehler ist aufgetreten.");
						}
						
					} else {
						p.sendMessage("§6>> §eDarkNetwork §6| §7Bitte nutze §b/permission <add/remove> <Spieler> <Recht>§7.");
					}
					} else {
						p.sendMessage("§6>> §eDarkNetwork §6| §7Bitte nutze §b/permission <add/remove> <Spieler> <Recht>§7.");
					}
					} else {
						p.sendMessage("§6>> §eDarkNetwork §6| §7Bitte nutze §b/permission <add/remove> <Spieler> <Recht>§7.");
					}
				
				
			
			
			
			
			
			
			
			
				} else {
	
					p.sendMessage("§cAccess Denied!");
				}
		return true;
			
}
	public boolean setPermission(String type, Player target, String permissionName){
		
		
		
			
			if(type.equalsIgnoreCase("add")){
			
			PermissionAttachment attachment = target.addAttachment(plugin);
			attachment.setPermission(permissionName, true);
			
			return true;
		}
		if(type.equalsIgnoreCase("remove")){
			
			PermissionAttachment attachment = target.addAttachment(plugin);
			attachment.setPermission(permissionName, false);
			
			
			return true;
		}
		
		
		return false;
	}

}
