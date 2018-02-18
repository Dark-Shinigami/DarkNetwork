package de.psych.main.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import de.psych.main.Main;

/**
 * Created by ErebosTheDark 23.05.2016
 */

public class StaffChatCMD implements CommandExecutor{

    public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args){
        if(cs instanceof ConsoleCommandSender){
            cs.sendMessage("You are no Player!");
            return true;
        }
        Player p = (Player) cs;
        if(p.hasPermission("dnetwork.staffchat")){
            if(Main.staff.contains(p)){
                Main.staff.remove(p);
                p.sendMessage("§6>> §eDarkNetwork §6| §7Du schreibst jetzt nicht mehr im §bStaff Chat§7.");
            }else {
                Main.staff.add(p);
                p.sendMessage("§6>> §eDarkNetwork §6| §7Du schreibst jetzt im §bStaff Chat§7.");
            }
        }else{
        	p.sendMessage("§6>> §eDarkNetwork §6| §7Keine Berechtigung.");
        }
        return false;
    }
}