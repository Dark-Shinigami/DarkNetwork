package de.psych.main.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TagNacht implements CommandExecutor {


	

	
	public boolean onCommand(CommandSender sender, Command cmd,String cmdLabel, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("§cYou are no Player!");
				return true;
				
		}

		if (cmd.getName().equalsIgnoreCase("time")) {
			Player p = (Player) sender;
			if (p.hasPermission("dnetwork.zeit")) {

				if (args.length == 0) {

					p.sendMessage("§6>> §eDarkNetwork §6| §7Bitte nutze §b/time <Day/Night>§7.");

				} else if (args.length == 1) {

					if (args[0].equalsIgnoreCase("day") || args[0].equalsIgnoreCase("d")) {

						p.getWorld().setTime(0);
						p.sendMessage("§6>> §eDarkNetwork §6| §7Zeit wurde auf §bTag §7gesetzt.");

					} else if (args[0].equalsIgnoreCase("night") || args[0].equalsIgnoreCase("n")) {

						p.getWorld().setTime(22000);
						p.sendMessage("§6>> §eDarkNetwork §6| §7Zeit wurde auf §bNacht §7gesetzt.");

					} else {
						p.sendMessage("§6>> §eDarkNetwork §6| §7Bitte nutze §b/time <Day/Night>§7.");

					}

				} else {
					p.sendMessage("§6>> §eDarkNetwork §6| §7Syntax Fehler.");

				}

			} else {
				p.sendMessage("§6>> §eDarkNetwork §6| §7Keine Berechtigung.");
			}

			return false;

		}

		return false;
	}

}
