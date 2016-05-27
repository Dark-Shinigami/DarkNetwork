package de.psych.main.events;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import de.psych.main.Main;
import de.psych.main.commands.Login_CMD;

public class LoginListener implements Listener{
	
	@EventHandler
	public void on(PlayerQuitEvent e){
		Player p = e.getPlayer();
		
		Login_CMD.login(p, false);
	}
	@EventHandler
	public void on(PlayerMoveEvent e){
		Player p = e.getPlayer();
		Location locO = e.getFrom();
		Location locN = e.getTo();
		
		if(Main.login.contains(p.getName())){
			return;
		}
		if(locO.getX() != locN.getX() || locO.getY() != locN.getY() || locO.getZ() != locN.getZ() ){

			p.teleport(locO);
			
		}
		
	}
	@EventHandler
	public void on(EntityDamageEvent e){
		Entity en = e.getEntity();

		if(en instanceof Player){
			Player p = (Player)en;
			if(Login_CMD.isLoggedIn(p)){
				return;
			}
			
			
			e.setCancelled(true);
			
			
		}

	}
	@EventHandler
	public void on(EntityDamageByEntityEvent e){
		Entity en = e.getDamager();

		
		if(en instanceof Player){
			Player p = (Player) en;
			if(Login_CMD.isLoggedIn(p)){
				return;
			}
			
			e.setCancelled(true);
		}
	}

}
