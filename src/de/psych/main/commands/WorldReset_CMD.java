package de.psych.main.commands;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.psych.main.Main;

public class WorldReset_CMD implements CommandExecutor{
	
	Main m;
	
	public WorldReset_CMD(Main main){
		this.m = main;
	}

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		
		if(cs instanceof Player){
			
			@SuppressWarnings("unused")
			Player p = (Player) cs;
			
            cs.sendMessage("§7Welt wird zurückgesetzt!");
            World w = resetWorld(new File("world_backup"), new File("limbo"), "limbo");
           
            for(Player all : Bukkit.getOnlinePlayers()) {
                all.teleport(w.getSpawnLocation());
                all.sendMessage("§7[...]");
                Warp.tpWarp(all, "spawn");
            }
            cs.sendMessage("§7Welt zurückgesetzt!");
            
           
            return true;
			
			
		}

		
		
		return false;
	}
	
    public World resetWorld(File backup, File toReset, String worldname) {
        
        for(Player all : Bukkit.getOnlinePlayers()) {
            all.teleport(Bukkit.getWorld("world").getSpawnLocation());
        }
       
        Bukkit.getServer().unloadWorld(worldname, true);
       
        try {
            FileUtils.deleteDirectory(toReset);
        } catch (IOException e) {
            e.printStackTrace();
        }
       
        if(!toReset.exists()) {
            try {
                FileUtils.copyDirectory(backup, toReset);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
       
        World w = Bukkit.createWorld(new WorldCreator(worldname));
        return w;
    }

}
