package de.psych.main.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GM1 implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd,String cmdLabel, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("§cYou are no Player!");
				return true;
				
		}
		Player p = (Player) sender;
		if (p.hasPermission("dnetwork.gm1")) {
	

			

				if (cmd.getName().equalsIgnoreCase("GM1")) {

					if (args.length == 0) {

						p.setGameMode(GameMode.CREATIVE);

						p.sendMessage("§6>> §eDarkNetwork §6| §7Dein Spielmodus wurde auf §bCreative §7gesetzt.");

						return true;

					}
				}

			

		} else {

			p.sendMessage("§6>> §eDarkNetwork §6| §7Keine Berechtigung.");

		}
		return true;
	}
}