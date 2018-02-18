package de.psych.main.events;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class PlayerMoveListener implements Listener{

	@EventHandler
	public void onMove(PlayerMoveEvent e){
		Player p = e.getPlayer();
		
		//scoreboard.setScoreboard(p);
		
		if(p.getLocation().getBlock().getType() == Material.IRON_PLATE){
			Vector v = p.getLocation().getDirection().multiply(1).setY(1);
			p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 5, 5);
			p.setVelocity(v);
		}
		
		
		Material b = Material.GOLD_BLOCK;
		
		if(p.getLocation().getBlock().getType() == b){
			Vector v = p.getLocation().getDirection().multiply(1).setY(1);
			p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 5, 5);
			p.setVelocity(v);
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e){
		Player p = e.getPlayer();
		
		try{
		if(p.getGameMode() == GameMode.CREATIVE || p.getGameMode() == GameMode.SPECTATOR){
			return;
		}
		
				if(p.isOnGround()){
			if(!p.getAllowFlight()){
				p.setAllowFlight(true);
			}
		}
			
		
	
		}catch(NullPointerException ex){
			
		}
	}
	

	
}
